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
import fitnessapp.screens.PublicDashboard;
import fitnessapp.utilities.API;
import fitnessapp.utilities.Constants;
import fitnessapp.utilities.Database;
import java.awt.Font;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
        final String[] fileToLoad=new String[]{
        "Initialisation...",
        "Initialisation de base de donnée...",
        "Verification de compte EMF...",
        "Près à utiliser EMF..."
        };
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
               
                if(i<=25&&i>0){
                    splashScreen.getMsgLoader().setText(fileToLoad[0]);
                }
                if(i>25&&i<60){
                    splashScreen.getMsgLoader().setText(fileToLoad[1]);
                }
                if(i>60&&i<90){
                    splashScreen.getMsgLoader().setText(fileToLoad[2]);
                }
                if(i>90){
                    splashScreen.getMsgLoader().setText(fileToLoad[3]);
                }
                var delay = Math.round(Math.random() * 80);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (rs.next()) {
                checkToken(rs.getString("authToken"))
                        .ifPresentOrElse(authResponse -> 
                                    SwingUtilities.invokeLater(() -> {
                                        try{
                                            if (authResponse.role().toLowerCase().equals("admin")) {
                                            Dashboard dashboard=new Dashboard(authResponse);
                                            dashboard.setIconImage(
                                                    ImageIO.read(new File(Constants.ICONS_PATH+"emf50x50.png")));
                                            dashboard.setVisible(true);
                                        } else {
                                            new PublicDashboard(authResponse).setVisible(true);
                                        }
                                        }
                                        catch(Exception e){}

                                        splashScreen.dispose();
                                    }),
                                () -> {
                                    try {
                                        stmt.execute("DELETE FROM auth");
                                    } catch (SQLException e) {
                                        Logger.getLogger(FitnessApp.class.getName()).log(Level.SEVERE,e.getMessage());
                                    }
                                    SwingUtilities.invokeLater(() -> new Login().setVisible(true));
                                    splashScreen.dispose();
                                });

            } else {
                SwingUtilities.invokeLater(() -> new Login().setVisible(true));
                splashScreen.dispose();
            }
        } 
        catch (SQLException ex) {
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
