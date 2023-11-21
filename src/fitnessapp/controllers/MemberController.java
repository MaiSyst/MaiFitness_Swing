/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.controllers;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.maisyst.MaiFetch;
import com.maisyst.exceptions.MaiException;
import com.maisyst.utils.Authorization;
import com.maisyst.utils.enums.ResponseStatusCode;
import fitnessapp.components.ButtonTableAction;
import fitnessapp.models.CustomerModel;
import fitnessapp.utilities.API;
import fitnessapp.utilities.Constants;
import fitnessapp.utilities.MaiFunctionCallWithArgs;
import fitnessapp.utilities.MaiState;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import raven.toast.Notifications;

/**
 *
 * @author orion90
 */
public class MemberController implements MaiState {

    private final JButton removeCustomer;
    private final JButton addCustomer;
    private final JTable table;
    private final JTextField search;
    private final JFrame parent;
    private final MaiFetch fetch;
    private final MaiState maiState;
    private List<CustomerModel> dataList = new ArrayList<>();
    private final Gson gson = new Gson();
    private final boolean isWithRoom;
    private final String roomId;
    private final Type listCustomer = new TypeToken<List<CustomerModel>>() {
    }.getType();

    public MemberController(
            final JButton removeCustomer,
            final JButton addCustomer,
            final JTable table, final JTextField search,
            final JFrame parent, final String token, final MaiState maiState) {
        this.removeCustomer = removeCustomer;
        this.addCustomer = addCustomer;
        this.table = table;
        this.search = search;
        this.parent = parent;
        this.maiState = maiState;
        this.isWithRoom=false;
        this.roomId="";
        this.fetch = API.fetch(new Authorization(token));
        init();
    }
    public MemberController(
            final JButton removeCustomer,
            final JButton addCustomer,
            final JTable table, final JTextField search,
            final JFrame parent, final String token, final MaiState maiState,boolean isWithRoom,String roomId) {
        this.removeCustomer = removeCustomer;
        this.addCustomer = addCustomer;
        this.table = table;
        this.search = search;
        this.parent = parent;
        this.maiState = maiState;
        this.isWithRoom=isWithRoom;
        this.roomId=roomId;
        this.fetch = API.fetch(new Authorization(token));
        init();
    }
    private void init(){
        if (addCustomer != null && removeCustomer != null) {

            addCustomer.addActionListener(l -> onHandleShowModal());
            addCustomer.setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "plus.svg", 1f));
            removeCustomer.setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "trash.svg", 1f));
            removeCustomer.addActionListener(l -> {
                var rows = table.getSelectedRows();
                if (rows.length > 0) {
                    List<String> ids = new ArrayList<>();
                    for (var row : rows) {
                        var item = table.getValueAt(row, 5).toString();
                        ids.add(item);
                    }
                    onRemoveCustomer(ids);
                } else {
                    Notifications.getInstance().show(Notifications.Type.INFO, "Veuillez la ligne a supprimer dans le tableau.");
                }
            });
        }
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) {
                    var row = table.getSelectedRow();
                    var customerId = table.getValueAt(row, 5).toString();
                    var firstName = table.getValueAt(row, 1).toString();
                    var lastName = table.getValueAt(row, 2).toString();
                    var birhtday = table.getValueAt(row, 3).toString();
                    var address = table.getValueAt(row, 4).toString();
                    onHandleEditModal(customerId, firstName, lastName, birhtday, address);
                }
            }

        });
        search.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Rechercher par prenom ou nom...");
        inputSearchEvent();
        fetchMemberData();
    
    }
    private void onRemoveCustomer(List<String> ids) {
        var options = JOptionPane.showConfirmDialog(parent, "Etes-vous sure de supprimer?", "Suppression", JOptionPane.OK_CANCEL_OPTION);
        if (options == JOptionPane.OK_OPTION) {
            try {
                Map<String, Object> body = new HashMap<>();
                body.put("ids", ids);
                fetch.post(Constants.CUSTOMER_DELETE_URL_PATH, body).then((result, status) -> {
                    if (status == ResponseStatusCode.OK) {
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "( " + ids.size() + " ) Membres ont ete supprimes.");
                        fetchMemberData();
                    } else {
                        Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, result);
                    }
                });

            } catch (MaiException e) {
                Logger.getLogger(MemberController.class.getName(), e.getMessage());
            }
        }
    }

    private void inputSearchEvent() {
        search.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (search.getText().isBlank()) {
                    insertDataTable(dataList);
                } else {
                    var query = search.getText().toLowerCase();
                    var data = dataList.stream().filter(item
                            -> item.firstName().toLowerCase().contains(query)
                            || item.lastName().toLowerCase().contains(query)).toList();
                    insertDataTable(data);
                }

            }
        });
    }

    private void fetchMemberData() {
        try {
            final String url;
            if(isWithRoom){
                url=Constants.CUSTOMER_FETCH_ACCESS_ROOM_URL_PATH+"/"+roomId;
            }else{
                url=Constants.CUSTOMER_FETCH_URL_PATH;
            }
            fetch.get(url).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    dataList = gson.fromJson(result, listCustomer);
                    insertDataTable(dataList);
                }
            });

        } catch (MaiException e) {
            Logger.getLogger(MemberController.class.getName(), e.getMessage());
        }
    }

    private void insertDataTable(List<CustomerModel> data) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        data.forEach(item
                -> model.addRow(new Object[]{
            item.identityEMF(),
            item.firstName(),
            item.lastName().toUpperCase(),
            item.yearOfBirth(),
            item.address(),
            item.customerId()})
        );
        TableColumnModel columnModel = table.getColumnModel();
        TableColumn buttonActionColumn = columnModel.getColumn(5);
        buttonActionColumn.setCellRenderer(new ButtonActionShowRenderCell());
        buttonActionColumn.setCellEditor(new ButtonActionShowRenderCellEditor(args -> showCardMember(
                args[0].toString(),
                args[1].toString(),
                args[2].toString(),
                args[3].toString(),
                args[4].toString()
        )));
    }

    private void onHandleShowModal() {
        new MemberModalController(fetch, this::fetchMemberData, maiState).show(parent);
    }

    private void onHandleEditModal(String customerId, String firstName, String lastName, String birthday, String address) {
        new MemberModalController(fetch, customerId, firstName, lastName, birthday, address, this::fetchMemberData, maiState).show(parent);
    }

    @Override
    public void updateState(Object... args) {
        fetchMemberData();
    }

    static final class ButtonActionShowRenderCell extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            var com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            ButtonTableAction buttonAction = new ButtonTableAction(null);
            buttonAction.setBackground(com.getBackground());
            return buttonAction;
        }
    }

    static final class ButtonActionShowRenderCellEditor extends DefaultCellEditor {

        private final MaiFunctionCallWithArgs callback;

        public ButtonActionShowRenderCellEditor(final MaiFunctionCallWithArgs callback) {
            super(new JCheckBox());
            this.callback = callback;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            var id = table.getValueAt(row, 0).toString();
            var firstName = table.getValueAt(row, 1).toString();
            var lastName = table.getValueAt(row, 2).toString();
            var birthday = table.getValueAt(row, 3).toString();
            var address = table.getValueAt(row, 4).toString();

            ButtonTableAction buttonAction = new ButtonTableAction(() -> callback.invoked(id, firstName, lastName, birthday, address));
            if (isSelected) {
                buttonAction.setBackground(table.getSelectionBackground());
            }
            return buttonAction;
        }

    }

    private void showCardMember(String id, String firstName, String lastName, String address, String birthday) {
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(id, BarcodeFormat.QR_CODE, 100, 100);
            BufferedImage imageBuff = MatrixToImageWriter.toBufferedImage(matrix);

            new CardMemberController(parent, id, firstName, lastName, birthday, address, imageBuff).show();
        } catch (WriterException ex) {
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
