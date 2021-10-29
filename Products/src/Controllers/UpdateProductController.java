/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Product;
import Services.ProductService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class UpdateProductController implements Initializable {

    @FXML
    private TextField txtidDuP;
    @FXML
    private TextField txtnameuP;
    @FXML
    private TextField txtqntuP;
    @FXML
    private TextField txtprixuP;
    @FXML
    private Button btnsaveUP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SaveUpdate(ActionEvent event) {
          
            
            /*int mproductid = Integer.parseInt(txtidPROD.getText());
            int mdonorid = Integer.parseInt(txtidDuP.getText());
            String mname = txtnameuP.getText();
            int mqt = Integer.parseInt(txtqntuP.getText());
            int mprix = Integer.parseInt(txtprixuP.getText());
            Product p = new Product(mproductid,mdonorid, mname, mqt, mprix);
            ProductService ps = new ProductService();
            ps.ModifierProduit(p);*/
    }
    
}
