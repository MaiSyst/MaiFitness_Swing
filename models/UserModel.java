/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.models;

/**
 *
 * @author maisyst
 */
public record UserModel(String userId,String username, String firstName, String lastName, String date, String address,
                     String phoneNumber, RoomModel room, String password, boolean isActive, String role) {}
