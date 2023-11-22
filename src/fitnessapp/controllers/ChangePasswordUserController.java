/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.controllers;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.maisyst.MaiFetch;
import com.maisyst.exceptions.MaiException;
import com.maisyst.utils.enums.ResponseStatusCode;
import fitnessapp.components.ChangePasswordUser;
import fitnessapp.utilities.Constants;
import fitnessapp.utilities.MaiUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.JFrame;
import raven.toast.Notifications;

/**
 *
 * @author maisyst
 */
public class ChangePasswordUserController {

    private final ChangePasswordUser changePasswordUser;
    private final MaiFetch fetch;

    public ChangePasswordUserController(final JFrame parent, final MaiFetch fetch, String username) {
        changePasswordUser = new ChangePasswordUser(parent, true);
        this.fetch = fetch;
        changePasswordUser.getBtnChangePassword().addActionListener(l -> onHandleChangePassword(username));
        changePasswordUser.getBtnClose().setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "close.svg"));
        changePasswordUser.getUndescore().putClientProperty(FlatClientProperties.STYLE, "arc:20");
        changePasswordUser.getBtnClose().addActionListener(l -> changePasswordUser.dispose());
        changePasswordUser.getInputConfPassword().putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true");
        changePasswordUser.getInputPassword().putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true");
        changePasswordUser.getInputConfPassword().putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Entrer votre mot de passe");
        changePasswordUser.getInputPassword().putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Entrer votre mot de passe");
        changePasswordUser.getInputConfPassword().putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon(Constants.ICONS_PATH + "pwd.svg"));
        changePasswordUser.getInputPassword().putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon(Constants.ICONS_PATH + "pwd.svg"));
        changePasswordUser.getBtnChangePassword().setIcon(new FlatSVGIcon("fitnessapp/icons/changePassword.svg"));
    }

    private void onHandleChangePassword(String username) {
        var pwd = MaiUtils.getTextPassword(changePasswordUser.getInputPassword());
        var confPwd = MaiUtils.getTextPassword(changePasswordUser.getInputConfPassword());
        if (pwd.isBlank() || confPwd.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Les champs ne peut être vide.");
        } else {
            if (!pwd.equals(confPwd)) {
                changePasswordUser.getMsgPassword().setText("Le mot de passe n'est pas conforme.");
            } else {
                changePasswordUser.getMsgPassword().setText("");
                Map<String, Object> body = new HashMap<>();
                body.put("arg", pwd);
                try {
                    fetch.post(Constants.USER_UPDATE_PASSWORD_URL_PATH + "/" + username, body)
                            .then((result, status) -> {
                                if (status == ResponseStatusCode.OK) {
                                    Notifications.getInstance().show(Notifications.Type.SUCCESS,
                                            "Votre Mot de passe à été mise à jour.");
                                    changePasswordUser.getInputConfPassword().setText("");
                                    changePasswordUser.getInputPassword().setText("");
                                    changePasswordUser.dispose();
                                } else {
                                    Notifications.getInstance().show(Notifications.Type.ERROR,
                                            "Désolé réessayer plustard.");
                                }
                            });
                } catch (MaiException e) {
                    Logger.getLogger(ChangePasswordUserController.class.getName(), e.getMessage());
                }
            }
        }
    }

    public void show() {
        changePasswordUser.setVisible(true);
    }

    public void dispose() {
        changePasswordUser.dispose();
    }
}
