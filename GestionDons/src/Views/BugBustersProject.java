/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author 21654
 */
public class BugBustersProject extends Application {
    
    @Override
    public void start(Stage primarystage)  {   
        
        
      
        Parent root;  
            try {
                root = FXMLLoader.load(getClass().getResource("/Views/AjouterDon.fxml"));
                 Scene scene = new Scene(root);
        primarystage.setTitle("Ajouter un don");
        primarystage.setScene(scene);
        primarystage.show();
            } catch (IOException ex) {
                Logger.getLogger(BugBustersProject.class.getName()).log(Level.SEVERE, null, ex);
            }
        
       
       
                
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
}
}
    

