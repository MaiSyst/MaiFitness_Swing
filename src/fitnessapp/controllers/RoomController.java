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
import fitnessapp.models.RoomModel;
import fitnessapp.models.RoomWithSubscribeModel;
import fitnessapp.utilities.API;
import fitnessapp.utilities.Constants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
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
public class RoomController {

    private JButton addRoom;
    private JPanel body;
    private JTextField search;
    private JFrame parent;
    private final MaiFetch fetch;
    private static List<RoomWithSubscribeModel> dataList=new ArrayList<>();
    public RoomController(JFrame parent, JButton addRoom, JPanel body, JTextField search, String token) {

        this.addRoom = addRoom;
        this.body = body;
        this.search = search;
        this.parent = parent;
        fetch = API.fetch(new Authorization(token));
        addRoom.setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "plus.svg", 1f));

        search.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Rechercher votre salle...");
        search.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon(Constants.ICONS_PATH + "search.svg"));
        search.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                if(search.getText().length()>0){
                    searchIt(search.getText());
                }else{
                    resetSearch();
                }
            }
            
        });
        addRoom.addActionListener(l -> onHandleShowModal());
        //reFetchRoom();
        reFetchRoomWithSubcribe();

    }

    private void deleteRoom(String roomId) {
        var option = JOptionPane.showConfirmDialog(parent, "Etes-vous de supprimer?", "Suppression", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                fetch.delete(Constants.ROOM_DELETE_URL_PATH + roomId).then((result, status) -> {
                    if (status == ResponseStatusCode.OK) {
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Salle à été supprimé:");
                        reFetchRoomWithSubcribe();
                    } else {
                        System.out.println(result);
                        Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Error de suppression:");

                    }
                });
            } catch (Exception e) {
            }
        }
    }

    private void editRoom(String roomId, String roomName) {
        new RoomModalController(fetch, roomName, roomId, args -> editToRoomPanel(args[0].toString(), args[1].toString())).show(parent);

    }

    private void reFetchRoom() {
        try {
            fetch.get(Constants.ROOM_FETCH_URL_PATH).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    body.removeAll();
                    Gson gson = new Gson();
                    Type roomListType = new TypeToken<List<RoomModel>>() {
                    }.getType();
                    //Type planningListType=new TypeToken<List<PlanningModel>>(){}.getType();
                    List<RoomModel> models = gson.fromJson(result, roomListType);
                    for (var model : models) {
                        MaiCardMini maiCardMini = new MaiCardMini(model.roomName(), "", model.roomId(),
                                args -> editRoom(args[0], args[1]),
                                args -> deleteRoom(args[0])
                        );
                        body.add(maiCardMini);
                        body.repaint();
                    }

                }
            });

        } catch (MaiException e) {
            System.out.println(e.getMessage());
        }

    }

    private void addToRoomPanel(String roomId, String roomName) {
        MaiCardMini maiCardMini = new MaiCardMini(
                roomName,
                "0 Abonnés",
                roomId,
                args -> editRoom(args[0], args[1]),
                args -> deleteRoom(args[0])
        );
        body.add(maiCardMini);
        body.repaint();
    }

    private void editToRoomPanel(String roomId, String roomName) {

        for (int i = 0; i < body.getComponentCount(); i++) {
            MaiCardMini v = (MaiCardMini) body.getComponent(i);
            if (v.getCardId().equals(roomId)) {
                v.title(roomName);

                body.repaint();
                break;
            }
        }

    }
    private void searchIt(String query){
       for(var comp:body.getComponents()){
           var card=(MaiCardMini)comp;
           comp.setVisible(card.title().trim().toLowerCase().contains(query.trim().toLowerCase()));
       }
    }
    
     private void resetSearch(){
       for(var comp:body.getComponents()){
           comp.setVisible(true);
       }
    }
    
    private void reFetchRoomWithSubcribe() {
        try {
            fetch.get(Constants.ROOM_FETCH_SUBSC_URL_PATH).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    body.removeAll();
                    final Gson gson = new Gson();
                    final Type roomListType = new TypeToken<List<RoomWithSubscribeModel>>() {
                    }.getType();
                    dataList = gson.fromJson(result, roomListType);
                    dataList.forEach(model -> {
                        MaiCardMini maiCardMini = new MaiCardMini(
                                model.roomName(),
                                model.totalSubscribe() + " Abonnés",
                                model.roomId(),
                                args -> editRoom(args[0], args[1]),
                                args -> deleteRoom(args[0]));
                        body.add(maiCardMini);
                        body.repaint();
                    });
                }
            });
        } catch (MaiException e) {
            System.out.println(e.getMessage());
        }

    }

    private void onHandleShowModal() {
        new RoomModalController(fetch, args -> addToRoomPanel(args[0].toString(), args[1].toString())).show(parent);
    }

}
