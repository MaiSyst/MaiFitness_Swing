/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.controllers;

import fitnessapp.screens.CoachModal;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author orion90
 */
public class CoachModalController {

    private CoachModal coachModal;

    public CoachModalController() {
        coachModal = new CoachModal();
        coachModal.getBtnClose().addActionListener(l -> coachModal.dispose());
        coachModal.getPhoneNumber().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                var val = coachModal.getPhoneNumber().getText().trim();
                final char c = e.getKeyChar();
                if (!Character.isDigit(c) || val.length() > 10) {
                    e.consume();
                } else {
                    val = val.replaceAll("-", "");
                    if (val.length() % 2 == 0 && val.length() != 0) {
                        coachModal.getPhoneNumber().setText(coachModal.getPhoneNumber().getText() + "-");
                    }
                }

            }

        });
    }

    public void show(JFrame parent) {
        coachModal.getAccessibleContext().setAccessibleParent(parent);
        coachModal.setVisible(true);
    }
}
