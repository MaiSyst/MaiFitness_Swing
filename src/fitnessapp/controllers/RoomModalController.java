/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.controllers;

import fitnessapp.screens.RoomModal;
import javax.swing.JFrame;

/**
 *
 * @author orion90
 */
public class RoomModalController {
    
    private RoomModal roomModal;
    public RoomModalController(){
        roomModal=new RoomModal();
        roomModal.getBtnClose().addActionListener(l->roomModal.dispose());
    }
    
    public void show(JFrame parent){
        roomModal.getAccessibleContext().setAccessibleParent(parent);
        roomModal.setVisible(true);
    }
}
