/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Views.BugBustersProject;
import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.TextToSpeach;

/**
 * FXML Controller class
 *
 * @author 21654
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane page;

    @FXML
    private Pane context;

    @FXML
    private JFXButton réclamation;

    @FXML
    private JFXButton propositions;

    @FXML
    private JFXButton guide;

    @FXML
    private JFXButton donneurfidèle;

    @FXML
    private JFXButton homeButton;
    @FXML
    private JFXButton savenouveaubutton;
    @FXML
    private JFXButton saveListButton;


    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
         /*  try {
            loadUI("Home");
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
               
        }*/  
    }    

    @FXML
    private void savenouveaubutton(ActionEvent event) throws IOException{
       loadUI("AjouterDon");
        new FadeIn(context).play();

    }
    @FXML
    private void saveListButton(ActionEvent event) throws IOException {
        
        loadUI("ListeDon");
        new FadeIn(context).play();
    }
    @FXML
    private void saveréclamation(ActionEvent event) throws IOException {
         loadUI("Réclamation");
        new FadeIn(context).play();
    }
    
    
     private void loadUI(String ui) throws IOException {
        context.getChildren().clear();
        context.getChildren().add(FXMLLoader.load(this.getClass().getResource("/views/" + ui + ".fxml")));
    }

    







}
