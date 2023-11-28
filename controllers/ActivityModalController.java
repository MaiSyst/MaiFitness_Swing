/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.controllers;

import com.maisyst.MaiFetch;
import com.maisyst.exceptions.MaiException;
import com.maisyst.utils.enums.ResponseStatusCode;
import fitnessapp.screens.ActivityModal;
import fitnessapp.utilities.Constants;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JTable;
import raven.toast.Notifications;
import fitnessapp.utilities.IMaiRefreshTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author orion90
 */
public class ActivityModalController {

    private ActivityModal activityModal;
    private final MaiFetch fetch;
    private final JTable table;
    private final IMaiRefreshTable refreshTable;

    public ActivityModalController(MaiFetch fetch, JTable table, IMaiRefreshTable refreshTable) {
        this.fetch = fetch;
        this.table = table;
        this.refreshTable = refreshTable;
        activityModal = new ActivityModal();
        activityModal.getBtnClose().addActionListener(l -> activityModal.dispose());
        activityModal.getBtnAdded().addActionListener(l -> addActivity());
        activityModal.getDescriptionActivity().addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e); 
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    addActivity();
                }
            }
            
        });
    }

    public ActivityModalController(MaiFetch fetch, JTable table, String id, String labelActivity, String descriptionActivity, IMaiRefreshTable refreshTable) {
        this.fetch = fetch;
        this.table = table;
        this.refreshTable = refreshTable;
        activityModal = new ActivityModal();
        activityModal.getLabelActivity().setText(labelActivity);
        activityModal.getDescriptionActivity().setText(descriptionActivity);
        activityModal.getBtnAdded().setText("Modifier");
        activityModal.getBtnClose().addActionListener(l -> activityModal.dispose());
        activityModal.getBtnAdded().addActionListener(l -> editActivity(id));
        activityModal.getDescriptionActivity().addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e); 
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    editActivity(id);
                }
            }
            
        });
    }

    public void show(JFrame parent) {
        activityModal.setVisible(true);
    }

    private void addActivity() {
        var label = activityModal.getLabelActivity().getText().toLowerCase();
        var desc = activityModal.getDescriptionActivity().getText().toLowerCase();
        if (label.isBlank() || desc.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Verifier vos champs si n'est pas vides");
        } else {

            try {
                Map<String, Object> body = new HashMap<>();
                body.put("label", label);
                body.put("description", desc);

                fetch.post(Constants.ACTIVITY_ADD_URL_PATH, body)
                        .then((result, status) -> {
                            if (status == ResponseStatusCode.OK) {
                                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Une Activite a ete ajoute");
                                refreshTable.refresh();
                                activityModal.getLabelActivity().setText("");
                                activityModal.getDescriptionActivity().setText("");
                            } else {
                                if (result.toLowerCase().contains("duplicate")) {
                                    Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Cette actvite exist déjà.");

                                } else if (result.toLowerCase().contains("data truncation")) {
                                    Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Votre description est trop longue,la limite est 255 caractères.");

                                } else {
                                    Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Erreur du serveur.");

                                }
                            }
                        });
            } catch (MaiException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void editActivity(String id) {
        var label = activityModal.getLabelActivity().getText().toLowerCase();
        var desc = activityModal.getDescriptionActivity().getText().toLowerCase();
        if (label.isBlank() || desc.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Verifier vos champs si n'est pas vides");
        } else {

            try {
                Map<String, Object> body = new HashMap<>();
                body.put("label", label);
                body.put("description", desc);

                fetch.put(Constants.ACTIVITY_UPDATE_URL_PATH + id, body)
                        .then((result, status) -> {
                            if (status == ResponseStatusCode.OK) {
                                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Activite a ete mise a jour");
                                refreshTable.refresh();
                                activityModal.dispose();
                            } else {

                                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, result);
                            }
                        });
            } catch (MaiException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
