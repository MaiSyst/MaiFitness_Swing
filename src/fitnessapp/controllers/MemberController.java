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
import fitnessapp.models.CustomerWithSubscribesModel;
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
public final class MemberController implements MaiState {

    private final JButton removeCustomer;
    private final JButton addCustomer;
    private final JTable table;
    private final JTextField search;
    private final JFrame parent;
    private final MaiFetch fetch;
    private final MaiState maiState;
    private List<CustomerWithSubscribesModel> dataList = new ArrayList<>();
    private final Gson gson = new Gson();
    private final boolean isAdmin;
    private final String roomId;
    private final Type listCustomer = new TypeToken<List<CustomerWithSubscribesModel>>() {
    }.getType();
    private static final Logger logger=Logger.getLogger(MemberController.class.getName());
    public MemberController(
            final JButton removeCustomer,
            final JButton addCustomer,
            final JTable table, final JTextField search,
            final JFrame parent,final JButton reSubscribe, final String token, final MaiState maiState) {
        this.removeCustomer = removeCustomer;
        this.addCustomer = addCustomer;
        this.table = table;
        this.search = search;
        this.parent = parent;
        this.maiState = maiState;
        this.isAdmin = true;
        this.roomId = "";
        this.fetch = API.fetch(new Authorization(token));
        init();
        addCustomer.setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "plus.svg", 1f));
        reSubscribe.addActionListener(l->onHandleResubscribe());
        reSubscribe.setIcon(new FlatSVGIcon(Constants.ICONS_PATH+"resubscribe.svg"));
    }

    public MemberController(
            final JButton removeCustomer,
            final JButton addCustomer,
            final JTable table, final JTextField search,
            final JFrame parent,final JButton reSubscribe, final String token, 
            final MaiState maiState, final String roomId) {
        this.removeCustomer = removeCustomer;
        this.addCustomer = addCustomer;
        this.table = table;
        this.search = search;
        this.parent = parent;
        this.maiState = maiState;
        this.isAdmin = false;
        this.roomId = roomId;
        this.fetch = API.fetch(new Authorization(token));
            addCustomer.setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "plus.svg", 1f));
        reSubscribe.addActionListener(l->onHandleResubscribe());
        reSubscribe.setIcon(new FlatSVGIcon(Constants.ICONS_PATH+"resubscribe.svg"));
        init();

    }
  
    
    private void init() {
        if (removeCustomer != null) {
            
            removeCustomer.setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "trash.svg", 1f));
            removeCustomer.addActionListener(l -> {
                final var rows = table.getSelectedRows();
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
        addCustomer.addActionListener(l -> onHandleShowModal());
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

    private void onRemoveCustomer(final List<String> ids) {
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
            if (!isAdmin) {
                url = Constants.CUSTOMER_FETCH_ACCESS_ROOM_URL_PATH + "/" + roomId;
            } else {
                url = Constants.CUSTOMER_FETCH_URL_PATH;
            }
            fetch.get(url).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    dataList = gson.fromJson(result, listCustomer);
                    insertDataTable(dataList);
                }
            });

        } catch (MaiException e) {
           logger.info(e.getMessage());
        }
    }

    private void insertDataTable(List<CustomerWithSubscribesModel> data) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        data.forEach(item
                -> model.addRow(new Object[]{
            item.identityEMF(),
            item.firstName(),
            item.lastName().toUpperCase(),
            item.yearOfBirth(),
            item.address(),
            item.customerId(),
            item.roomName(),
           item.subscribes().get(0).dateEnd()
        })
        );
        TableColumnModel columnModel = table.getColumnModel();
        TableColumn buttonActionColumn = columnModel.getColumn(5);
        columnModel.getColumn(6).setMaxWidth(0);
        columnModel.getColumn(6).setMinWidth(0);
        columnModel.getColumn(6).setPreferredWidth(0);
        
        columnModel.getColumn(7).setMaxWidth(0);
        columnModel.getColumn(7).setMinWidth(0);
        columnModel.getColumn(7).setPreferredWidth(0);
        
        columnModel.getColumn(8).setMaxWidth(0);
        columnModel.getColumn(8).setMinWidth(0);
        columnModel.getColumn(8).setPreferredWidth(0);
        
        buttonActionColumn.setCellRenderer(new ButtonActionShowRenderCell());
        buttonActionColumn.setCellEditor(new ButtonActionShowRenderCellEditor(args -> 
                showCardMember(
                args[0].toString(),
                args[1].toString(),
                args[2].toString(),
                args[3].toString(),
                args[4].toString(),
                args[5].toString().toUpperCase(),args[6].toString()
        )));
    }

    private void onHandleShowModal() {
        new MemberModalController(fetch, this::fetchMemberData, maiState,isAdmin).show();
    }

    private void onHandleEditModal(String customerId, String firstName,
            String lastName, String birthday, String address) {
        new MemberModalController(fetch, customerId, firstName, lastName, 
                birthday, address, this::fetchMemberData, 
                maiState,isAdmin).show();
    }

    @Override
    public void updateState(Object... args) {
        fetchMemberData();
        
        showCardMember(args[0].toString(),
                args[1].toString(),
                args[2].toString(),
                args[3].toString(),
                args[4].toString(),
                args[5].toString(),
                args[6].toString()
                );
    }
    
    private void onHandleResubscribe(){
        var row=table.getSelectedRow();
        if(row==-1){
            Notifications.getInstance().show(Notifications.Type.INFO, "Selectionner la ligne d'abord avant de faire le réabonnement.");
        }else{
            var firstName=table.getValueAt(row, 1).toString();
            var lastName=table.getValueAt(row, 2).toString();
            var identity=table.getValueAt(row, 0).toString();
            new ResubscribeController(parent, fetch, identity, firstName, lastName).show();
        }
    }
    
    static final class ButtonActionShowRenderCell extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            var com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
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
            var roomName = table.getValueAt(row, 6).toString();
            var expirate = table.getValueAt(row, 7).toString();
           
            ButtonTableAction buttonAction = new ButtonTableAction(() -> callback.invoked(
                    id, 
                    firstName, 
                    lastName, 
                    address,
                    birthday,
                    roomName,
                    expirate
                    ));
            if (isSelected) {
                buttonAction.setBackground(table.getSelectionBackground());
            }
            return buttonAction;
        }

    }

    private void showCardMember(
            String id, 
            String firstName, 
            String lastName, 
            String address, 
            String birthday,
            String roomName,
            String expirateDate
            ) {
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(id, BarcodeFormat.QR_CODE, 120, 120);
            BufferedImage imageBuff = MatrixToImageWriter.toBufferedImage(matrix);

            new CardMemberController(parent, id, firstName, lastName, 
                    birthday, 
                    address,roomName,expirateDate, imageBuff).show();
        } catch (WriterException ex) {
           logger.log(Level.SEVERE, ex.getMessage(), ex.getCause());
        }
    }
}
