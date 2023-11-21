/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/module-info.java to edit this template
 */

module FitnessApp {
    requires com.formdev.flatlaf;
    requires com.formdev.flatlaf.extras;
    requires com.formdev.flatlaf.fonts.roboto;
    requires AbsoluteLayout;
    requires datechooser;
    requires com.google.gson;
    requires swing.toast.notifications;
    requires org.xerial.sqlitejdbc;
    requires org.slf4j;
    requires com.github.weisj.jsvg;
    requires com.google.zxing;
    requires com.google.zxing.javase;
    requires java.base;
    requires java.net.http;
    requires MaiFetch;
    requires MaiDateCompare;
    requires java.desktop;
    requires java.sql;
    requires java.logging;
    
    exports fitnessapp.models;
}
