/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entité.Don;
import Service.DonService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author 21654
 */
public class ModifierDonController implements Initializable {

    @FXML
    private JFXComboBox<String> modifierertypeservice;
    @FXML
    private JFXTextField modifiererdéscription;
    @FXML
    private JFXTextField modifierlieu;
    @FXML
    private DatePicker modifierdate;
    @FXML
    private JFXButton enregistrerbutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }       

    @FXML
    private void enregistrerbutton(ActionEvent event) {
        
        DonService us = new DonService();
        
         if (modifierertypeservice.getValue().isEmpty() || modifiererdéscription.getText().isEmpty() || modifierlieu.getText().isEmpty()|| modifierdate.getValue()== null )
        {
            new Alert(Alert.AlertType.ERROR, "Veuillez verifier les champs", new ButtonType[]{ButtonType.OK}).show();
        }
            
        else {         
                    String TypeService = modifierertypeservice.getValue();
                    String déscription= modifiererdéscription.getText();
                    String lieu = modifierlieu.getText();
                    Date DateDisponibilité =Date.valueOf(modifierdate.getValue().toString());

                    
                     Don d = new Don();
                     d.setTypeService(TypeService);
                     d.setDéscription(déscription);
                     d.setLieu(lieu);
                     d.setDateDisponibilité(DateDisponibilité);
                     us.modifierDone(d);
                     TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            String tilte = "Don Modifié";
            String message = "Don Modifié avec Succées !";
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(3));
                    
    
    
    }
    }   
    }
    
    

