/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.controllers;

import com.maisyst.MaiFetch;
import com.maisyst.exceptions.MaiException;
import com.maisyst.utils.enums.ResponseStatusCode;
import fitnessapp.components.PopupValidateCustomer;
import fitnessapp.models.SubscriptionModel;
import fitnessapp.utilities.Constants;
import fitnessapp.utilities.MaiUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import raven.toast.Notifications;

/**
 *
 * @author maisyst
 */
public class PopupValidateCustomerController {

    private final PopupValidateCustomer modal;

    public PopupValidateCustomerController(final JFrame parent) {
        modal = new PopupValidateCustomer(parent, true);
        modal.getBtnClose().addActionListener(l->modal.dispose());
    }

    public void show(final String identity, 
            final String firstName, 
            final String lastName,
            final String dateStart,
            final String dateEnd,
            final String messageValidating,
            final String typeSubscription,
            final boolean isValid, 
            final List<SubscriptionModel> subscriptionModels,
            final MaiFetch fetch) {
        modal.getFirstName().setText(firstName);
        modal.getLastName().setText(lastName);
        modal.getIdentityCustomer().setText(identity);
        modal.getValidity().setText(messageValidating);
        modal.getDateEnd().setText(dateEnd);
        modal.getDateStart().setText(dateStart);
        modal.getTypeSubscription().setText(typeSubscription);
        if (isValid) {
            modal.getCenter().remove(modal.getComboSubscription());
            modal.getCenter().remove(modal.getLblSubscription());
            
            modal.getContainer().remove(modal.getFooter());
        } else {
            final DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) modal.getComboSubscription().getModel();
            comboBoxModel.removeAllElements();
            subscriptionModels.forEach(model -> comboBoxModel.addElement(model));
            modal.getComboSubscription().setRenderer(new MaiUtils.MaiComboxBoxCell());
            modal.getBtnRescubscribe().addActionListener(l->updateSubscription(identity,fetch));
            
        }
        modal.setVisible(true);

    }
    private void updateSubscription(final String identify, final MaiFetch fetch) {
        try {
            Map<String, Object> body = new HashMap<>();
            var subscType = (SubscriptionModel) modal.getComboSubscription().getSelectedItem();
            body.put("arg", subscType.subscriptionId());

            fetch.put(Constants.CUSTOMER_UPDATE_SUBSCRIPTION_URL_PATH + "/" + identify, body).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Réabonnement effectué.");
                    modal.dispose();
                } else {
                    Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Erreur de Réabonnement");
                }
            });
        } catch (MaiException e) {
           
            Logger.getLogger(PopupValidateCustomerController.class.getName()).info(e.getMessage());
        }
    }
}
