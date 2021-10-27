/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import Service.UserService;
import Connection.MyConnection;
import javafx.scene.control.PasswordField;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField inputname;
    @FXML
    private TextField inputphone;
    @FXML
    private TextField inputmail;
    @FXML
    private TextField inputville;
    @FXML
    private ComboBox<String> inputrole;
    
    @FXML
    private Button signup;
    @FXML
    private PasswordField inputpassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UserService us =new UserService();
         inputrole.getItems().add("Donneur");
        inputrole.getItems().add("Association ou Club");
    }    

    @FXML
    private void signupAction(ActionEvent event) {
        
        UserService us = new UserService();
          String name = inputname.getText();
                    String phone = inputphone.getText();
                    String mail = inputmail.getText();
                    String city = inputville.getText();
                    String role = inputrole.getSelectionModel().getSelectedItem();
                    String password = inputpassword.getText();
         if (inputname.getText().isEmpty() || inputphone.getText().isEmpty() || inputmail.getText().isEmpty()|| inputville.getText().isEmpty()|| inputrole.getSelectionModel()==null|| inputpassword.getText().isEmpty())
        {
            new Alert(Alert.AlertType.ERROR, "Veuillez verifier les champs", new ButtonType[]{ButtonType.OK}).show();
        }
          else if (!mail.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" )) {
               new Alert(Alert.AlertType.ERROR, "Merci de saisir une adresse mail valide.", new ButtonType[]{ButtonType.OK}).show();
           }
            
        else {         
                 
                    User u = new User();
            u.setName(name);
            u.setPhone(phone);
            u.setMail(mail);
            u.setCity(city);
            u.setRole(role);
            u.setPassword(password);
            us.addUser(u);
             
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            String tilte = "Création compte avec succés";
            String message = "Welcome to HelpLine Charity !";
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(3));

         }

        
        
    }
    
}
