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
import com.maisyst.utils.Authorization;
import com.maisyst.utils.enums.ResponseStatusCode;
import fitnessapp.models.SubscribeModel;
import fitnessapp.utilities.API;
import fitnessapp.utilities.Constants;
import fitnessapp.utilities.MaiState;
import fitnessapp.utilities.MaiUtils;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author orion90
 */
public final class DashboardController implements MaiState{
    private final JLabel numberSubscribeActive;
    private final JLabel numberSubscribeStandard;
    private final JLabel numberSubscribeGold;
    private final JLabel numberSubscribPrime;
    private final JLabel montantAnnualSubscribe;
    private final JTable table;
    private final MaiFetch fetch;
    private final Type listTypeSubscribe=new TypeToken<List<SubscribeModel>>(){}.getType();
    private final Gson gson=new Gson();
    public List<SubscribeModel>dataList=new ArrayList<>();
    public DashboardController(final JLabel numberSubscribeActive, final JLabel numberSubscribeStandard,
            final JLabel numberSubscribeGold, final JLabel numberSubscribPrime, 
            final JLabel montantAnnualSubscribe,final JButton btnRefreshSubscribe, final JTable table,String token) {
        this.numberSubscribeActive = numberSubscribeActive;
        this.numberSubscribeStandard = numberSubscribeStandard;
        this.numberSubscribeGold = numberSubscribeGold;
        this.numberSubscribPrime = numberSubscribPrime;
        this.montantAnnualSubscribe = montantAnnualSubscribe;
        this.table = table;
        fetch=API.fetch(new Authorization(token));
        fetchSubscribes();
        btnRefreshSubscribe.addActionListener(l->fetchSubscribes());
        btnRefreshSubscribe.setIcon(new FlatSVGIcon(Constants.ICONS_PATH+"resubscribe.svg"));
    }
    private void changeContentCardSubscribeInfo(){
       var response=dataList.stream().filter(item->item.isActive()).toList().size();
       numberSubscribeActive.setText(response+" Abonnés actif");
    }
    private void changeContentCardSubscribeByTypeInfo(){
        var countGoldSubscribe=dataList.stream().filter(item->item.subscription().type().toLowerCase().equals("gold")).toList().size();
        var countPrimeSubscribe=dataList.stream().filter(item->item.subscription().type().toLowerCase().equals("prime")).toList().size();
        var countStandardSubscribe=dataList.stream().filter(item->item.subscription().type().toLowerCase().equals("standard")).toList().size();
        numberSubscribPrime.setText("* "+countPrimeSubscribe+" prime abonnés");
        numberSubscribeStandard.setText("* "+countStandardSubscribe+" standard abonnés");
        numberSubscribeGold.setText("* "+countGoldSubscribe+" gold abonnés");
    }
    private void calculTotalMontant(){
        AtomicLong sum=new AtomicLong();
        dataList.forEach(item->sum.addAndGet((long) item.subscription().price()));
        montantAnnualSubscribe.setText(MaiUtils.numberFormat(sum.get())+" FCFA");
    }
    private void fetchSubscribes(){
        try {
            fetch.get(Constants.SUBSCRIBE_FETCH_URL_PATH).then((result,status)->{
                if(status==ResponseStatusCode.OK){
                    dataList=gson.fromJson(result, listTypeSubscribe);
                    insertToDataTable(dataList);
                    changeContentCardSubscribeByTypeInfo();
                    changeContentCardSubscribeInfo();
                    calculTotalMontant();
                }
            });
        } catch (MaiException e) {
            Logger.getLogger(DashboardController.class.getName(),e.getMessage());
        }
    }
    private void insertToDataTable(List<SubscribeModel>data){
        DefaultTableModel tableModel=(DefaultTableModel)table.getModel();
        tableModel.setRowCount(0);
        data.forEach(item->tableModel.addRow(new Object[]{
            item.subscribeId(),
            item.dateStart(),
            item.dateEnd(),
            item.customer().firstName()+" "+item.customer().lastName(),
            item.subscription().type(),
            item.isActive()
        }));
    }

    @Override
    public void updateState(Object... args) {
        fetchSubscribes();
    }

    public List<SubscribeModel> getDataList() {
        return dataList;
    }
    
    
}
