/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.maisyst.MaiFetch;
import com.maisyst.exceptions.MaiException;
import com.maisyst.utils.enums.ResponseStatusCode;
import fitnessapp.models.RoomWithSubscribeModel;
import fitnessapp.screens.UsersModal;
import fitnessapp.utilities.Constants;
import fitnessapp.utilities.MaiFunctionCall;
import fitnessapp.utilities.MaiUtils;
import static fitnessapp.utilities.MaiUtils.getTextPassword;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import raven.toast.Notifications;

/**
 *
 * @author maisyst
 */
public final class UserModalController {

    private final UsersModal usersModal;
    private final MaiFunctionCall callback;
    private final MaiFetch fetch;
    private final Gson gson = new Gson();

    public UserModalController(final MaiFetch fetch, final MaiFunctionCall callback) {
        this.callback = callback;
        this.fetch = fetch;
        usersModal = new UsersModal();
        usersModal.getBtnAdded().addActionListener(l -> onHandleAddNewUser());
        usersModal.getBtnClose().addActionListener(l -> usersModal.dispose());
        fetchRoom();
    }

    public UserModalController(final MaiFetch fetch,
            String firstName,
            String lastName,
            String address,
            String birthdate,
            String roomName,
            final MaiFunctionCall callback) {
        this.callback = callback;
        this.fetch = fetch;
        usersModal = new UsersModal();
        usersModal.getBtnAdded().addActionListener(l -> onHandleEditUser());
        usersModal.getBtnClose().addActionListener(l -> usersModal.dispose());
        usersModal.getTxtFirstname().setText(firstName);
        usersModal.getTxtLastname().setText(lastName);
        usersModal.getTxtAddress().setText(address);
        usersModal.getDatePicker().setText(birthdate);
        for (var i = 0; i < usersModal.getComboxRoomModel().getItemCount(); i++) {
            if (usersModal.getComboxRoomModel().getItemAt(i).roomName().equals(roomName)) {
                usersModal.getComboxRoomModel().setSelectedIndex(i);
                break;
            }
        }
        fetchRoom();
    }

    public void show() {
        usersModal.setVisible(true);
    }

    private void onHandleAddNewUser() {
        if (isInputBlank()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Verifier que si l'un des champs n'est pas vide.");
        } else {
            if (!getTextPassword(usersModal.getPassword())
                    .equals(getTextPassword(usersModal.getConfPassword()))) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Votre mot de passe n'est pas identique.");

            } else {
                try {

                    var rand = (new Random()).nextInt(1000);
                    var room = (RoomWithSubscribeModel) usersModal.getComboxRoomModel().getSelectedItem();
                    Map<String, Object> body = new HashMap<>();
                    body.put("firstName", usersModal.getTxtFirstname().getText());
                    body.put("lastName", usersModal.getTxtLastname().getText());
                    body.put("date", usersModal.getDatePicker().getText());
                    body.put("address", usersModal.getTxtAddress().getText());
                    body.put("phoneNumber", usersModal.getPhoneNumber().getText());
                    body.put("username", usersModal.getTxtFirstname().getText() + "@" + rand);
                    body.put("password", getTextPassword(usersModal.getPassword()));
                    body.put("roomId", room.roomId());
                    fetch.post(Constants.USER_ADD_URL_PATH, body).then((result, status) -> {
                        if (status == ResponseStatusCode.OK) {
                            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Un gérant été ajouté.");
                            clearInput();
                            callback.invoked();
                        } else {
                            if (result.toLowerCase().contains("duplicate")) {
                                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Cette salle est déjà gérée.");
                            } else {
                                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, result);
                            }
                        }
                    });
                } catch (MaiException e) {
                    Logger.getLogger(UserModalController.class.getName(), e.getMessage());
                }
            }

        }
    }
     private void onHandleEditUser() {
        if (isInputBlank()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Verifier que si l'un des champs n'est pas vide.");
        } else {
            if (!getTextPassword(usersModal.getPassword())
                    .equals(getTextPassword(usersModal.getConfPassword()))) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Votre mot de passe n'est pas identique.");

            } else {
                try {

                    var rand = (new Random()).nextInt(1000);
                    var room = (RoomWithSubscribeModel) usersModal.getComboxRoomModel().getSelectedItem();
                    Map<String, Object> body = new HashMap<>();
                    body.put("firstName", usersModal.getTxtFirstname().getText());
                    body.put("lastName", usersModal.getTxtLastname().getText());
                    body.put("date", usersModal.getDatePicker().getText());
                    body.put("address", usersModal.getTxtAddress().getText());
                    body.put("phoneNumber", usersModal.getPhoneNumber().getText());
                    body.put("username", usersModal.getTxtFirstname().getText() + "@" + rand);
                    body.put("password", getTextPassword(usersModal.getPassword()));
                    body.put("roomId", room.roomId());
                    fetch.post(Constants.USER_UPDATE_URL_PATH, body).then((result, status) -> {
                        if (status == ResponseStatusCode.OK) {
                            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Un gérant été ajouté.");
                            clearInput();
                            callback.invoked();
                        } else {
                            if (result.toLowerCase().contains("duplicate")) {
                                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Cette salle est déjà gérée.");
                            } else {
                                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, result);
                            }
                        }
                    });
                } catch (MaiException e) {
                    Logger.getLogger(UserModalController.class.getName(), e.getMessage());
                }
            }

        }
    }

    private boolean isInputBlank() {
        return usersModal.getTxtFirstname().getText().isBlank()
                || usersModal.getTxtAddress().getText().isBlank()
                || usersModal.getTxtLastname().getText().isBlank()
                || usersModal.getPhoneNumber().getText().isBlank()
                || getTextPassword(usersModal.getPassword()).isBlank()
                || getTextPassword(usersModal.getConfPassword()).isBlank()
                || usersModal.getComboxRoomModel().getSelectedItem() == null;
    }

    private void clearInput() {
        usersModal.getTxtFirstname().setText("");
        usersModal.getTxtAddress().setText("");
        usersModal.getTxtLastname().setText("");
        usersModal.getPhoneNumber().setText("");
        usersModal.getPassword().setText("");
        usersModal.getConfPassword().setText("");
    }

    private void fetchRoom() {
        try {
            fetch.get(Constants.ROOM_FETCH_NO_MANAGER_URL_PATH).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    final Type roomListType = new TypeToken<List<RoomWithSubscribeModel>>() {
                    }.getType();
                    List<RoomWithSubscribeModel> models = gson.fromJson(result, roomListType);
                    DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) usersModal.getComboxRoomModel().getModel();
                    comboBoxModel.removeAllElements();
                    models.forEach(model -> {
                        comboBoxModel.addElement(model);
                    });
                    usersModal.getComboxRoomModel().setRenderer(new MaiUtils.MaiComboxBoxCell());
                }
            });
        } catch (MaiException e) {
            System.out.println(e.getMessage());
        }

    }
}
