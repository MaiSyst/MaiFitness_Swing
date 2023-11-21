/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.controllers;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import fitnessapp.components.RoomDetails;
import fitnessapp.utilities.Constants;
import javax.swing.JFrame;

/**
 *
 * @author maisyst
 */
public class RoomDetailsController {
    private final RoomDetails roomDetails;
    public RoomDetailsController(
            final JFrame parent,String roomName,
            String numberPlanning,String numberSubscribe,
            String subscriptionGoldTotal,
            String subscriptionStandardTotal,
            String subscriptionPrimeTotal,
            String nameManager
            ){
        roomDetails=new RoomDetails(parent, true);
        roomDetails.getBtnClose().addActionListener(l->roomDetails.dispose());
        roomDetails.getBtnClose().setIcon(new FlatSVGIcon(Constants.ICONS_PATH+"close.svg"));
        roomDetails.getRoomName().setText(roomName);
        roomDetails.getSubscriptionGoldTotal().setText(subscriptionGoldTotal);
        roomDetails.getSubscriptionStandardTotal().setText(subscriptionStandardTotal);
        roomDetails.getSubscriptionPrimeTotal().setText(subscriptionPrimeTotal);
        roomDetails.getNumberPlanning().setText(numberPlanning);
        roomDetails.getNumbreSubscribe().setText(numberSubscribe);
        roomDetails.getNameManager().setText(nameManager);
    }
    
    public void show(){
        roomDetails.setVisible(true);
    }
}
