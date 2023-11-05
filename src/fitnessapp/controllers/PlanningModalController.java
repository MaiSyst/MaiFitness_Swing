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
import fitnessapp.models.RoomWithSubscribeModel;
import fitnessapp.screens.PlanningModal;
import fitnessapp.utilities.Constants;
import fitnessapp.utilities.MaiFunctionCall;
import fitnessapp.utilities.MaiUtils;
import fitnessapp.utilities.MaiUtils.MaiComboxBoxCell;
import java.awt.Color;
import java.awt.Component;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JList;
import raven.toast.Notifications;

/**
 *
 * @author orion90
 */
public final class PlanningModalController {

    private final JFrame parent;
    private final PlanningModal planningModal;
    private final MaiFetch fetch;
    private final Gson gson = new Gson();
    private final MaiFunctionCall functionCall;

    public PlanningModalController(final JFrame parent, final MaiFetch fetch, MaiFunctionCall functionCall) {
        this.parent = parent;
        this.fetch = fetch;
        planningModal = new PlanningModal(parent, true);
        planningModal.getCloseModal().addActionListener(l -> planningModal.dispose());
        planningModal.getAddNewPlanning().addActionListener(l -> addNewPlanning());
        planningModal.getAddNewPlanning().setIcon(new FlatSVGIcon(Constants.ICONS_PATH+"plus.svg"));
        planningModal.getAddNewPlanning().setText("Valider");
        planningModal.getAddNewPlanning().setForeground(Color.WHITE);
        fetchActivities();
        fetchRoom();
        this.functionCall = functionCall;
    }

    public PlanningModalController(final JFrame parent, final MaiFetch fetch,final String planningId, final String day, final String timeStart, final String timeEnd, final String activity, final String roomName, MaiFunctionCall functionCall) {
        this.fetch = fetch;
        this.parent=parent;
        planningModal = new PlanningModal(parent, true);
        planningModal.getCloseModal().addActionListener(l -> planningModal.dispose());
        planningModal.getAddNewPlanning().addActionListener(l -> editPlanning(planningId));
        planningModal.getAddNewPlanning().setIcon(new FlatSVGIcon(Constants.ICONS_PATH+"edit.svg"));
        planningModal.getAddNewPlanning().setText("Modifier");
        planningModal.getAddNewPlanning().setForeground(Color.WHITE);
        planningModal.getComboBoxDay().setSelectedItem(day);
        planningModal.getComboHourStart().setSelectedItem(timeStart.split(":")[0]);
        planningModal.getComboMMStart().setSelectedItem(timeStart.split(":")[1]);
        planningModal.getComboHourEnd().setSelectedItem(timeEnd.split(":")[0]);
        planningModal.getComboMMEnd().setSelectedItem(timeEnd.split(":")[1]);
        fetchActivities();
        fetchRoom();
        for(var i=0;i<planningModal.getComboActivities().getItemCount();i++){
            if(planningModal.getComboActivities().getItemAt(i).label().equals(activity)){
                planningModal.getComboActivities().setSelectedIndex(i);
                break;
            }
        }
        for(var i=0;i<planningModal.getComboRoom().getItemCount();i++){
            if(planningModal.getComboRoom().getItemAt(i).roomName().equals(roomName)){
                planningModal.getComboRoom().setSelectedIndex(i);
                break;
            }
        }
        this.functionCall = functionCall;
    }

    private final void fetchActivities() {
        try {
            fetch.get(Constants.ACTIVITY_FETCH_URL_PATH).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    final Type activityListType = new TypeToken<List<ActivityModel>>() {
                    }.getType();
                    List<ActivityModel> models = gson.fromJson(result, activityListType);
                    DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) planningModal.getComboActivities().getModel();
                    comboBoxModel.removeAllElements();
                    models.forEach(model -> {
                        comboBoxModel.addElement(model);
                    });
                    planningModal.getComboActivities().setRenderer(new MaiComboxBoxCell());
                }
            });
        } catch (MaiException e) {
            System.out.println(e.getMessage());
        }
    }

    private final void fetchRoom() {
        try {
            fetch.get(Constants.ROOM_FETCH_SUBSC_URL_PATH).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    final Type roomListType = new TypeToken<List<RoomWithSubscribeModel>>() {
                    }.getType();
                    List<RoomWithSubscribeModel> models = gson.fromJson(result, roomListType);
                    DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) planningModal.getComboRoom().getModel();
                    comboBoxModel.removeAllElements();
                    models.forEach(model -> {
                        comboBoxModel.addElement(model);
                    });
                    planningModal.getComboRoom().setRenderer(new MaiComboxBoxCell());
                }
            });
        } catch (MaiException e) {
            System.out.println(e.getMessage());
        }
    }

    public final void show() {
        planningModal.setVisible(true);
    }
    
    private void addNewPlanning() {
        var activityId = ((ActivityModel) planningModal.getComboActivities().getSelectedItem()).activityId();
        var roomId = ((RoomWithSubscribeModel) planningModal.getComboRoom().getSelectedItem()).roomId();
        var day = planningModal.getComboBoxDay().getSelectedItem().toString();
        var heureStart = planningModal.getComboHourStart().getSelectedItem().toString() + ":" + planningModal.getComboMMStart().getSelectedItem().toString();
        var heureEnd = planningModal.getComboHourEnd().getSelectedItem().toString() + ":" + planningModal.getComboMMEnd().getSelectedItem().toString();

        try {
            System.out.println(day);
            Map<String, Object> body = new HashMap<>();
            body.put("day", MaiUtils.dateToEnglish(day));
            body.put("startTime", heureStart + ":00");
            body.put("endTime", heureEnd + ":00");
            fetch.post(Constants.PLANNING_ADD_URL_PATH + activityId + "/" + roomId, body)
                    .then((result, status) -> {
                        if (status == ResponseStatusCode.OK) {
                            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Planning a été ajouté.");
                            this.functionCall.invoked();
                        } else {
                            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, result);
                        }
                    });
        } catch (MaiException e) {
            Logger.getLogger(PlanningModalController.class.getName()).log(Level.SEVERE, e.getMessage(), e);

        }
    }
    
    private void editPlanning(final String planningId) {
        var activityId = ((ActivityModel) planningModal.getComboActivities().getSelectedItem()).activityId();
        var roomId = ((RoomWithSubscribeModel) planningModal.getComboRoom().getSelectedItem()).roomId();
        var day = planningModal.getComboBoxDay().getSelectedItem().toString();
        var heureStart = planningModal.getComboHourStart().getSelectedItem().toString() + ":" + planningModal.getComboMMStart().getSelectedItem().toString();
        var heureEnd = planningModal.getComboHourEnd().getSelectedItem().toString() + ":" + planningModal.getComboMMEnd().getSelectedItem().toString();

        try {
            Map<String, Object> body = new HashMap<>();
            body.put("day", MaiUtils.dateToEnglish(day));
            body.put("startTime", heureStart + ":00");
            body.put("endTime", heureEnd + ":00");
            fetch.put(Constants.PLANNING_UPDATE_URL_PATH + activityId + "/" + roomId+"/"+planningId, body)
                    .then((result, status) -> {
                        if (status == ResponseStatusCode.OK) {
                            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Planning a été mise a jour.");
                            this.functionCall.invoked();
                            planningModal.dispose();
                        } else {
                            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, result);
                        }
                    });
        } catch (MaiException e) {
            Logger.getLogger(PlanningModalController.class.getName()).log(Level.SEVERE, e.getMessage(), e);

        }
    }
}
