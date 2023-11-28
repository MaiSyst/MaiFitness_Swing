/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.controllers;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.maisyst.MaiFetch;
import com.maisyst.exceptions.MaiException;
import com.maisyst.utils.enums.ResponseStatusCode;
import fitnessapp.models.ActivityModel;
import fitnessapp.models.RoomWithSubscribeModel;
import fitnessapp.models.SubscriptionModel;
import fitnessapp.screens.MemberModal;
import fitnessapp.utilities.Constants;
import fitnessapp.utilities.MaiFunctionCall;
import fitnessapp.utilities.MaiState;
import fitnessapp.utilities.MaiUtils;
import fitnessapp.utilities.MaiUtils.MaiComboxBoxCell;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import raven.toast.Notifications;

/**
 *
 * @author orion90
 */
public final class MemberModalController {

    private final MemberModal modal;
    private final MaiFetch fetch;
    private final Gson gson = new Gson();
    private final MaiFunctionCall functionCall;
    private static final Logger logger = Logger.getLogger(MemberModalController.class.getName());
    private String subscriptionType;

    public MemberModalController(final MaiFetch fetch,
            final MaiFunctionCall functionCall,
            final boolean isAdmin, final String roomId) {
        this.modal = new MemberModal();
        this.fetch = fetch;
        this.functionCall = functionCall;
        modal.getBtnClose().addActionListener(l -> modal.dispose());

        modal.getBtnAdded().setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "plus.svg"));
        fetchActivities();
        fetchSubscription();
        if (isAdmin) {
            fetchRoom();
            modal.getBtnAdded().addActionListener(l -> {
                var room = (RoomWithSubscribeModel) modal.getComboxRoomModel().getSelectedItem();
                addNewMember(room.roomId());
            });
        } else {
            modal.getCenter().remove(modal.getRoomContainer());
            modal.getBtnAdded().addActionListener(l -> addNewMember(roomId));
        }
    }

    public MemberModalController(
            final MaiFetch fetch, final String memberId,
            final String firstName, final String lastName, final String birthday,
            final String address, final String roomName, 
            final String subscriptionType, final MaiFunctionCall functionCall,
            final boolean isAdmin, final String roomId) {
        this.modal = new MemberModal();
        this.fetch = fetch;
        this.functionCall = functionCall;
        this.subscriptionType = subscriptionType;
        modal.getBtnClose().addActionListener(l -> modal.dispose());

        modal.getTxtFirstname().setText(firstName);
        modal.getTxtLastname().setText(lastName);
        modal.getTxtAddress().setText(address);
        modal.getDatePicker().setText(birthday);
        modal.getBtnAdded().setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "edit.svg"));
        modal.getBtnAdded().setText("Modifier");
        modal.getTitleMembre().setText("Modifier un membre");
        modal.getCenter().remove(modal.getActivityContainer());

        if (!isAdmin) {

            modal.getCenter().remove(modal.getSubscContainer());
            modal.getCenter().remove(modal.getRoomContainer());
            modal.getCenter().setLayout(new GridLayout(4, 0));
            modal.getCenter().remove(modal.getRoomContainer());
            modal.getFooter().setPreferredSize(new Dimension(modal.getWidth(), 200));
            modal.getHeader().setPreferredSize(new Dimension(modal.getWidth(), 200));
            modal.setSize(new Dimension(modal.getWidth(), 400));
            modal.setPreferredSize(new Dimension(modal.getWidth(), 400));
            modal.getBtnAdded().addActionListener(
                    l-> editMember(memberId, isAdmin, roomId));
        } else {
            fetchSubscription();
            fetchRoom();
            for (var i = 0; i < modal.getComboxRoomModel().getItemCount(); i++) {
                if (modal.getComboxRoomModel().getItemAt(i).roomName().toLowerCase()
                        .equals(roomName.toLowerCase())) {
                    modal.getComboxRoomModel().setSelectedIndex(i);
                    break;
                }
            }
            for (var i = 0; i < modal.getComboxSubscription().getItemCount(); i++) {
                if (modal.getComboxSubscription().getItemAt(i).type().toLowerCase()
                        .equals(subscriptionType.toLowerCase())) {
                    modal.getComboxSubscription().setSelectedIndex(i);
                    break;
                }
            }
            modal.getBtnAdded().addActionListener(l -> {
                var roomModel = (RoomWithSubscribeModel) modal.getComboxRoomModel().getSelectedItem();
                editMember(memberId, isAdmin, roomModel.roomId());
            });
        }

    }

    private void fetchActivities() {
        try {
            fetch.get(Constants.ACTIVITY_FETCH_URL_PATH).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    final Type activityListType = new TypeToken<List<ActivityModel>>() {
                    }.getType();
                    List<ActivityModel> models = gson.fromJson(result, activityListType);
                    DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) modal.getComboxActivity().getModel();
                    comboBoxModel.removeAllElements();
                    models.forEach(model -> {
                        comboBoxModel.addElement(model);
                    });
                    modal.getComboxActivity().setRenderer(new MaiComboxBoxCell());
                }
            });
        } catch (MaiException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    private void clearInputText() {
        modal.getTxtFirstname().setText("");
        modal.getTxtLastname().setText("");
        modal.getTxtAddress().setText("");
    }

    private void addNewMember(String roomId) {
        var firstName = modal.getTxtFirstname().getText().toLowerCase();
        var lastName = modal.getTxtLastname().getText().toLowerCase();
        var birthDay = modal.getDatePicker().getText();
        var address = modal.getTxtAddress().getText().toLowerCase();
        var activity = (ActivityModel) modal.getComboxActivity().getSelectedItem();
        var subscription = (SubscriptionModel) modal.getComboxSubscription().getSelectedItem();

        if (firstName.isBlank() || lastName.isBlank() || birthDay.isBlank()
                || address.isBlank() || roomId == null || activity == null || subscription == null) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Les champs ne peut être accepté");
        } else {
            try {
                Map<String, Object> body = new HashMap<>();
                body.put("firstName", firstName);
                body.put("lastName", lastName);
                body.put("yearOfBirth", birthDay);
                body.put("address", address);
                fetch.post(
                        Constants.CUSTOMER_ADD_URL_PATH + subscription.type() + "/" + activity.activityId() + "/" + roomId,
                        body).then((result, status) -> {
                            if (status == ResponseStatusCode.OK) {
                                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Un membre à été ajouté.");
                                functionCall.invoked();
                                clearInputText();
                            } else {
                                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, result);

                            }
                        });
            } catch (MaiException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
    }

    private void editMember(String memberId, boolean isAdmin, String roomId) {
        var firstName = modal.getTxtFirstname().getText().toLowerCase();
        var lastName = modal.getTxtLastname().getText().toLowerCase();
        var birthDay = modal.getDatePicker().getText();
        var address = modal.getTxtAddress().getText().toLowerCase();

        var subscription = (SubscriptionModel) modal.getComboxSubscription().getSelectedItem();

        var typeSubscription = isAdmin ? subscription.type() : subscriptionType;
        if (firstName.isBlank() || lastName.isBlank() || birthDay.isBlank() || address.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Les champs ne peut être accepté");
        } else {
            try {
                Map<String, Object> body = new HashMap<>();
                body.put("firstName", firstName);
                body.put("lastName", lastName);
                body.put("yearOfBirth", birthDay);
                body.put("address", address);
                fetch.put(
                        Constants.CUSTOMER_UPDATE_URL_PATH + "/" + typeSubscription + "/" + roomId + "/" + memberId,
                        body).then((result, status) -> {
                            if (status == ResponseStatusCode.OK) {
                                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Un membre à été mise-à-jour.");
                                functionCall.invoked();
                                clearInputText();
                                modal.dispose();
                            } else {
                                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, result);

                            }
                        });
            } catch (MaiException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
    }

    private void fetchSubscription() {
        try {
            fetch.get(Constants.SUBSCRIPTION_FETCH_URL_PATH).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    final Type subscriptionListType = new TypeToken<List<SubscriptionModel>>() {
                    }.getType();
                    List<SubscriptionModel> models = gson.fromJson(result, subscriptionListType);
                    DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) modal.getComboxSubscription().getModel();
                    comboBoxModel.removeAllElements();
                    models.forEach(model -> {
                        comboBoxModel.addElement(model);
                    });
                    modal.getComboxSubscription().setRenderer(new MaiComboxBoxCell());
                }
            });
        } catch (MaiException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    private void fetchRoom() {
        try {
            fetch.get(Constants.ROOM_FETCH_URL_PATH).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    final Type roomListType = new TypeToken<List<RoomWithSubscribeModel>>() {
                    }.getType();
                    List<RoomWithSubscribeModel> models = gson.fromJson(result, roomListType);
                    DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) modal.getComboxRoomModel().getModel();
                    comboBoxModel.removeAllElements();
                    models.forEach(model -> comboBoxModel.addElement(model));
                    modal.getComboxRoomModel().setRenderer(new MaiUtils.MaiComboxBoxCell());
                }
            });
        } catch (MaiException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }

    }

    public void show() {
        modal.setVisible(true);
    }

}
