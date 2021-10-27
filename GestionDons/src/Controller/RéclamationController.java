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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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
    private JFXTextField passtext;
    @FXML
    private JFXTextField emailtext;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void sendemail(){
      /* Lancer.setOnAction(e -> {
           
            if (objet.getText().trim().length() == 0) {
                             new Alert(Alert.AlertType.ERROR, "vous devez saisir le contenu de la réclamation !", new ButtonType[]{ButtonType.OK}).show();
            
            } else if (texte.getText().trim().length() == 0) {
                
                new Alert(Alert.AlertType.ERROR, "vous devez saisir le contenu de la réclamation !", new ButtonType[]{ButtonType.OK}).show();
            } else {
                RéclamationService RS = new RéclamationService();
                Réclamation r = new Réclamation();
                r.setUser();
                r.setobjet(objet.getText());
                r.settexte(texte.getText());
                RS.ajouterReclamation(r);
                try {
                   // MailReclamation.sendMail(user.getEmail(), r);
                } catch (Exception ex) {
                    Logger.getLogger(RéclamationController.class.getName()).log(Level.SEVERE, null, ex);
                }
                 AnchorPane newLoadedPane = null;
            try {
                newLoadedPane = FXMLLoader.load(getClass().getResource("../FXML/MesReclamation.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(RéclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
                container.getChildren().clear();
                container.getChildren().add(newLoadedPane);
            }

        });
        btn_back.setOnAction(e -> {
             AnchorPane newLoadedPane = null;
            try {
                newLoadedPane = FXMLLoader.load(getClass().getResource("/AjouterDon"));
            } catch (IOException ex) {
                Logger.getLogger(RéclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
                container.getChildren().clear();
                container.getChildren().add(newLoadedPane);
        });
    }

        
        
    

    @FXML
    private void SaveLancer(ActionEvent event) {
    }

    @FXML
    private void passtext(ActionEvent event) {
    }

    @FXML
    private void emailtext(ActionEvent event) {
    }
    */
}
}
