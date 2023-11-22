/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.controllers;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.maisyst.MaiFetch;
import com.maisyst.exceptions.MaiException;
import com.maisyst.utils.Authorization;
import com.maisyst.utils.enums.ResponseStatusCode;
import fitnessapp.components.MaiCardMini;
import fitnessapp.models.CustomerModel;
import fitnessapp.models.PlanningModel;
import fitnessapp.models.RoomModel;
import fitnessapp.models.UserModel;
import fitnessapp.utilities.API;
import fitnessapp.utilities.Constants;
import fitnessapp.utilities.MaiState;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import raven.toast.Notifications;

/**
 *
 * @author orion90
 */
public final class RoomController implements MaiState {

    private final JPanel body;
    private final JFrame parent;
    private final MaiFetch fetch;
    private static final Logger logger = Logger.getLogger(RoomController.class.getName());

    public RoomController(final JFrame parent, final JButton addRoom, final JPanel body, final JTextField search, final String token) {
        this.body = body;
        this.parent = parent;
        fetch = API.fetch(new Authorization(token));
        addRoom.setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "plus.svg", 1f));

        search.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Rechercher votre salle...");
        search.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon(Constants.ICONS_PATH + "search.svg"));
        search.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (search.getText().length() > 0) {
                    searchIt(search.getText());
                } else {
                    resetSearch();
                }
            }

        });
        addRoom.addActionListener(l -> onHandleShowModal());
        reFetchRoom();

    }

    private void deleteRoom(final String roomId) {
        var option = JOptionPane.showConfirmDialog(parent, "Etes-vous de supprimer?", "Suppression", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                fetch.delete(Constants.ROOM_DELETE_URL_PATH + roomId).then((result, status) -> {
                    if (status == ResponseStatusCode.OK) {
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Salle à été supprimé:");
                        reFetchRoom();
                    } else {
                        Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Error de suppression:");

                    }
                });
            } catch (MaiException e) {
                logger.info(e.getMessage());
            }
        }
    }

    private void editRoom(final String roomId, final String roomName) {
        new RoomModalController(fetch, roomName, roomId,
                args -> editToRoomPanel(args[0].toString(),
                        args[1].toString())).show(parent);

    }

    private void reFetchRoom() {
        try {
            fetch.get(Constants.ROOM_FETCH_URL_PATH).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    body.removeAll();
                    Gson gson = new Gson();
                    Type roomListType = new TypeToken<List<RoomModel>>() {
                    }.getType();
                    List<RoomModel> models = gson.fromJson(result, roomListType);
                    for (var model : models) {

                        var desc = model.customers().size() > 1 ? model.customers().size() + " abonnés" : model.customers().size() + " abonné";
                        MaiCardMini maiCardMini = new MaiCardMini(model.roomName(), desc, model.roomId(),
                                args -> editRoom(args[0], args[1]),
                                args -> deleteRoom(args[0]),
                                args -> showDetailRoom(
                                        model.roomName(),
                                        model.planning(),
                                        model.customers(),
                                        model.manager())
                        );
                        body.add(maiCardMini);
                        body.repaint();
                    }

                }
            });

        } catch (MaiException e) {
            logger.info(e.getMessage());
        }

    }

    private void addToRoomPanel(final String roomId, final String roomName) {
        MaiCardMini maiCardMini = new MaiCardMini(
                roomName,
                "0 Plannings",
                roomId,
                args -> editRoom(args[0], args[1]),
                args -> deleteRoom(args[0]),
                args -> showDetailRoom(args[1], new ArrayList<>(), new ArrayList<>(), null)
        );
        body.add(maiCardMini);
        body.repaint();
    }

    private void editToRoomPanel(final String roomId, final String roomName) {

        for (int i = 0; i < body.getComponentCount(); i++) {
            MaiCardMini v = (MaiCardMini) body.getComponent(i);
            if (v.getCardId().equals(roomId)) {
                v.title(roomName);

                body.repaint();
                break;
            }
        }

    }

    private void searchIt(final String query) {
        for (var comp : body.getComponents()) {
            var card = (MaiCardMini) comp;
            comp.setVisible(card.title().trim().toLowerCase().contains(query.trim().toLowerCase()));
        }
    }

    private void resetSearch() {
        for (var comp : body.getComponents()) {
            comp.setVisible(true);
        }
    }

    @Override
    public void updateState(Object... args) {
        reFetchRoom();
    }

    private void showDetailRoom(final String roomName, final List<PlanningModel> plannings,
            final List<CustomerModel> customers, final UserModel manager) {
        if (manager != null) {
            new RoomDetailsController(parent,
                    roomName,
                    plannings.size() + " Plannings",
                    customers.size() + " Abonnés",
                    0 + " ",
                    0 + " ",
                    0 + " ",
                    manager.firstName() + " " + manager.lastName()
            ).show();
        } else {
            new RoomDetailsController(parent,
                    roomName,
                    plannings.size() + " Plannings",
                    customers.size() + " Abonnés",
                    0 + " ",
                    0 + " ",
                    0 + " ",
                    "Pas de gérant"
            ).show();
        }
    }

    private void onHandleShowModal() {
        new RoomModalController(fetch, args -> addToRoomPanel(args[0].toString(), args[1].toString())).show(parent);
    }

}
