/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.controllers;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import fitnessapp.components.AccountPopup;
import fitnessapp.screens.Login;
import fitnessapp.utilities.Constants;
import fitnessapp.utilities.Database;
import java.awt.Point;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author maisyst
 */
public class AccountPopupController {
    private final AccountPopup accountPopup;
    private final JFrame parent;
    public AccountPopupController(JFrame parent,Point point){
        this.parent=parent;
        this.accountPopup=new AccountPopup(parent, true);
        point.y+=200;
        accountPopup.setLocation(point);
        accountPopup.getSignOut().setIcon(new FlatSVGIcon(Constants.ICONS_PATH+"signOut.svg"));
        accountPopup.getChangePassword().setIcon(new FlatSVGIcon(Constants.ICONS_PATH+"changePassword.svg"));
        accountPopup.getSignOut().addActionListener(l->signOut());
    }
    private void signOut() {
        accountPopup.dispose();
        var options = JOptionPane.showConfirmDialog(null, "Etes-vous sure de connecter?", "Deconnection", JOptionPane.OK_CANCEL_OPTION);
        if (options == JOptionPane.OK_OPTION) {
            try {
                var stmt = Database.getInstance().createStatement();
                stmt.execute("DELETE FROM auth");
                new Login().setVisible(true);
                parent.dispose();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    public void show(){
        accountPopup.setVisible(true);
    }
    public void dispose(){
        accountPopup.dispose();
    }
}
