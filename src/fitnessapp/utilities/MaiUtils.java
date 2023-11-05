/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.utilities;

import fitnessapp.models.ActivityModel;
import fitnessapp.models.RoomWithSubscribeModel;
import fitnessapp.models.SubscriptionModel;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author orion90
 */
public class MaiUtils {

    public static final class MaiComboxBoxCell extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value instanceof ActivityModel model) {
                value = model.label();
            }else if (value instanceof RoomWithSubscribeModel model) {
                value = model.roomName();
            }
            else if (value instanceof SubscriptionModel model) {
                var val=model.type()+ " / " + model.price() + "FCFA / " + model.label();
                value = val;
            }
            
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        }

    }

    public static String dateToEnglish(String day) {
        return switch (day.toLowerCase()) {
            case "lundi" ->
                "MONDAY";
            case "mardi" ->
                "TUESDAY";
            case "mercredi" ->
                "WEDNESDAY";
            case "jeudi" ->
                "THURSDAY";
            case "vendredi" ->
                "FRIDAY";
            case "samedi" ->
                "SATURDAY";
            case "dimanche" ->
                "SUNDAY";
            default ->
                null;
        };
    }

    public static String dateToFrench(String day) {
        return switch (day) {
            case "MONDAY" ->
                "Lundi";
            case "TUESDAY" ->
                "Mardi";
            case "WEDNESDAY" ->
                "Mercredi";
            case "THURSDAY" ->
                "Jeudi";
            case "FRIDAY" ->
                "Vendredi";
            case "SATURDAY" ->
                "Samedi";
            case "SUNDAY" ->
                "Dimanche";
            default ->
                null;
        };
    }
}
