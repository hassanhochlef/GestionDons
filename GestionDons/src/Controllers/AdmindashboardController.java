/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.User;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn ;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Service.UserService;
import Connection.MyConnection;
import java.io.IOException;
import java.util.Set;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import org.controlsfx.control.textfield.TextFields;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AdmindashboardController implements Initializable {

    
    UserService us = new UserService();

    //@FXML
    public Pane mainpane;
    @FXML
    private Button btnUser;
    @FXML
    private Button btnAdmin;
    @FXML
    private Button btnReclamation;
    @FXML
    private Button btnRecompense;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void btnUserAction(ActionEvent event) throws IOException {
        loadUI("UsersAdmin");
    }

    @FXML
    private void btnAdminAction(ActionEvent event) throws IOException {
        loadUI("AdminsView");
    }

    @FXML
    private void btnReclamationAction(ActionEvent event) throws IOException {
        loadUI("ReclamationAdmin");
    }

    @FXML
    private void btnRecompenseAction(ActionEvent event) {
    }
    
    private void loadUI(String ui) throws IOException{
        mainpane.getChildren().clear();
        mainpane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/Views/" + ui + ".fxml")));
    }
        
        
   
        
    
}
