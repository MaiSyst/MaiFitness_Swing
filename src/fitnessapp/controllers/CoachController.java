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
import fitnessapp.models.ActivityModel;
import fitnessapp.models.CoachModel;
import fitnessapp.utilities.API;
import fitnessapp.utilities.Constants;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import static java.awt.event.ItemEvent.SELECTED;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

/**
 *
 * @author orion90
 */
public class CoachController {

    private final JTable tableCoach;
    private final JLabel selectedItemTable;
    private final JFrame parent;
    private final MaiFetch fetch;
    private final JComboBox<String> filterActivity;
    private final Type listCoachType = new TypeToken<List<CoachModel>>() {
    }.getType();
    private static List<CoachModel> dataList = new ArrayList<>();
    private final Gson gson = new Gson();
    private static final Logger logger = Logger.getLogger(CoachController.class.getName());

    public CoachController(final JTextField search, final JButton removeCoach,
            final JButton addCoach,
            final JTable tableCoach,
            final JLabel selectedItemTable,
            final String token,
            final JFrame parent,
            final JComboBox<String> filterActivity,
            final boolean isAdmin
    ) {
        this.parent = parent;
        this.tableCoach = tableCoach;
        this.selectedItemTable = selectedItemTable;
        this.filterActivity = filterActivity;
        this.fetch = API.fetch(new Authorization(token));
        if (removeCoach != null && addCoach != null) {
            addCoach.addActionListener(l -> onHandleShowModal());
            addCoach.setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "plus.svg"));
            removeCoach.setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "trash.svg"));
            removeCoach.addActionListener(l -> activitySuppression());
        }
        search.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Rechercher par prenom ou nom...");
        search.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon(Constants.ICONS_PATH + "search.svg"));

        dataRefreshTable();
        fetchActivities();
        if (isAdmin) {
            tableCoach.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    super.focusLost(e);
                    changeSelectedItems();
                }

            });
            tableCoach.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    changeSelectedItems();
                    if (e.getClickCount() == 2) {
                        var rows = tableCoach.getSelectedRow();
                        var id = tableCoach.getValueAt(rows, 0).toString();
                        var f = tableCoach.getValueAt(rows, 1).toString();
                        var l = tableCoach.getValueAt(rows, 2).toString();
                        var p = tableCoach.getValueAt(rows, 3).toString();
                        var a = tableCoach.getValueAt(rows, 4).toString();
                        var s = tableCoach.getValueAt(rows, 5).toString();
                        showEditModal(id, f, l, p, a, s);
                    }
                }

            });
            tableCoach.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    super.keyReleased(e);
                    changeSelectedItems();
                }

                @Override
                public void keyPressed(KeyEvent e) {

                    super.keyPressed(e);
                    changeSelectedItems();
                    if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                        activitySuppression();
                    }
                }

            });
        }
        //Filter coach by activity
        filterActivity.addItemListener(l -> {
            if (l.getStateChange() == SELECTED) {
                var item = l.getItem().toString();
                if (item.equals("Toutes les activites")) {
                    insertToDataTable(dataList);
                } else {
                    filterCoachByActivity(item);
                }
            }
        });
        //Search input
        search.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (search.getText().isBlank()) {
                    insertToDataTable(dataList);
                } else {
                    var query = search.getText();
                    var response = dataList.stream()
                            .filter(p -> p.firstName().contains(query) || p.lastName().contains(query))
                            .toList();
                    insertToDataTable(response);
                }
            }

        });
    }

    private void filterCoachByActivity(String query) {
        var resp = dataList.stream().filter(f -> f.speciality().equals(query)).toList();
        insertToDataTable(resp);
    }

    private void fetchActivities() {
        try {
            fetch.get(Constants.ACTIVITY_FETCH_URL_PATH).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    final Type activityListType = new TypeToken<List<ActivityModel>>() {
                    }.getType();
                    final List<ActivityModel> models = gson.fromJson(result, activityListType);
                    filterActivity.removeAllItems();
                    filterActivity.addItem("Toutes les activites");
                    models.forEach(model -> filterActivity.addItem(model.label()));
                }
            });
        } catch (MaiException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    private void activitySuppression() {
        var rows = tableCoach.getSelectedRows();
        if (rows.length == 0) {
            Notifications.getInstance().show(Notifications.Type.INFO, "Vous devriez selectionner d'abord la line a supprimer.");
        } else {
            var response = JOptionPane.showConfirmDialog(parent, "Etes-vous sure de supprimer?", "Suppression", JOptionPane.OK_CANCEL_OPTION);
            if (response == JOptionPane.OK_OPTION) {
                List<String> ids = new ArrayList<>();
                for (int row : rows) {
                    ids.add(tableCoach.getValueAt(row, 0).toString());
                }
                removedOneOrMultiItems(ids);
            }
        }
    }

    private void removedOneOrMultiItems(List<String> coachIds) {
        try {
            Map<String, Object> body = new HashMap<>();
            body.put("ids", coachIds);
            fetch.post(Constants.COACH_DELETE_MANY_URL_PATH, body).then((res, status) -> {
                if (status == ResponseStatusCode.OK) {
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "(" + coachIds.size() + ") entraineur ont été supprimé.");
                    dataRefreshTable();
                } else {
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Erreur de suppression.");

                }
            });
        } catch (MaiException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    private void dataRefreshTable() {
        try {
            fetch.get(Constants.COACH_FETCH_URL_PATH).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    dataList = gson.fromJson(result, listCoachType);
                    insertToDataTable(dataList);
                }
            });
        } catch (MaiException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    private void insertToDataTable(List<CoachModel> data) {
        DefaultTableModel tableModel = (DefaultTableModel) tableCoach.getModel();
        tableModel.setRowCount(0);
        data.forEach(model
                -> tableModel.addRow(new String[]{
            model.coachId(),
            model.firstName(),
            model.lastName(),
            model.phone(),
            model.address(),
            model.speciality()
        })
        );

    }

    private void changeSelectedItems() {
        if (tableCoach.getSelectedRows().length != 0) {
            selectedItemTable.setText("( " + tableCoach.getSelectedRows().length + " )");
            selectedItemTable.setForeground(Color.BLACK);

        } else {
            selectedItemTable.setText("( 0 )");
            selectedItemTable.setForeground(Color.GRAY);
        }
    }

    private void onHandleShowModal() {
        new CoachModalController(fetch, this::dataRefreshTable).show();
    }

    private void showEditModal(String coachId,
            String firstName,
            String lastName,
            String phone,
            String address,
            String speciality) {
        new CoachModalController(fetch, coachId, firstName, lastName,
                phone, address, speciality,
                this::dataRefreshTable).show();
    }

}
