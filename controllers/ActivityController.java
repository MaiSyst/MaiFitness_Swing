/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.controllers;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.google.gson.Gson;
import com.maisyst.MaiFetch;
import com.maisyst.exceptions.MaiException;
import com.maisyst.utils.Authorization;
import com.maisyst.utils.enums.ResponseStatusCode;
import fitnessapp.models.ActivityModel;
import fitnessapp.utilities.API;
import fitnessapp.utilities.Constants;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.util.List;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import fitnessapp.utilities.MaiState;
import fitnessapp.utilities.MaiUtils;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

/**
 *
 * @author orion90
 */
public class ActivityController {

    private final JFrame parent;
    private final JButton addActivity;
    private final JTextField search;
    private final JButton removeActivity;
    private final JTable table;
    private final String flatStyle = FlatClientProperties.STYLE;
    private final Gson gson = new Gson();
    private final JLabel numberActvitiesSelectJLabel;
    private final MaiFetch fetch;
    private final List<Integer> activityIdSelected = new ArrayList<>();
    private final Type activityListType = new TypeToken<List<ActivityModel>>() {
    }.getType();
    private List<MaiState> states=new ArrayList<>();
    public ActivityController(final JFrame parent, final JButton addActivity, 
            final JTextField search,final JButton removeActivity, JTable table,
            final String token, final JLabel numberActvitiesSelectJLabel) {
        this.addActivity = addActivity;
        this.parent = parent;
        this.removeActivity = removeActivity;
        this.search = search;
        this.table = table;
        this.numberActvitiesSelectJLabel = numberActvitiesSelectJLabel;
        fetch = API.fetch(new Authorization(token));
        addActivity.addActionListener(l -> onHandleShowModal());
        addActivity.setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "plus.svg", 1f));
        removeActivity.setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "trash.svg", 1f));
        removeActivity.addActionListener(l -> activitySuppression());
        search.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Rechercher par label activité...");
        search.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon(Constants.ICONS_PATH + "search.svg"));

        search.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                var word = search.getText().trim();
                if (word.length() == 0) {
                    dataRefreshTable();

                } else {
                    searchIt(word);
                }
            }

        });
        dataRefreshTable();
        table.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                changeSelectedItems();
            }

        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeSelectedItems();
                if (e.getClickCount() == 2) {
                    showEditMode();
                }
            }

        });
        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                changeSelectedItems();
            }

            @Override
            public void keyPressed(KeyEvent e) {

                super.keyPressed(e); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                changeSelectedItems();
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    activitySuppression();
                }
            }

        });
    }

    private void activitySuppression() {
        var rows = table.getSelectedRows();
        if (rows.length == 0) {
            Notifications.getInstance().show(Notifications.Type.INFO, "Vous devriez selectionner d'abord la line a supprimer.");
        } else {
            var response = JOptionPane.showConfirmDialog(parent, "Etes-vous sure de supprimer?", "Suppression", JOptionPane.OK_CANCEL_OPTION);
            if (response == JOptionPane.OK_OPTION) {
                List<UUID> ids = new ArrayList<>();
                for (int row : rows) {
                    ids.add(UUID.fromString(table.getValueAt(row, 0).toString()));
                }
                removedOneOrMultiActivities(ids);
            }
        }
    }

    private void removedOneOrMultiActivities(List<UUID> activitiesId) {
        try {
            Map<String, Object> body = new HashMap<>();
            body.put("ids", activitiesId);
            fetch.post(Constants.ACTIVITY_DELETE_MANY_URL_PATH, body).then((res, status) -> {
                if (status == ResponseStatusCode.OK) {
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "(" + activitiesId.size() + ") activités ont été supprimé.");
                    dataRefreshTable();
                } else {
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Erreur de suppression.");

                }
            });
        } catch (MaiException e) {
            System.out.println(e.getMessage());
        }
    }

    private void changeSelectedItems() {
        if (table.getSelectedRows().length != 0) {
            numberActvitiesSelectJLabel.setText("( " + table.getSelectedRows().length + " )");
            numberActvitiesSelectJLabel.setForeground(Color.BLACK);

        } else {
            numberActvitiesSelectJLabel.setText("( 0 )");
            numberActvitiesSelectJLabel.setForeground(Color.GRAY);
        }
    }

    private void searchIt(String word) {
        try {
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            fetch.get(Constants.ACTIVITY_SEARCH_URL_PATH + "query=" + word).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    tableModel.setRowCount(0);
                    List<ActivityModel> models = gson.fromJson(result, activityListType);
                    models.forEach(model
                            -> tableModel.addRow(new String[]{String.valueOf(model.activityId()), model.label(), model.description()})
                    );
                }
            });
            changeSelectedItems();

        } catch (MaiException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showEditMode() {
        var row = table.getSelectedRow();
        var id = table.getValueAt(row, 0).toString();
        var label = table.getValueAt(row, 1).toString().toLowerCase();
        var desc = table.getValueAt(row, 2).toString().toLowerCase();
        new ActivityModalController(fetch, table, id, label, desc, this::dataRefreshTable).show(parent);
    }

    private void dataRefreshTable() {
        try {
            fetch.get(Constants.ACTIVITY_FETCH_URL_PATH).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    List<ActivityModel> models = gson.fromJson(result, activityListType);
                    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                    tableModel.setRowCount(0);
                    models.forEach(model
                            -> tableModel.addRow(new String[]{
                                String.valueOf(model.activityId()), 
                                MaiUtils.toCapitalCase(model.label()), model.description()})
                    );
                    states.forEach(state->state.updateState());
                }
            });
            changeSelectedItems();
        } catch (MaiException e) {
            System.out.println(e.getMessage());
        }
    }

    private void onHandleShowModal() {
        new ActivityModalController(fetch, table, this::dataRefreshTable).show(parent);
    }
   public void subscribe(MaiState ...state){
        states.addAll(Arrays.asList(state));
    }
    public void unsubscribe(MaiState state){
        states.remove(state);
    }
}
