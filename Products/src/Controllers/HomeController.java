/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Product;
import Services.ProductService;
import java.io.IOException;
import static java.lang.Compiler.command;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class HomeController implements Initializable {

    @FXML
    private TableView<Product> showT;
    @FXML
    private Button btnajoutH;
    @FXML
    private Button btnmodH;
    @FXML
    private Button btnsupH;
    @FXML
    private Button btndemandeH;
    @FXML
    private TableColumn<Product, Integer> colidD;
    @FXML
    private TableColumn<Product, String> colNOM;
    @FXML
    private TableColumn<Product, Integer> colqnt;
    @FXML
    private TableColumn<Product, Integer> colprix;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<Product> ProductList= FXCollections.observableArrayList();
        ProductService ps = new ProductService();
        ProductList = (ObservableList<Product>) ps.afficherProduit();
        
        colidD.setCellValueFactory(new PropertyValueFactory<>("donorId"));
        colNOM.setCellValueFactory(new PropertyValueFactory<>("name"));
        colqnt.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        colprix.setCellValueFactory(new PropertyValueFactory<>("prix_approx"));
       
        
        //ps.setItems(ProductList);
        
    }    

    

    @FXML
    private void AJOUTER(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/HomeProducts.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(" Ajouter Produit");
            stage.show();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    @FXML
    private void MODIFIER(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/UpdateProduct.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(" Modifier Produit");
            stage.show();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    @FXML
    private void SUPPRIMER(ActionEvent event) {
        ///////supprimer un produit selectionné du tab
        //tableview.getItems().removeAll(tableview.getSelectionModel().getSelectedItem());
       
    
    }

    @FXML
    private void DEMANDE(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Demande.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(" Demander Produit");
            stage.show();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    @FXML
    private void clickTreeV(MouseEvent event) {
        
    ////// sélectionner un produit du tableau
      /*  
    Product m = new Product();
        Product p = (Product) TableView.getSelectionModel().getSelectedItem();

        ProductService ps = new ProductService();
        ps.SupprimerProduit(p);
        System.out.println(p);

    }*/
    }

   
    
}
