/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.maisyst.MaiFetch;
import com.maisyst.date.MaiDate;
import com.maisyst.exceptions.MaiException;
import com.maisyst.utils.Authorization;
import com.maisyst.utils.enums.ResponseStatusCode;
import fitnessapp.models.ActivityModel;
import fitnessapp.models.SubscriptionModel;
import fitnessapp.utilities.API;
import fitnessapp.utilities.Constants;
import fitnessapp.utilities.MaiState;
import fitnessapp.utilities.MaiUtils;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import raven.toast.Notifications;

/**
 *
 * @author maisyst
 */
public class PublicDashboardController {

    private final JTextField inputCheckCustomer;
    private final JButton checkCustomerValidate;
    private MaiState maiState;
    private final JTextField firstName;
    private final JTextField lastName;
    private final JTextField birthdate;
    private final JTextField address;
    private final JComboBox<ActivityModel> activities;
    private final JComboBox<SubscriptionModel> subscription;
    private final JTextField priceMontant;
    private final JLabel msgFirstName;
    private final JLabel msgLastName;
    private final JLabel msgAddress;
    private final JButton saveMember;
    private final JFrame parent;
    private final MaiFetch fetch;
    private final Gson gson = new Gson();
    private final String roomId;
    public PublicDashboardController(
            final JFrame parent,
            final JTextField inputCheckCustomer,
            final JButton checkCustomerValidate,
            final JTextField firstName,
            final JTextField lastName,
            final JTextField birthdate,
            final JTextField address,
            final JComboBox<ActivityModel> activities,
            final JComboBox<SubscriptionModel> subscription,
            final JTextField priceMontant,
            final JLabel remainPrice,
            final JLabel priceSubscription,
            final JButton saveMember,
            final JLabel msgFirstName,
            final JLabel msgLastName,
            final JLabel msgBirthdate,
            final JLabel msgAddress,
            final JLabel msgMontant,
            String token,
            String roomId
    ) {
        this.checkCustomerValidate = checkCustomerValidate;
        this.inputCheckCustomer = inputCheckCustomer;
        this.firstName = firstName;
        this.lastName = lastName;
        this.activities = activities;
        this.address = address;
        this.birthdate = birthdate;
        this.subscription = subscription;
        this.priceMontant = priceMontant;
        this.saveMember = saveMember;
        this.msgAddress = msgAddress;
        this.msgFirstName = msgFirstName;
        this.msgLastName = msgLastName;
        this.parent = parent;
        this.roomId = roomId;
        this.fetch = API.fetch(new Authorization(token));
        inputCheckCustomer.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    System.out.println("Enter");
                }
            }

        });
        fetchSubscription();
        fetchActivities();
        saveMember.addActionListener(l -> addNewMember());
        var subsDefault = (SubscriptionModel) subscription.getSelectedItem();
        if (subsDefault != null) {

            priceSubscription.setText(subsDefault.price()+" FCFA");
        }
        subscription.addItemListener(itemListener -> {
            if (itemListener.getStateChange() == ItemEvent.SELECTED) {
                var subs = (SubscriptionModel) itemListener.getItem();
                priceSubscription.setText(subs.price()+" FCFA");
            }
        });
        priceMontant.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                }
                var price = priceMontant.getText();
                if (!price.isBlank()) {
                    var substraction = Double.parseDouble(price) - Double.parseDouble(priceSubscription.getText().split(" ")[0]);
                    if (substraction >= 0) {
                        remainPrice.setText(substraction + " FCFA");
                        msgMontant.setText("");
                    }else{
                        msgMontant.setText("Montant insuffisant.");
                        remainPrice.setText(0 + " FCFA");
                    }
                }
            }

        });
    }

    private void addNewMember() {
        if (firstName.getText().isBlank() || lastName.getText().isBlank()
                || address.getText().isBlank() || priceMontant.getText().isBlank()) {
            if (firstName.getText().isBlank()) {
                msgFirstName.setText("Prémon ne peut être vide.");
            } else {
                msgFirstName.setText("");
            }
            if (lastName.getText().isBlank()) {
                msgLastName.setText("Nom ne peut être vide.");
            } else {
                msgLastName.setText("");
            }
            if (address.getText().isBlank()) {
                msgAddress.setText("L'adresse ne peut être vide.");
            } else {
                msgAddress.setText("");
            }
            if (priceMontant.getText().isBlank()) {
                JOptionPane.showMessageDialog(parent, "S'il vous plait entrer un montant.");
            }
        } else {
            var year = Integer.parseInt(birthdate.getText().split("-")[0]);
            if (MaiDate.currentYears() - year < 5) {
                JOptionPane.showMessageDialog(parent, "Désolé l'âge doit être supérieur à 5 ans");
            } else {
                try {
                    Map<String, Object> body = new HashMap<>();
                    body.put("firstName", firstName.getText());
                    body.put("lastName", lastName.getText());
                    body.put("yearOfBirth", birthdate.getText());
                    body.put("address", address.getText());
                    var activity = (ActivityModel) activities.getSelectedItem();
                    var subs = (SubscriptionModel) subscription.getSelectedItem();
                    fetch.post(
                            Constants.CUSTOMER_ADD_URL_PATH + subs.type() + "/" + activity.activityId() + "/" + roomId,
                            body).then((result, status) -> {
                                if (status == ResponseStatusCode.OK) {
                                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Un membre à été ajouté.");
                                    maiState.updateState();
                                    clearInputText();
                                } else {
                                    Notifications.getInstance().show(Notifications.Type.ERROR, result);

                                }
                            });
                } catch (MaiException e) {
                    Logger.getLogger(MemberModalController.class.getName(), e.getMessage());
                }
            }
        }
    }

    private void clearInputText() {
        firstName.setText("");
        lastName.setText("");
        birthdate.setText("");
        address.setText("");
        priceMontant.setText("");

    }

    private void fetchSubscription() {
        try {
            fetch.get(Constants.SUBSCRIPTION_FETCH_URL_PATH).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    final Type subscriptionListType = new TypeToken<List<SubscriptionModel>>() {
                    }.getType();
                    List<SubscriptionModel> models = gson.fromJson(result, subscriptionListType);
                    DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) subscription.getModel();
                    comboBoxModel.removeAllElements();
                    models.forEach(model -> comboBoxModel.addElement(model));
                    subscription.setRenderer(new MaiUtils.MaiComboxBoxCell());
                }
            });
        } catch (MaiException e) {
            System.out.println(e.getMessage());
        }
    }

    private void fetchActivities() {
        try {
            fetch.get(Constants.ACTIVITY_FETCH_URL_PATH).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    final Type activityListType = new TypeToken<List<ActivityModel>>() {
                    }.getType();
                    List<ActivityModel> models = gson.fromJson(result, activityListType);
                    DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) activities.getModel();
                    comboBoxModel.removeAllElements();
                    models.forEach(model -> comboBoxModel.addElement(model));
                    activities.setRenderer(new MaiUtils.MaiComboxBoxCell());
                }
            });
        } catch (MaiException e) {
            System.out.println(e.getMessage());
        }
    }

    public void subscribe(MaiState maiState) {
        this.maiState = maiState;
    }
}
