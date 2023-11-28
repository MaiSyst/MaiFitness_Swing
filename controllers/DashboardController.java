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
import fitnessapp.models.SubscribeModel;
import fitnessapp.utilities.API;
import fitnessapp.utilities.Constants;
import fitnessapp.utilities.MaiState;
import fitnessapp.utilities.MaiUtils;
import static fitnessapp.utilities.MaiUtils.toCapitalCase;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author orion90
 */
public final class DashboardController implements MaiState {

    private final JLabel numberSubscribeActive;
    private final JLabel numberSubscribeInActive;
    private final JLabel numberSubscribeStandard;
    private final JLabel numberSubscribeGold;
    private final JLabel numberSubscribPrime;
    private final JLabel montantAnnualSubscribe;
    private final JTextField inputSearchSubscribe;
    private final JComboBox<String> comboxStatusSubscribe;
    private final JTable table;
    private final MaiFetch fetch;
    private final Type listTypeSubscribe = new TypeToken<List<SubscribeModel>>() {
    }.getType();
    private final Gson gson = new Gson();
    private List<MaiState> states = new ArrayList<>();
    public List<SubscribeModel> dataList = new ArrayList<>();

    public DashboardController(
            final JLabel numberSubscribeActive,
            final JLabel numberSubscribeStandard,
            final JLabel numberSubscribeGold,
            final JLabel numberSubscribPrime,
            final JLabel montantAnnualSubscribe,
            final JButton btnRefreshSubscribe,
            final JTextField inputSearchSubscribe,
            final JComboBox<String> comboxStatusSubscribe,
            final JLabel numberSubscribeInActive,
            final JTable table, String token) {
        this.numberSubscribeActive = numberSubscribeActive;
        this.numberSubscribeStandard = numberSubscribeStandard;
        this.numberSubscribeGold = numberSubscribeGold;
        this.numberSubscribPrime = numberSubscribPrime;
        this.montantAnnualSubscribe = montantAnnualSubscribe;
        this.table = table;
        this.comboxStatusSubscribe = comboxStatusSubscribe;
        this.numberSubscribeInActive = numberSubscribeInActive;
        this.inputSearchSubscribe = inputSearchSubscribe;
        fetch = API.fetch(new Authorization(token));
        fetchSubscribes();
        btnRefreshSubscribe.addActionListener(l -> {
            fetchSubscribes();
            states.forEach(state -> state.updateState());
        });
        btnRefreshSubscribe.setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "resubscribe.svg"));
        inputSearchSubscribe.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Rechercher abonne par le nom ou prenom du client...");
        inputSearchSubscribe.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon(Constants.ICONS_PATH + "search.svg"));
        inputSearchSubscribe.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (inputSearchSubscribe.getText().isBlank()) {
                    insertToDataTable(dataList);
                } else {
                    searchSubscribeByName(inputSearchSubscribe.getText());
                }
            }

        });
        comboxStatusSubscribe.addItemListener(aListener -> {
            if (aListener.getStateChange() == ItemEvent.SELECTED) {
                var selected = aListener.getItem().toString();
                switch (selected.toLowerCase()) {
                    case "expirés" -> {
                        var result = dataList.stream().filter(data -> !data.isActive()).toList();
                        insertToDataTable(result);
                    }
                    case "actifs" -> {
                        var result = dataList.stream().filter(data -> data.isActive()).toList();
                        insertToDataTable(result);
                    }
                    default -> {
                        insertToDataTable(dataList);
                    }

                }
            }
        });
//        MaiUtils.realTimeFetchData(args->{
//            fetchSubscribes();
//            states.forEach(state->state.updateState());
//        });
    }

    private void changeContentCardSubscribeInfo() {
        var response = dataList.stream().filter(item -> item.isActive()).toList().size();
        var responseInactif = dataList.stream().filter(item -> !item.isActive()).toList().size();
        numberSubscribeActive.setText(response + " Abonnements actif");
        numberSubscribeInActive.setText(responseInactif + " Abonnements expirés");

    }

    private void changeContentCardSubscribeByTypeInfo() {
        var countGoldSubscribe = dataList.stream().filter(item -> item.subscription().type().toLowerCase().equals("gold")).toList().size();
        var countPrimeSubscribe = dataList.stream().filter(item -> item.subscription().type().toLowerCase().equals("prime")).toList().size();
        var countStandardSubscribe = dataList.stream().filter(item -> item.subscription().type().toLowerCase().equals("standard")).toList().size();
        numberSubscribPrime.setText("* " + countPrimeSubscribe + " prime abonnés");
        numberSubscribeStandard.setText("* " + countStandardSubscribe + " standard abonnés");
        numberSubscribeGold.setText("* " + countGoldSubscribe + " gold abonnés");
    }

    private void calculTotalMontant() {
        AtomicLong sum = new AtomicLong();
        dataList.forEach(item -> sum.addAndGet((long) item.subscription().price()));
        montantAnnualSubscribe.setText(MaiUtils.numberFormat(sum.get()) + " FCFA");
    }

    private void fetchSubscribes() {
        try {
            fetch.get(Constants.SUBSCRIBE_FETCH_URL_PATH).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    dataList = gson.fromJson(result, listTypeSubscribe);
                    insertToDataTable(dataList);
                    changeContentCardSubscribeByTypeInfo();
                    changeContentCardSubscribeInfo();
                    calculTotalMontant();
                }
            });
        } catch (MaiException e) {
            Logger.getLogger(DashboardController.class.getName(), e.getMessage());
        }
    }

    private void insertToDataTable(List<SubscribeModel> data) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        data.forEach(item -> tableModel.addRow(new Object[]{
            item.subscribeId(),
            item.dateStart(),
            item.dateEnd(),
            toCapitalCase(item.customer().firstName()) + " " + item.customer().lastName().toUpperCase(),
            item.subscription().type(),
            item.isActive()
        }));
    }

    private void searchSubscribeByName(String name) {
        var result = dataList.stream().filter(data
                -> data.customer().firstName().toLowerCase().contains(name)
                || data.customer().lastName().toLowerCase().contains(name)
        ).toList();
        insertToDataTable(result);
    }

    @Override
    public void updateState(Object... args) {
        fetchSubscribes();
    }
    
    public void subscribe(MaiState... subscribes) {
        states.addAll(Arrays.asList(subscribes));
    }

    public List<SubscribeModel> getDataList() {
        return dataList;
    }

}
