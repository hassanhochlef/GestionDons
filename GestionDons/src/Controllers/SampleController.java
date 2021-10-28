/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class SampleController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Circle profile_pic;
    @FXML
    private Label name;

    private String app_Id=" ";
    private String app_Secret=" ";
     private String redirect_url=" ";
      private String state=" ";
       private String authentication=" ";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login_as_facebook(ActionEvent event) {
    }
    
}
