/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.models;

/**
 *
 * @author orion90
 */
public record SubscribeInCustomerModel(
        String subscribeId,String dateStart,String dateEnd,boolean isActive,String subscriptionType){}
