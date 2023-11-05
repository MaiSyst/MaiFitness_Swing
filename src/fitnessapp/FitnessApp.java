/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fitnessapp;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.google.gson.Gson;
import com.maisyst.exceptions.MaiException;
import com.maisyst.utils.enums.ResponseStatusCode;
import fitnessapp.components.MaiSplashScreen;
import fitnessapp.models.AuthResponse;
import fitnessapp.screens.Dashboard;
import fitnessapp.screens.Login;
import fitnessapp.utilities.API;
import fitnessapp.utilities.Database;
import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import raven.toast.Notifications;

/**
 *
 * @author orion90
 */
public class FitnessApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final Connection connection = Database.getInstance();
        FlatRobotoFont.install();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 15));
        FlatMacLightLaf.registerCustomDefaultsSource("fitnessapp.properties");
        FlatMacLightLaf.setup();

        MaiSplashScreen splashScreen = new MaiSplashScreen();
        splashScreen.setVisible(true);

        try {
            final var stmt = connection.createStatement();
            var rs = stmt.executeQuery("SELECT * FROM auth");
            for (var i = 0; i <= 100; i++) {
                splashScreen.getProgressPercentage().setText(i + "%");
                var delay = Math.round(Math.random() * 80);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (rs.next()) {
                checkToken(rs.getString("authToken"))
                        .ifPresentOrElse(
                                authResponse -> {
                                    SwingUtilities.invokeLater(() -> {
                                        new Dashboard(authResponse).setVisible(true);
                                        splashScreen.dispose();
                                    });
                                },
                                () -> {
                                    try {
                                        stmt.execute("DELETE FROM auth");
                                    } catch (SQLException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    SwingUtilities.invokeLater(() -> new Login().setVisible(true));
                                    splashScreen.dispose();
                                });

            } else {
                SwingUtilities.invokeLater(() -> new Login().setVisible(true));
                splashScreen.dispose();
            }
        } catch (SQLException ex) {
            Logger.getLogger(FitnessApp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static Optional<AuthResponse> checkToken(String token) {
        final AtomicReference<AuthResponse> auth = new AtomicReference<>(null);
        try {
            Map<String, Object> body = new HashMap<>();
            body.put("token", token);
            API.fetch().post("/auth/checkToken", body).then((result, status) -> {
                if (status == ResponseStatusCode.OK) {
                    Gson gson = new Gson();
                    auth.set(gson.fromJson(result, AuthResponse.class));
                } else {
                    auth.set(null);
                }
            });
            return auth.get() == null ? Optional.empty() : Optional.of(auth.get());
        } catch (MaiException e) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Désolé :(, Serveur n'est pas disponible");
            return Optional.empty();
        }
    }

}
