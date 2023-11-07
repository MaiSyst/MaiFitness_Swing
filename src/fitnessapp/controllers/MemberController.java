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
import com.raven.datechooser.Button;
import fitnessapp.components.ButtonTableAction;
import fitnessapp.models.CustomerModel;
import fitnessapp.utilities.API;
import fitnessapp.utilities.Constants;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author orion90
 */
public class MemberController {

    private final JButton removeCustomer;
    private final JButton addCustomer;
    private final JTable table;
    private final JTextField search;
    private final JFrame parent;
    private final MaiFetch fetch;
    private List<CustomerModel> dataList = new ArrayList<>();
    private final Gson gson = new Gson();
    private final Type listCustomer = new TypeToken<List<CustomerModel>>() {
    }.getType();

    public MemberController(
            final JButton removeCustomer,
            final JButton addCustomer,
            final JTable table, final JTextField search,
            final JFrame parent, final String token) {
        this.removeCustomer = removeCustomer;
        this.addCustomer = addCustomer;
        this.table = table;
        this.search = search;
        this.parent = parent;
        this.fetch = API.fetch(new Authorization(token));
        addCustomer.addActionListener(l -> onHandleShowModal());
        addCustomer.setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "plus.svg", 1f));
        removeCustomer.setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "trash.svg", 1f));
        search.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Rechercher par prenom ou nom...");
        fetchMemberData();
    }

    private void fetchMemberData() {
        try {
            fetch.get(Constants.CUSTOMER_FETCH_URL_PATH).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    dataList = gson.fromJson(result, listCustomer);
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0);

                    dataList.forEach(data -> {

                        model.addRow(new Object[]{
                            data.customerId(),
                            data.firstName(),
                            data.lastName().toUpperCase(),
                            data.yearOfBirth(),
                            data.address(),});

                    });
                    TableColumnModel columnModel = table.getColumnModel();
                    TableColumn buttonActionColumn = columnModel.getColumn(5);
                    buttonActionColumn.setCellRenderer(new ButtonActionShowRenderCell());
                    buttonActionColumn.setCellEditor(new ButtonActionShowRenderCellEditor());
                }
            });

        } catch (MaiException e) {
            Logger.getLogger(MemberController.class.getName(), e.getMessage());
        }
    }

    private void onHandleShowModal() {
        new MemberModalController(fetch, this::fetchMemberData).show(parent);
    }

    static final class ButtonActionShowRenderCell extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            var com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            var id = table.getValueAt(row, 0).toString();
            ButtonTableAction buttonAction = new ButtonTableAction();
            buttonAction.setBackground(com.getBackground());
            return buttonAction;
        }
    }

    static final class ButtonActionShowRenderCellEditor extends DefaultCellEditor {

        public ButtonActionShowRenderCellEditor() {
            super(new JCheckBox());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            var id = table.getValueAt(row, 0).toString();
            ButtonTableAction buttonAction = new ButtonTableAction();
            if (isSelected) {
                buttonAction.setBackground(table.getSelectionBackground());
            }
            return buttonAction;
        }

    }

    
}
