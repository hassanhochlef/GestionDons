/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Entité.Don;
import Service.DonService;
import animatefx.animation.FadeIn;
import com.jfoenix.svg.SVGGlyph;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.TextToSpeach;



/**
 * FXML Controller class
 *
 * @author 21654
 */
public class AjouterDonController implements Initializable {

    @FXML
    private JFXTextField ajouterdonneurid;

    @FXML
    private JFXTextField ajouterdéscription;

    @FXML
    private JFXTextField ajouterlieu;

    @FXML
    private JFXButton Validerbutton;

    @FXML
    private JFXComboBox<String> ajoutertypeservice;

    @FXML
    private DatePicker date;
    @FXML
    private AnchorPane page;
    @FXML
    private Pane context;


    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TextToSpeach
        TextToSpeach.speak("Votre Don est crée ");
        
        DonService ds = new DonService();
        ajoutertypeservice.getItems().add("Transport");
        ajoutertypeservice.getItems().add("Construction");
        ajoutertypeservice.getItems().add("Santé");
        ajoutertypeservice.getItems().add("Education");
        ajoutertypeservice.getItems().add("Autre");
    
    }
    @FXML
    void SaveDon(ActionEvent event) {
        
        if (ajoutertypeservice.getSelectionModel() == null ||ajouterdonneurid.getText().isEmpty() || ajouterlieu.getText().isEmpty() || date.getValue() == null)
        {
            new Alert(Alert.AlertType.ERROR, "Veuillez remplir tous les champs !", new ButtonType[]{ButtonType.OK}).show();
        }           
        else {         
        int donorId = Integer.parseInt(ajouterdonneurid.getText());
        String TypeService= ajoutertypeservice.getValue();
        String lieu = ajouterlieu.getText();
        Date DateDisponibilité =Date.valueOf(date.getValue().toString());
        String déscription = ajouterdéscription.getText();
         
        Don d = new Don(donorId, donorId, TypeService, lieu, DateDisponibilité, déscription);
        DonService ds = new DonService();
        ds.ajouterDon(d);
     
        //Notification
         String title =  " Félicitations " ;
         String message =  " Vous avez créé avec succès votre don " ;
        TrayNotification tay = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        
        tay.setAnimationType(type);
        tay.setTitle(title);
        tay.setMessage(message);
        tay.setNotificationType(NotificationType.SUCCESS);
        tay.showAndDismiss(Duration.millis(1));
        
         
     }
    
    }
    
}


