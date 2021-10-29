/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.EvenementService;
import services.UserSession;

/**
 * FXML Controller class
 *
 * @author SeifD
 */
public class DashBoardController implements Initializable {

    
    
    @FXML
    private Label totalEvents;
    @FXML
    private JFXButton btnParticipEvent;
    @FXML
    private Label nbreParticipants;
    @FXML
    private Label donCollecte;
    @FXML
    private Label assocLabel1;
    @FXML
    private Label assocLabel2;
    @FXML
    private Label assocLabel3;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ImageView imageViewNom1;
    @FXML
    private Label labelNom1;
    @FXML
    private ImageView imageViewNom2;
    @FXML
    private Label labelNom2;
    @FXML
    private ImageView imageViewNom3;
    @FXML
    private Label labelNom3;
    @FXML
    private ImageView imageViewNom4;
    @FXML
    private Label labelNom4;
    @FXML
    private ImageView imageViewNom5;
    @FXML
    private Label labelNom5;
    
    Image imageProfil = new Image("/images/profile.png");
    @FXML
    private JFXButton btnEditProfil;
    @FXML
    private ImageView imgPhotoSession;
    @FXML
    private Label labelNomSession;
    
    UserSession us = new UserSession();
    
    @FXML
    private JFXToggleButton toggleDarkDayMode;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int i=0;
        EvenementService es = new EvenementService();
        totalEvents.setText(String.valueOf(es.getNombreEvents()));
        nbreParticipants.setText(String.valueOf(es.getNombreParticipants()));
        donCollecte.setText(String.valueOf(es.getAllDonsFromEvents()));
        
        assocLabel1.setText(es.getAssocActive1());
        assocLabel2.setText(es.getAssocActive2());
        assocLabel3.setText(es.getAssocActive3());
        
        Set<String> names = es.getUsersParticipated();
        for (String name : names){
            if (i==0){
                labelNom1.setText(name); 
                imageViewNom1.setImage(imageProfil);
            }
            else if (i==1){
                labelNom2.setText(name);  
                imageViewNom2.setImage(imageProfil);
            }
            else if (i==2){
                labelNom3.setText(name); 
                imageViewNom3.setImage(imageProfil);
            }
            else if (i==3){
                labelNom4.setText(name);  
                imageViewNom4.setImage(imageProfil);
            }
            else if (i==4){
                labelNom5.setText(name); 
                imageViewNom5.setImage(imageProfil);
            }
            i++;
    
        }
        
        labelNomSession.setText(us.getActualUserName());
        
        

    }    

    @FXML
    private void ParticiperButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/ParticiperEvent.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();   
        
    }

    @FXML
    private void btnEditProfilAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/editprofile.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }
    
 

    @FXML
    private void toggleDarkDayModeAction(ActionEvent event) {
        if (toggleDarkDayMode.isSelected()){
            toggleDarkDayMode.setText("ON");
        }
        else{
            toggleDarkDayMode.setText("OFF");
        }
    }
    


    
    
}
