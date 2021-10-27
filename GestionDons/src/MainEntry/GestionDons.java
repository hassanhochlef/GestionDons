/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package MainEntry;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Date;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage; 
import Service.DonationCrud;


import java.sql.SQLException;
import javafx.stage.StageStyle;


/**
 *
 * @author Hassan
 */
public class GestionDons extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/Views/Main.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
       stage.isResizable();
       stage.initStyle(StageStyle.UNDECORATED);
        /*Parent root1 = FXMLLoader.load(getClass().getResource("StatsDash.fxml"));
        Scene scene1 = new Scene(root1);
         stage.setScene(scene1);*/
          stage.show();
        
        
        
     
      

        
      
   
    }

    
 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
          /*DonationCrud dc = new DonationCrud();
          Don d = new Don("11-05-1998",500,"Transport",1,2);
          dc.AjouterDons(d);*/
        launch(args);

        //EvenementService es = new EvenementService();
        //System.out.println(es.getMyParticipations(1));
        //System.out.println(es.getBesoinTotalByCategorie("Pauvreté"));

    }

    
    
}
