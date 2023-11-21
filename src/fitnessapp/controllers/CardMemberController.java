/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.controllers;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import fitnessapp.components.CardMember;
import fitnessapp.utilities.Constants;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author maisyst
 */
public final class CardMemberController {
    private final CardMember cardMember;
    public CardMemberController(JFrame parent,String identity,String firstName,String lastName,String birthDate,String address,BufferedImage imageBuffered){
        cardMember=new CardMember(parent, true);
        cardMember.getCard().putClientProperty(FlatClientProperties.STYLE, "arc:30;background:#ffffff");
        cardMember.getBtnClose().addActionListener(l->cardMember.dispose());
        cardMember.getBtnClose().setIcon(new FlatSVGIcon(Constants.ICONS_PATH+"close.svg"));
        cardMember.getBtnPrint().setIcon(new FlatSVGIcon(Constants.ICONS_PATH+"printer.svg"));
        cardMember.getBtnPrint().addActionListener(l->printCardMember());
        cardMember.getFirstName().setText(firstName.toUpperCase());
        cardMember.getLastName().setText(lastName.toUpperCase());
        cardMember.getAddress().setText(address);
        cardMember.getBirthday().setText(birthDate);
        cardMember.getIdentity().setText(identity);
        cardMember.getQrCode().setIcon(new ImageIcon(imageBuffered));
    }
    private void printCardMember(){
        try {
           PrinterJob printerJob=PrinterJob.getPrinterJob();
            var services=PrinterJob.lookupPrintServices();
            System.out.println(services.length);
//           var isPrint=printerJob.printDialog();
//           if(isPrint){
//               System.out.println("Print");
//               printerJob.print();
//           }
        } catch (Exception ex) {
            Logger.getLogger(CardMemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void show(){
        cardMember.setVisible(true);
    }
}
