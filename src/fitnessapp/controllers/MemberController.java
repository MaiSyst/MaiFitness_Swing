/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.controllers;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.maisyst.MaiFetch;
import com.maisyst.utils.Authorization;
import fitnessapp.utilities.API;
import fitnessapp.utilities.Constants;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

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
    public MemberController(
            final JButton removeCustomer, 
            final JButton addCustomer, 
            final JTable table, final JTextField search, 
            final JFrame parent,final String token) {
        this.removeCustomer = removeCustomer;
        this.addCustomer = addCustomer;
        this.table = table;
        this.search = search;
        this.parent = parent;
        this.fetch=API.fetch(new Authorization(token));
        addCustomer.addActionListener(l -> onHandleShowModal());
        addCustomer.setIcon(new FlatSVGIcon(Constants.ICONS_PATH+"plus.svg", 1f));
        removeCustomer.setIcon(new FlatSVGIcon(Constants.ICONS_PATH+"trash.svg", 1f));
        search.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Rechercher par prenom ou nom...");
    }

    private void onHandleShowModal() {
        new MemberModalController(fetch).show(parent);
    }
}
