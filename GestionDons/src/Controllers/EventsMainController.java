/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import animatefx.animation.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author SeifD
 */
public class EventsMainController implements Initializable {
    public Pane context;
    
    private Label label;
    @FXML
    private JFXButton homeButton;
    @FXML
    private JFXButton newEventButton;
    @FXML
    private JFXButton myEventsButton;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadUI("DashBoard");
        } catch (IOException ex) {
            Logger.getLogger(EventsMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void homeButtonAction(ActionEvent event) throws IOException {
        
        loadUI("DashBoard");
        new FadeIn(context).play();
        
    }

    @FXML
    private void newEventButtonAction(ActionEvent event) throws IOException {
        loadUI("AddEvent");
        new FadeIn(context).play();
    }

    
    @FXML
    private void myEventsButtonAction(ActionEvent event) throws IOException {
        loadUI("DonEvent");
        new FadeIn(context).play();
    }

    
    
    private void loadUI(String ui) throws IOException{
        context.getChildren().clear();
        context.getChildren().add(FXMLLoader.load(this.getClass().getResource("/views/" + ui + ".fxml")));
    }

    
    
}
