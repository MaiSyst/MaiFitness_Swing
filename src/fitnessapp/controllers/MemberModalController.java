/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.controllers;

import fitnessapp.screens.MemberModal;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author orion90
 */
public class MemberModalController {
    private final MemberModal modal;
    public MemberModalController(){
        this.modal=new MemberModal();
        modal.getBtnClose().addActionListener(l->modal.dispose());
    }
    public void show(JFrame parent){
        modal.getAccessibleContext().setAccessibleParent(parent);
        modal.setVisible(true);
    }
    
}
