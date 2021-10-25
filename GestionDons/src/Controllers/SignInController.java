/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.User;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import Connection.MyConnection;
import Service.UserService ;
import Service.UserSession;
/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class SignInController implements Initializable {

    @FXML
    private TextField inputemail;
    @FXML
    private TextField inputpassword;
    @FXML
    private Button signin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     
 
    @FXML
    private void signinAction(ActionEvent event) {
     MyConnection cnx = MyConnection.getInstance();
        PreparedStatement ps;
        ResultSet rs;
        String mail = inputemail.getText();
        String password = inputpassword.getText();
        
       UserSession us = new UserSession ();
        if (us.verifyUser(mail, password)){
            User u = us.getUserConnected(mail);
            us.addUserSession(u);
        }
               
        
        
        }
        
        
        
        }
        
    
    
        
    
    
    


