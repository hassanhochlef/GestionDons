/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
//import com.gluonhq.charm.glisten.control.TextField;
import Entities.Besoin;
import Service.DonationCrud;

import javafx.scene.control.TextField;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * FXML Controller class
 *
 * @author Hassan
 */
public class AjoutBesoinAssociationController implements Initializable {
    @FXML
     public TextField categorieBesoin;
     @FXML
     public TextField descriptionBesoin;
      @FXML
     public TextField montantBesoin;
       @FXML
     public TextField quantiteBesoin;
        @FXML
     public TextField categorieObjet;
         @FXML
     public TextField descriptionObjet;
          @FXML
     public TextField montantObjet;
          @FXML
     public ImageView photoObjet;
    
DonationCrud dc = new DonationCrud();
String path;
    @FXML
    private Button close;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         }    

    @FXML
    private void AjouterBesoin(ActionEvent event) throws SQLException {
        
        Besoin b = new Besoin();
        b.setCategorie(categorieBesoin.getText());
        b.setDescription(descriptionBesoin.getText());
        b.setMontant(Float.parseFloat(montantBesoin.getText()));
        b.setQuantite(Integer.parseInt(quantiteBesoin.getText()));
       
        dc.AjouterBesoinD(b);
         Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
       
        
    }

    @FXML
    private void ImporterImage(ActionEvent event) {
        FileChooser fc = new FileChooser ();
        fc.setTitle("My file Chooser");
        
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.png","*.jpg"));
        File file = fc.showOpenDialog(null);
          if (file != null) { 
              path = file.getAbsolutePath();
              System.out.println(path);
              photoObjet.setImage(new Image(file.toURI().toString()));
          }
          else {
             
          }
    }

    @FXML
    private void AjouterObjet(ActionEvent event) throws SQLException {
      
             Besoin b = new Besoin();
        b.setCategorie(categorieObjet.getText());
        b.setQuantite(0);
        b.setDescription(descriptionObjet.getText());
        b.setMontant(Float.parseFloat(montantObjet.getText()));
        b.setPhoto(path);
        dc.AjouterBesoinD(b);
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
       
    }
    
}
