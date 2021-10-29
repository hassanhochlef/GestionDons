/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Product;
import Services.ProductService;
import java.net.URL;
//import java.time.Duration;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//////package utils dans l'integration 
//import utils.JavaMail;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class HomeProductsController implements Initializable {

    @FXML
    private TextField txtiddonor;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtqualite;
    @FXML
    private TextField txtprx;
    @FXML
    private Button valideradd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SaveAdd(ActionEvent event) {
        if (txtiddonor.getText().isEmpty() || txtnom.getText().isEmpty() || txtqualite.getText().isEmpty()  || txtprx.getText().isEmpty())
        {
            new Alert(Alert.AlertType.ERROR, "Veuillez verifier les champs", new ButtonType[]{ButtonType.OK}).show();
        }
            
        else {    
        
            int rdonorid = Integer.parseInt(txtiddonor.getText());
            String rname = txtnom.getText();
            int rqt = Integer.parseInt(txtqualite.getText());
            int rprix = Integer.parseInt(txtprx.getText());
            Product p = new Product(rdonorid, rname, rqt, rprix);
            ProductService ps = new ProductService();
            ps.ajouterProduit(p);
            
           
            
            
            ///////Mailing la class est cituée dans le package utils dans l'integration
            
            /*Set<String> mails = es.getEmailByRegion(region);
        
            for (String mail : mails){
                 try {
                     JavaMail.sendMail(mail);
                 } catch (Exception ex) {
                     Logger.getLogger(HomeProductsController.class.getName()).log(Level.SEVERE, null, ex);
                 }*/
            
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.close();
        
    }
    
}
}
