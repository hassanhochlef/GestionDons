/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.User;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import Connection.MyConnection;
import Service.JavaMailUtil;
import Service.UserService ;
import Service.UserSession;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class SignInController implements Initializable {

    @FXML
    private TextField inputemail;
   
    @FXML
    private Button signin;

    @FXML
    private PasswordField inputpassword;
    @FXML
    private Button forgotBtn;
    
    private Stage stage;
    private Scene scene;
    private Parent root;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     
 
    @FXML
    private void signinAction(ActionEvent event) throws IOException {
     MyConnection cnx = MyConnection.getInstance();
        PreparedStatement ps;
         String mail = inputemail.getText();
        String password = inputpassword.getText();
        ResultSet rs;
           if (inputemail.getText().isEmpty() || inputpassword.getText().isEmpty())
           {
                           new Alert(Alert.AlertType.ERROR, "Veuillez verifier les champs", new ButtonType[]{ButtonType.OK}).show();

             }
           else if (!mail.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" )) {
               new Alert(Alert.AlertType.ERROR, "Merci de saisir une adresse mail valide.", new ButtonType[]{ButtonType.OK}).show();
           }
            
        else { 
       
       UserSession us = new UserSession ();
        if (us.verifyUser(mail, password)){
            User u = us.getUserConnected(mail);
            us.addUserSession(u);
            Parent root = FXMLLoader.load(getClass().getResource("/Views/HomeScreen.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

             TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            String tilte = "Connexion  avec succés";
            String message = "Welcome to HelpLine Charity !";
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(3));
        }
               
        
        
        }

        
        
    }   

    @FXML
    private void forgot(ActionEvent event) throws SQLException {
        String mail= inputemail.getText();
        JavaMailUtil ms = new JavaMailUtil();
        UserService us = new UserService();
        String x = us.getPassword1(mail);
        try {
            new Alert(Alert.AlertType.ERROR, "Vérifier votre boite Mail et récupérer votre mot de passe", new ButtonType[]{ButtonType.OK}).show();
            ms.sendMail(mail,x);
        } catch (Exception ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
        
    
    
        
    
    
    


