/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Service.RéclamationService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author 21654
 */
public class RéclamationController implements Initializable {

    @FXML
    private JFXTextField objet;
    @FXML
    private JFXTextArea texte;
    @FXML
    private JFXButton Lancer;
    @FXML
    private AnchorPane page;
    @FXML
    private Pane context;
    @FXML
    private JFXTextField emailtext;
    @FXML
    private JFXTextField passtext;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void sendemail() throws MessagingException{
        
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAccountEmail = "laouinikhoubaib@gmail.com";
        String password = "54487021";
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password); 
            }  
        });
        
         
        
       /* Message message = prepareMessage(session, myAccountEmail, recipient); 
        Transport.send(message);
        System.out.println("Message sent succesfully");*/
    }
    
    private static Message prepareMessage(Session session, String myAccountEmail, String recipient){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO,  new InternetAddress(recipient));
            message.setSubject("Un événement est disponible prés de chez vous");
            message.setText("Test Mail");
            return message;
        } catch (Exception ex) {
           
        }
        return null;
    }
    
    

    @FXML
    private void SaveLancer(ActionEvent event) {
        String title =  " Félicitations " ;
         String message =  " Réclamation envoyée " ;
        TrayNotification tay = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        
        tay.setAnimationType(type);
        tay.setTitle(title);
        tay.setMessage(message);
        tay.setNotificationType(NotificationType.SUCCESS);
        tay.showAndDismiss(Duration.millis(1));
    }

    @FXML
    private void passtext(ActionEvent event) {
    }

    @FXML
    private void emailtext(ActionEvent event) {
    }
    
}


