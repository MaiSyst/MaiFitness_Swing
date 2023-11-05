/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.models;

import java.util.List;

/**
 *
 * @author orion90
 */
public record RoomModel(String roomId,String roomName,List<PlanningModel>planning) {}
