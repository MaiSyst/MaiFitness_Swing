/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.controllers;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import fitnessapp.screens.MemberModal;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author orion90
 */
public class MemberController {
     private JButton removeCustomer;
    private JButton addCustomer;
    private JTable table;
    private JTextField search;
    private JFrame parent; 

    public MemberController(JButton removeCustomer, JButton addCustomer, JTable table, JTextField search, JFrame parent) {
        this.removeCustomer = removeCustomer;
        this.addCustomer = addCustomer;
        this.table = table;
        this.search = search;
        this.parent = parent;
        addCustomer.addActionListener(l -> onHandleShowModal());
        addCustomer.setIcon(new FlatSVGIcon("fitnessapp/icons/plus.svg", 1f));
        removeCustomer.setIcon(new FlatSVGIcon("fitnessapp/icons/trash.svg", 1f));
        search.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Rechercher votre salle...");
    }
    private void onHandleShowModal() {
        new MemberModalController().show(parent);
    }
}
