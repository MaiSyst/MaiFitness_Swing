/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.controllers;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author orion90
 */
public class RoomController {

    private JButton removeRoom;
    private JButton addRoom;
    private JTable table;
    private JTextField search;
    private JFrame parent;

    public RoomController(JFrame parent, JButton removeRoom, JButton addRoom, JTable table, JTextField search) {
        this.removeRoom = removeRoom;
        this.addRoom = addRoom;
        this.table = table;
        this.search = search;
        this.parent = parent;
        addRoom.addActionListener(l -> onHandleShowModal());
        addRoom.setIcon(new FlatSVGIcon("fitnessapp/icons/plus.svg", 1f));
        removeRoom.setIcon(new FlatSVGIcon("fitnessapp/icons/trash.svg", 1f));
        search.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Rechercher votre salle...");
    }

    private void onHandleShowModal() {
        new RoomModalController().show(parent);
    }

}
