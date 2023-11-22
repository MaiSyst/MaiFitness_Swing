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
public record CustomerWithSubscribesModel(String customerId,
        String firstName,String lastName,String yearOfBirth,
        String address,String identityEMF,String roomName,List<SubscribeInCustomerModel>subscribes) {}
