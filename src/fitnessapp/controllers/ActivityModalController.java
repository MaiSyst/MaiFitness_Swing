/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.controllers;

import fitnessapp.screens.ActivityModal;
import javax.swing.JFrame;
/**
 *
 * @author orion90
 */
public class ActivityModalController {
   
    private ActivityModal activityModal;
    public ActivityModalController(){
        activityModal=new ActivityModal();
        activityModal.getBtnClose().addActionListener(l->activityModal.dispose());
    }
    
    public void show(JFrame parent){
        activityModal.getAccessibleContext().setAccessibleParent(parent);
        activityModal.setVisible(true);
    }
       
}
