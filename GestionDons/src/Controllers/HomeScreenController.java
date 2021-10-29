/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.RecompenseService;
import services.UserSession;

/**
 * FXML Controller class
 *
 * @author SeifD
 */
public class HomeScreenController implements Initializable {

    @FXML
    private JFXButton btnEditProfil;
    @FXML
    private ImageView imgPhotoSession;
    @FXML
    private Label labelNomSession;
    @FXML
    private JFXButton btnGestionEvents;
    @FXML
    private JFXButton btnGestionDons;
    @FXML
    private JFXButton btnGestionServices;
    @FXML
    private JFXButton btnGestionProduits;
    @FXML
    private JFXButton btnTrasformerPts;
    @FXML
    private JFXButton btnReclamation;
    @FXML
    private JFXButton btnLogout;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    UserSession session = new UserSession();
    RecompenseService serviceRec = new RecompenseService();
    
    @FXML
    private Label labelPts;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelNomSession.setText(session.getActualUserName());
        labelPts.setText(labelPts.getText() + " " + serviceRec.getNbrePts(session.getActualUserId()) + " Points");
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
    private void btnGestionEventsAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/EventsMain.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void btnGestionDonsAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/DonsScreen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void btnGestionServicesAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/AjouterDon.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void btnGestionProduitsAction(ActionEvent event) {
    }

    @FXML
    private void btnTrasformerPtsAction(ActionEvent event) {
    }

    @FXML
    private void btnReclamationAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/Réclamation.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void btnLogoutAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/Main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }

    
}
