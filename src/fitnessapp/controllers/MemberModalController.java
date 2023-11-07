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
import fitnessapp.models.ActivityModel;
import fitnessapp.models.SubscriptionModel;
import fitnessapp.screens.MemberModal;
import fitnessapp.utilities.Constants;
import fitnessapp.utilities.MaiFunctionCall;
import fitnessapp.utilities.MaiUtils;
import fitnessapp.utilities.MaiUtils.MaiComboxBoxCell;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import raven.toast.Notifications;

/**
 *
 * @author orion90
 */
public class MemberModalController {

    private final MemberModal modal;
    private final MaiFetch fetch;
    private final Gson gson = new Gson();
    private final MaiFunctionCall functionCall;

    public MemberModalController(final MaiFetch fetch,final MaiFunctionCall functionCall) {
        this.modal = new MemberModal();
        this.fetch = fetch;
        this.functionCall=functionCall;
        modal.getBtnClose().addActionListener(l -> modal.dispose());
        modal.getBtnAdded().addActionListener(l->addNewMember());
        fetchActivities();
        fetchSubscription();
    }
    public MemberModalController(final MaiFetch fetch,String memberId,String firstName,String lastName,String birthday,String address,final MaiFunctionCall functionCall) {
        this.modal = new MemberModal();
        this.fetch = fetch;
        this.functionCall=functionCall;
        modal.getBtnClose().addActionListener(l -> modal.dispose());
        modal.getBtnAdded().addActionListener(l->editMember(memberId));
        modal.getTxtFirstname().setText(firstName);
        modal.getTxtLastname().setText(lastName);
        modal.getTxtAddress().setText(address);
        modal.getDatePicker().setText(birthday);
        fetchActivities();
        fetchSubscription();
    }

    private final void fetchActivities() {
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
            System.out.println(e.getMessage());
        }
    }
    private void clearInputText(){
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
        if (firstName.isBlank() || lastName.isBlank() || birthDay.isBlank() || address.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Les champs ne peut être accepté");
        } else {
            try {
                Map<String, Object> body = new HashMap<>();
                body.put("firstName", firstName);
                body.put("lastName", lastName);
                body.put("yearOfBirth", birthDay);
                body.put("address", address);
                fetch.post(
                        Constants.CUSTOMER_ADD_URL_PATH+subscription.subscriptionId()+"/"+activity.activityId(),
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
                Logger.getLogger(MemberModalController.class.getName(), e.getMessage());
            }
        }
    }
    
    private void editMember(String memberId) {
        var firstName = modal.getTxtFirstname().getText();
        var lastName = modal.getTxtLastname().getText();
        var birthDay = modal.getDatePicker().getText();
        var address = modal.getTxtAddress().getText();
        var activity = (ActivityModel) modal.getComboxActivity().getSelectedItem();
        var subscription = (SubscriptionModel) modal.getComboxSubscription().getSelectedItem();
        if (firstName.isBlank() || lastName.isBlank() || birthDay.isBlank() || address.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Les champs ne peut être accepté");
        } else {
            try {
                Map<String, Object> body = new HashMap<>();
                body.put("firstName", firstName);
                body.put("lastName", lastName);
                body.put("yearOfBirth", birthDay);
                body.put("address", address);
                fetch.post(
                        Constants.CUSTOMER_UPDATE_URL_PATH+subscription.subscriptionId()+"/"+activity.activityId()+"/"+memberId,
                        body).then((result, status) -> {
                    if (status == ResponseStatusCode.OK) {
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Un membre à été mise-à-jour.");
                        functionCall.invoked();
                        clearInputText();
                    } else {
                        Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, result);

                    }
                });
            } catch (MaiException e) {
                Logger.getLogger(MemberModalController.class.getName(), e.getMessage());
            }
        }
    }

    private final void fetchSubscription() {
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
            System.out.println(e.getMessage());
        }
    }

    public void show(JFrame parent) {
        modal.setVisible(true);
    }

}
