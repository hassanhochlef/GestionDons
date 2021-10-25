/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import Entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.UserSession;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.NotificationAPI;

/**
 * FXML Controller class
 *
 * @author SeifD
 */
public class LoginScreenController implements Initializable {

    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXTextField mailInput;
    @FXML
    private JFXTextField passwordInput;
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    
    UserSession us = new UserSession();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnLoginAction(ActionEvent event) throws IOException {
        
        String mail = mailInput.getText();
        String pw = passwordInput.getText();
        if (us.verifyUser(mail, pw)){
            User u = us.getUserConnected(mail);
            us.addUserSession(u);
        
            
            
            NotificationAPI.SuccessNotification("Vous vous êtes bien connecté ! !","");
            
            Parent root = FXMLLoader.load(getClass().getResource("/views/EventsMain.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }
        else{
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            String tilte = "Erreur login";
            String message = "Veuillez vérifier les champs d'authentification!";
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(3));
        }
    }
    
}
