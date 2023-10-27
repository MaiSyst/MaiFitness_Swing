/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.controllers;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author orion90
 */
public class CoachController {
    private JTextField search;
    private JButton removeCoach;
    private JButton addCoach;
    private JFrame parent;

    public CoachController(JTextField search, JButton removeCoach, JButton addCoach, JFrame parent) {
        this.search = search;
        this.removeCoach = removeCoach;
        this.addCoach = addCoach;
        this.parent = parent;
        addCoach.addActionListener(l -> onHandleShowModal());
        addCoach.setIcon(new FlatSVGIcon("fitnessapp/icons/plus.svg", 1f));
        removeCoach.setIcon(new FlatSVGIcon("fitnessapp/icons/trash.svg", 1f));
        search.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Rechercher votre entraineur...");
    }
    private void onHandleShowModal() {
        new CoachModalController().show(parent);
    }
    
}
