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
import fitnessapp.models.SubscriptionModel;
import fitnessapp.screens.ResubscribeModal;
import fitnessapp.utilities.Constants;
import fitnessapp.utilities.MaiFunctionCall;
import fitnessapp.utilities.MaiUtils;
import java.lang.reflect.Type;
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
public class ResubscribeController {

    private final ResubscribeModal modal;
    private static final Logger logger=Logger.getLogger(ResubscribeController.class.getName());
    private final MaiFunctionCall callback;
    public ResubscribeController(final JFrame parent, 
            final MaiFetch fetch, final String identify, 
            final String firstName, final String lastName,final MaiFunctionCall callback) {
        modal = new ResubscribeModal(parent, true);
        modal.getTxtFirstName().setText(firstName);
        modal.getTxtLastName().setText(lastName);
        modal.getTxtIdentify().setText(identify);
        this.callback=callback;
        modal.getBtnClose().addActionListener(l -> modal.dispose());
        modal.getBtnClose().setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "close.svg"));
        modal.getBtnUpdateSubscription().addActionListener(l -> updateSubscription(identify, fetch));
        fetchSubscription(fetch);
    }
   

    private void fetchSubscription(final MaiFetch fetch) {
        try {
            fetch.get(Constants.SUBSCRIPTION_FETCH_URL_PATH).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    final Type subscriptionListType = new TypeToken<List<SubscriptionModel>>() {
                    }.getType();
                    Gson gson = new Gson();
                    List<SubscriptionModel> models = gson.fromJson(result, subscriptionListType);
                    DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) modal.getComboxSubscription().getModel();
                    comboBoxModel.removeAllElements();
                    models.forEach(model -> {
                        comboBoxModel.addElement(model);
                    });
                    modal.getComboxSubscription().setRenderer(new MaiUtils.MaiComboxBoxCell());
                }
            });
        } catch (MaiException e) {
           logger.info( e.getMessage());
        }
    }

    public void show() {
        modal.setVisible(true);
    }

    private void updateSubscription(final String identify, final MaiFetch fetch) {
        try {
            Map<String, Object> body = new HashMap<>();
            var subscType = (SubscriptionModel) modal.getComboxSubscription().getSelectedItem();
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
           
            logger.info(e.getMessage());
        }
    }
}
