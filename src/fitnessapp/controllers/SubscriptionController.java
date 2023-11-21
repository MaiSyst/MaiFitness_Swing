/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.controllers;

import com.formdev.flatlaf.FlatClientProperties;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.maisyst.MaiFetch;
import com.maisyst.exceptions.MaiException;
import com.maisyst.utils.Authorization;
import com.maisyst.utils.enums.ResponseStatusCode;
import fitnessapp.models.SubscriptionModel;
import fitnessapp.utilities.API;
import fitnessapp.utilities.Constants;
import java.lang.reflect.Type;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author orion90
 */
public class SubscriptionController {

    private final JPanel cardStandard;
    private final JPanel cardPrime;
    private final JPanel cardGold;
    private final JFrame parent;
    private final JLabel standardMonth;
    private final JLabel standardPrice;
    private final JLabel primeMonth;
    private final JLabel primePrice;
    private final JLabel goldMonth;
    private final JLabel goldPrice;
    private final MaiFetch fetch;
    private final String flatStyle = FlatClientProperties.STYLE;
    private final String panelStyle = "arc:20";
    private final Type listSubscription = new TypeToken<List<SubscriptionModel>>() {
    }.getType();

    public SubscriptionController(final JFrame parent, final JPanel cardStandard,
            final JPanel cardPrime, final JPanel cardGold,
            JLabel standardMonth,
            JLabel standardPrice,
            JLabel primeMonth,
            JLabel primePrice,
            JLabel goldMonth,
            JLabel goldPrice,
            String token) {
        this.cardGold = cardGold;
        this.cardPrime = cardPrime;
        this.cardStandard = cardStandard;
        this.parent = parent;
        this.standardMonth = standardMonth;
        this.standardPrice = standardPrice;
        this.primeMonth = primeMonth;
        this.primePrice = primePrice;
        this.goldMonth = goldMonth;
        this.goldPrice = goldPrice;
        this.fetch = API.fetch(new Authorization(token));
        cardGold.putClientProperty(flatStyle, panelStyle);
        cardPrime.putClientProperty(flatStyle, panelStyle);
        cardStandard.putClientProperty(flatStyle, panelStyle);
        freshDataTable();
    }

    private void freshDataTable() {
        try {
            fetch.get(Constants.SUBSCRIPTION_FETCH_URL_PATH).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    Gson gson = new Gson();
                    List<SubscriptionModel> models = gson.fromJson(result, listSubscription);
                    models.forEach(model ->loadElementToCardComponent(model.type(),model.price(),model.label()));
                }
            });
        } catch (MaiException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadElementToCardComponent(String type, double price, String month) {
        switch (type) {
            case "PRIME"->{
                this.primeMonth.setText(month);
                this.primePrice.setText(String.valueOf(price)+"FCFA");
            }
            case "STANDARD"->{
                this.standardMonth.setText(month);
                this.standardPrice.setText(String.valueOf(price)+"FCFA");
            }
            default->{
                this.goldMonth.setText(month);
                this.goldPrice.setText(String.valueOf(price)+"FCFA");
            }
        }
    }
}
