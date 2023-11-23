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
import fitnessapp.models.UserModel;
import fitnessapp.utilities.API;
import fitnessapp.utilities.Constants;
import fitnessapp.utilities.MaiState;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

/**
 *
 * @author maisyst
 */
public class UserController implements MaiState{

    private final JButton addUsers;
    private final JButton removeUsers;
    private final JTextField searchUsers;
    private final JLabel selectedNumberUsersToRemove;
    private final JTable table;
    private final MaiFetch fetch;
    private List<UserModel> dataList = new ArrayList<>();
    private final Gson gson = new Gson();
    private final JFrame parent;
    private final MaiState state;
    public UserController(final JFrame parent, JButton addUsers, JButton removeUsers,
            JTextField searchUsers, JLabel selectedNumberUsersToRemove,
            JTable table, final String token,MaiState state) {
        this.addUsers = addUsers;
        this.removeUsers = removeUsers;
        this.searchUsers = searchUsers;
        this.parent = parent;
        this.state=state;
        fetch = API.fetch(new Authorization(token));
        this.selectedNumberUsersToRemove = selectedNumberUsersToRemove;
        this.table = table;
        addUsers.addActionListener(l -> onHandleShowModal());
        addUsers.setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "plus.svg", 1f));
        removeUsers.setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "trash.svg", 1f));
        removeUsers.addActionListener(l -> onHandleRemoveUsers());
        searchUsers.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Rechercher par prenom ou nom...");
        searchUsers.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon(Constants.ICONS_PATH + "search.svg"));
        searchUsers.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                var query = searchUsers.getText();
                if (query.isBlank()) {
                    insertDataTable(dataList);
                } else {
                    onSearchUser(query);
                }
            }

        });
        requestDataTable();
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
                    onHandleEditShowModal();
                }
                if (table.getSelectedColumn() == 7) {
                    var selected = Boolean.parseBoolean(table.getValueAt(table.getSelectedRow(), 7).toString());
                    var username = table.getValueAt(table.getSelectedRow(), 0).toString();

                    if (!selected) {

                        var option = JOptionPane.showConfirmDialog(parent, "Voulez-vous desactiver ce compte(" + username + ")?", "Desactivation de compte", JOptionPane.OK_CANCEL_OPTION);
                        if (option == JOptionPane.OK_OPTION) {
                            disableOrEnableAccount(false,username);
                        } else {
                            table.setValueAt(true, table.getSelectedRow(), 7);

                        }

                    } else {
                        var option = JOptionPane.showConfirmDialog(parent, "Voulez-vous activer ce compte(" + username + ")?", "Activation de compte", JOptionPane.OK_CANCEL_OPTION);
                        if (option == JOptionPane.OK_OPTION) {
                            disableOrEnableAccount(true,username);
                        } else {
                            table.setValueAt(false, table.getSelectedRow(), 7);

                        }
                    }

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
                    onHandleRemoveUsers();
                }
            }

        });
    }

    private void disableOrEnableAccount(boolean isActive, String username) {
        try {
            Map<String, Object> body = new HashMap<>();
            body.put("isActive", isActive);
            fetch.post(Constants.USER_DISABLE_ENABLE_ACCOUNT_URL_PATH + "/" + username, body)
                    .then((result, status) -> {
                        if (status == ResponseStatusCode.OK) {
                            table.setValueAt(isActive, table.getSelectedRow(), 7);
                        }
                    });
        } catch (MaiException ex) {
            Logger.getLogger(UserController.class.getName(), ex.getMessage());
        }
    }

    private void onHandleRemoveUsers() {
        var rows = table.getSelectedRows();
        if (rows.length == 0) {
            Notifications.getInstance().show(Notifications.Type.INFO, "Vous devriez selectionner d'abord la line a supprimer.");
        } else {
            var response = JOptionPane.showConfirmDialog(parent, "Etes-vous sure de supprimer?", "Suppression", JOptionPane.OK_CANCEL_OPTION);
            if (response == JOptionPane.OK_OPTION) {
                List<String> ids = new ArrayList<>();
                for (int row : rows) {
                    ids.add(table.getValueAt(row, 8).toString());
                }
                removedOneOrMultiUsers(ids);
            }
        }
    }

    private void removedOneOrMultiUsers(List<String> usersId) {
        try {
            Map<String, Object> body = new HashMap<>();
            body.put("ids", usersId);
            fetch.post(Constants.USER_DELETE_MANY_URL_PATH, body).then((res, status) -> {
                if (status == ResponseStatusCode.OK) {
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "(" + usersId.size() + ") gerants ont été supprimé.");
                    requestDataTable();
                } else {
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Erreur de suppression.");

                }
            });
        } catch (MaiException e) {
            Logger.getLogger(UserController.class.getName(), e.getMessage());
        }
    }

    private void changeSelectedItems() {
        if (table.getSelectedRows().length != 0) {
            selectedNumberUsersToRemove.setText("( " + table.getSelectedRows().length + " )");
            selectedNumberUsersToRemove.setForeground(Color.BLACK);

        } else {
            selectedNumberUsersToRemove.setText("( 0 )");
            selectedNumberUsersToRemove.setForeground(Color.GRAY);
        }
    }

    private void onHandleShowModal() {

        new UserModalController(fetch, this::requestDataTable).show();
    }

    private void onHandleEditShowModal() {
        var row=table.getSelectedRow();
        var username=table.getValueAt(row, 0).toString();
        var firstName=table.getValueAt(row, 1).toString();
        var lastName=table.getValueAt(row, 2).toString();
        var date=table.getValueAt(row, 3).toString();
        var address=table.getValueAt(row, 4).toString();
        var phone=table.getValueAt(row, 5).toString();
        var roomName=table.getValueAt(row,6).toString();
        
        new UserModalController(fetch,firstName,lastName,address,phone,date,roomName,username, this::requestDataTable).show();
    }

    private void onSearchUser(final String query) {
        var response = dataList.stream().filter(item
                -> item.firstName().toLowerCase().contains(query.toLowerCase())
                || item.lastName().toLowerCase().contains(query.toLowerCase())).toList();
        insertDataTable(response);
    }

    private void requestDataTable() {
        try {
            fetch.get(Constants.USER_FETCH_URL_PATH).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    Type listUserModel = new TypeToken<List<UserModel>>() {
                    }.getType();
                    dataList = gson.fromJson(result, listUserModel);
                    insertDataTable(dataList);
                    state.updateState();
                }
            });
            changeSelectedItems();
        } catch (MaiException e) {
             Logger.getLogger(UserController.class.getName(), e.getMessage());
        }
    }

    private void insertDataTable(List<UserModel> data) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        data.forEach(item -> tableModel.addRow(new Object[]{
            item.username(),
            item.firstName(),
            item.lastName(),
            item.date(),
            item.address(),
            item.phoneNumber(),
            item.room().roomName(),
            item.isActive(),
            item.userId()
        }));

        table.getColumnModel().getColumn(8).setMaxWidth(0);
        table.getColumnModel().getColumn(8).setMinWidth(0);
        table.getColumnModel().getColumn(8).setPreferredWidth(0);
    }

    @Override
    public void updateState(Object... args) {
        requestDataTable();
    }
    
}
