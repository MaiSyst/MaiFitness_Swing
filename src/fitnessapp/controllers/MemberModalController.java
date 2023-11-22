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
    private final MaiState maiState;
    private static final Logger logger = Logger.getLogger(MemberModalController.class.getName());

    public MemberModalController(final MaiFetch fetch, 
            final MaiFunctionCall functionCall, 
            final MaiState maiState, final boolean isAdmin) {
        this.modal = new MemberModal();
        this.fetch = fetch;
        this.functionCall = functionCall;
        this.maiState = maiState;
        modal.getBtnClose().addActionListener(l -> modal.dispose());
        modal.getBtnAdded().addActionListener(l -> addNewMember());
        modal.getBtnAdded().setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "plus.svg"));
        fetchActivities();
        fetchSubscription();
        if (isAdmin) {
            fetchRoom(); 
        }else{
            modal.getCenter().remove(modal.getRoomContainer());
        }
    }

    public MemberModalController(
            final MaiFetch fetch, final String memberId,
            final String firstName, final String lastName, final String birthday,
            final String address, final MaiFunctionCall functionCall,
            final MaiState maiState, final boolean isAdmin) {
        this.modal = new MemberModal();
        this.fetch = fetch;
        this.functionCall = functionCall;
        this.maiState = maiState;
        modal.getBtnClose().addActionListener(l -> modal.dispose());
        modal.getBtnAdded().addActionListener(l -> editMember(memberId, isAdmin));
        modal.getTxtFirstname().setText(firstName);
        modal.getTxtLastname().setText(lastName);
        modal.getTxtAddress().setText(address);
        modal.getDatePicker().setText(birthday);
        modal.getBtnAdded().setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "edit.svg"));
        modal.getBtnAdded().setText("Modifier");
        modal.getTitleMembre().setText("Modifier un membre");
        modal.getCenter().remove(modal.getActivityContainer());
        modal.getCenter().remove(modal.getSubscContainer());
        modal.getCenter().remove(modal.getRoomContainer()); 
        modal.getCenter().setLayout(new GridLayout(4,0));
        
        modal.getFooter().setPreferredSize(new Dimension(modal.getWidth(),200));
        modal.getHeader().setPreferredSize(new Dimension(modal.getWidth(),200));
        modal.setSize(new Dimension(modal.getWidth(),400));
        modal.setPreferredSize(new Dimension(modal.getWidth(),400));
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

    private void addNewMember() {
        var firstName = modal.getTxtFirstname().getText();
        var lastName = modal.getTxtLastname().getText();
        var birthDay = modal.getDatePicker().getText();
        var address = modal.getTxtAddress().getText();
        var activity = (ActivityModel) modal.getComboxActivity().getSelectedItem();
        var subscription = (SubscriptionModel) modal.getComboxSubscription().getSelectedItem();
        var room = (RoomWithSubscribeModel) modal.getComboxRoomModel().getSelectedItem();
        if (firstName.isBlank() || lastName.isBlank() || birthDay.isBlank()
                || address.isBlank() || room == null || activity == null || subscription == null) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Les champs ne peut être accepté");
        } else {
            try {
                Map<String, Object> body = new HashMap<>();
                body.put("firstName", firstName);
                body.put("lastName", lastName);
                body.put("yearOfBirth", birthDay);
                body.put("address", address);
                fetch.post(
                        Constants.CUSTOMER_ADD_URL_PATH + subscription.type() + "/" + activity.activityId() + "/" + room.roomId(),
                        body).then((result, status) -> {
                            if (status == ResponseStatusCode.OK) {
                                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Un membre à été ajouté.");
                                functionCall.invoked();
                                maiState.updateState();
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

    private void editMember(String memberId, boolean isWithRoom) {
        var firstName = modal.getTxtFirstname().getText();
        var lastName = modal.getTxtLastname().getText();
        var birthDay = modal.getDatePicker().getText();
        var address = modal.getTxtAddress().getText();
        var roomModel = (RoomWithSubscribeModel) modal.getComboxRoomModel().getSelectedItem();
        var roomPath = isWithRoom ? roomModel.roomId() : "user";
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
                        Constants.CUSTOMER_UPDATE_URL_PATH + "/" + roomPath + "/" + memberId,
                        body).then((result, status) -> {
                            if (status == ResponseStatusCode.OK) {
                                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Un membre à été mise-à-jour.");
                                functionCall.invoked();
                                if(maiState!=null){
                                    maiState.updateState();
                                }
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
