/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.controllers;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.maisyst.MaiFetch;
import com.maisyst.exceptions.MaiException;
import com.maisyst.utils.enums.ResponseStatusCode;
import fitnessapp.models.ActivityModel;
import fitnessapp.screens.CoachModal;
import fitnessapp.utilities.Constants;
import fitnessapp.utilities.MaiFunctionCall;
import fitnessapp.utilities.MaiUtils.MaiComboxBoxCell;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import raven.toast.Notifications;

/**
 *
 * @author orion90
 */
public final class CoachModalController {

    private final CoachModal coachModal;
    private final Gson gson = new Gson();
    private final MaiFetch fetch;
    private final MaiFunctionCall functionCall;

    public CoachModalController(final MaiFetch fetch, final MaiFunctionCall functionCall) {
        this.fetch = fetch;
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
        coachModal.getBtnAdded().addActionListener(l -> addNewCoach());
        coachModal.getBtnAdded().setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "plus.svg"));
        coachModal.getBtnAdded().setText("Ajouter");
        this.functionCall = functionCall;
        fetchActivities(null);
    }

    public CoachModalController(final MaiFetch fetch,
            String coachId,
            String firstName,
            String lastName,
            String phone,
            String address,
            String speciality,
            final MaiFunctionCall functionCall) {
        this.fetch = fetch;
        coachModal = new CoachModal();
        coachModal.getBtnClose().addActionListener(l -> coachModal.dispose());
        this.functionCall = functionCall;
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
        coachModal.getBtnAdded().addActionListener(l -> editCoach(coachId));
        coachModal.getBtnAdded().setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "edit.svg"));
        coachModal.getBtnAdded().setText("Modifier");
        coachModal.getTxtFirstname().setText(firstName);
        coachModal.getTxtLastname().setText(lastName);
        coachModal.getTxtAddress().setText(address);
        coachModal.getPhoneNumber().setText(phone);

        fetchActivities(speciality);
    }

    private void addNewCoach() {
        var firstName = coachModal.getTxtFirstname().getText();
        var lastName = coachModal.getTxtLastname().getText();
        var phone = coachModal.getPhoneNumber().getText();
        var address = coachModal.getTxtAddress().getText().replaceAll("-", "");
        var activity = (ActivityModel) coachModal.getComboxActivity().getSelectedItem();
        if (firstName.isBlank() || lastName.isBlank() || phone.isBlank() || address.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Verifier que si l'un des champs n'est pas.");
        } else {
            try {
                Map<String, Object> body = new HashMap<>();
                body.put("firstName", firstName);
                body.put("lastName", lastName);
                body.put("phone", phone);
                body.put("address", address);
                fetch.post(Constants.COACH_ADD_URL_PATH + activity.activityId(), body).then((result, status) -> {
                    if (status == ResponseStatusCode.OK) {
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Un entraineur à été ajouté.");
                        functionCall.invoked();
                    } else {
                        if (result.toLowerCase().contains("duplicate entry")) {
                            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Ce numero de telephone exist deja.");

                        } else {
                            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, result);

                        }
                    }
                });
            } catch (MaiException e) {
                Logger.getLogger(CoachModalController.class.getName(), e.getMessage());
            }
        }
    }

    private void editCoach(String coachId) {
        var firstName = coachModal.getTxtFirstname().getText();
        var lastName = coachModal.getTxtLastname().getText();
        var phone = coachModal.getPhoneNumber().getText();
        var address = coachModal.getTxtAddress().getText();
        var activity = (ActivityModel) coachModal.getComboxActivity().getSelectedItem();
        if (firstName.isBlank() || lastName.isBlank() || phone.isBlank() || address.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Verifier que si l'un des champs n'est pas.");
        } else {
            try {
                Map<String, Object> body = new HashMap<>();
                body.put("firstName", firstName);
                body.put("lastName", lastName);
                body.put("phone", phone);
                body.put("address", address);
                fetch.put(Constants.COACH_UPDATE_URL_PATH + activity.activityId() + "/" + coachId, body).then((result, status) -> {
                    if (status == ResponseStatusCode.OK) {
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Un entraineur à été mise à jour.");
                        functionCall.invoked();
                    } else {
                        Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, result);
                    }
                });
            } catch (MaiException e) {
                Logger.getLogger(CoachModalController.class.getName(), e.getMessage());
            }
        }
    }

    private void fetchActivities(String sp) {
        try {
            fetch.get(Constants.ACTIVITY_FETCH_URL_PATH).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    final Type activityListType = new TypeToken<List<ActivityModel>>() {
                    }.getType();
                    final List<ActivityModel> models = gson.fromJson(result, activityListType);
                    final DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) coachModal.getComboxActivity().getModel();
                    comboBoxModel.removeAllElements();
                    if (sp == null) {
                        models.forEach(model -> comboBoxModel.addElement(model));
                    } else {
                        models.forEach(model -> {
                            comboBoxModel.addElement(model);
                            if (model.label().equals(sp)) {
                                comboBoxModel.setSelectedItem(model);
                            }
                        });
                    }
                    coachModal.getComboxActivity().setRenderer(new MaiComboxBoxCell());
                }
            });
        } catch (MaiException e) {
            System.out.println(e.getMessage());
        }
    }

    public void show() {
        coachModal.setVisible(true);
    }
}
