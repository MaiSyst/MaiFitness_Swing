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
    requires MaiFetch;
    requires java.net.http;
    requires java.desktop;
    requires com.github.weisj.jsvg;
    exports fitnessapp.models;
}
