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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
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
    private final String token;
    private final Gson gson = new Gson();
    private final MaiFetch fetch;
    private final List<Integer> activityIdSelected = new ArrayList<>();
    private final Type activityListType = new TypeToken<List<ActivityModel>>() {
    }.getType();

    public ActivityController(JFrame parent, JButton addActivity, JTextField search, JButton removeActivity, JTable table, String token) {
        this.addActivity = addActivity;
        this.parent = parent;
        this.removeActivity = removeActivity;
        this.search = search;
        this.table = table;
        this.token = token;
        Authorization authorization = new Authorization(token);

        fetch = API.fetch(authorization);
        addActivity.addActionListener(l -> onHandleShowModal());
        addActivity.setIcon(new FlatSVGIcon("fitnessapp/icons/plus.svg", 1f));
        removeActivity.setIcon(new FlatSVGIcon("fitnessapp/icons/trash.svg", 1f));
        search.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Rechercher votre activite...");
        dataRefreshTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    showEditMode();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getWhen() == 2000L) {
                    deleteCurrentSelected();
                }
            }

        });
        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                if (e.getKeyCode() == KeyEvent.VK_DELETE||e.getKeyCode() == KeyEvent.VK_CANCEL) {
                    System.out.println("Deleted");
                }
            }

        });
    }

    private void showEditMode() {
        System.out.println("EditMode");
    }

    private void deleteCurrentSelected() {
        System.out.println("DeleteMode");
    }

    private void dataRefreshTable() {
        try {
            fetch.get(Constants.ACTIVITY_URL_PATH + "/fetchAll").then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    List<ActivityModel> models = gson.fromJson(result, activityListType);
                    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                    tableModel.setRowCount(0);
                    models.forEach(model
                            -> tableModel.addRow(new String[]{String.valueOf(model.activityId()), model.label(), model.description()})
                    );
                }
            });
        } catch (MaiException e) {
            System.out.println(e.getMessage());
        }
    }

    private void onHandleShowModal() {
        new ActivityModalController().show(parent);
    }

}
