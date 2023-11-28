/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.controllers;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.maisyst.MaiFetch;
import com.maisyst.exceptions.MaiException;
import com.maisyst.utils.enums.ResponseStatusCode;
import fitnessapp.components.PopupValidateCustomer;
import fitnessapp.models.SubscriptionModel;
import fitnessapp.utilities.Constants;
import fitnessapp.utilities.MaiFunctionCall;
import fitnessapp.utilities.MaiUtils;
import java.awt.Color;
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
    private final MaiFunctionCall callback;
    public PopupValidateCustomerController(final JFrame parent,final MaiFunctionCall callback) {
        modal = new PopupValidateCustomer(parent, true);
        modal.getBtnClose().addActionListener(l->modal.dispose());
        modal.getBtnClose().setIcon(new FlatSVGIcon(Constants.ICONS_PATH+"close.svg"));
        modal.getBtnRescubscribe().setIcon(new FlatSVGIcon(Constants.ICONS_PATH+"resubscribe.svg"));
        this.callback=callback;
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
        modal.getFirstName().setText(firstName.toUpperCase());
        modal.getLastName().setText(lastName.toUpperCase());
        modal.getIdentityCustomer().setText(identity);
       
        modal.getDateEnd().setText(dateEnd);
        modal.getDateStart().setText(dateStart);
        modal.getTypeSubscription().setText(typeSubscription);
        modal.getValidity().setText(messageValidating);
        if (isValid) {
            modal.getCenter().remove(modal.getComboSubscription());
            modal.getCenter().remove(modal.getLblSubscription());
            modal.getContainer().remove(modal.getFooter());
            modal.getIconValidate().setVisible(true);
             modal.getExpirateIcon().setVisible(false);
        } else {
            final DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) modal.getComboSubscription().getModel();
            comboBoxModel.removeAllElements();
            subscriptionModels.forEach(model -> comboBoxModel.addElement(model));
            modal.getComboSubscription().setRenderer(new MaiUtils.MaiComboxBoxCell());
            modal.getBtnRescubscribe().addActionListener(l->updateSubscription(identity,fetch));
             modal.getValidity().setForeground(Color.red);
             modal.getIconValidate().setVisible(false);
             modal.getExpirateIcon().setVisible(true);
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
                    callback.invoked();
                } else {
                    Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Erreur de Réabonnement");
                }
            });
        } catch (MaiException e) {
           
            Logger.getLogger(PopupValidateCustomerController.class.getName()).info(e.getMessage());
        }
    }
}
