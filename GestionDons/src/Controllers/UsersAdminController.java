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
import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.PreparedStatement;
import java.util.Set;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import org.controlsfx.control.textfield.TextFields;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class UsersAdminController implements Initializable {

    @FXML
    private TableView<User> table;
    
    @FXML
    private TableColumn<User, String> col_name;
    @FXML
    private TableColumn<User, String> col_city;
    @FXML
    private TableColumn<User, String> col_gouvernorat;
    @FXML
    private TableColumn<User, String> col_phone;
    @FXML
    private TableColumn<User, String> col_mail;
    @FXML
    private TableColumn<User, String> col_role;
    @FXML
    private TableColumn<User, Float> col_montant;
   @FXML
    private TextField searchInput;

        private ObservableList<User> listM;
    private ObservableList<User> dataList;
    
    UserService us = new UserService();

    @FXML
    private Button deleteuser;
private Connection cnx;
        private PreparedStatement ste;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       filterTable();
           UserService us = new UserService();
 Set<String> suggets = us.getSuggests();
       TextFields.bindAutoCompletion(searchInput, suggets);
        
        
        
       // TextFields.bindAutoCompletion(searchInput, suggets);
    }    
        public void showAllUsers(){
        ObservableList<User> userList = FXCollections.observableArrayList();
        UserService us = new UserService();
        userList = us.retrieveallUser();
        
        col_name.setCellValueFactory(new PropertyValueFactory<>("name") );
        col_city.setCellValueFactory(new PropertyValueFactory<>("city") );
        col_gouvernorat.setCellValueFactory(new PropertyValueFactory<>("gouvernorat") );
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone") );
        col_mail.setCellValueFactory(new PropertyValueFactory<>("mail") );
        col_role.setCellValueFactory(new PropertyValueFactory<>("role") );
        col_montant.setCellValueFactory(new PropertyValueFactory<>("montant") );
        table.setItems(userList);
    }
        
        
    public void filterTable(){
        
        UserService us = new UserService() ;
        col_name.setCellValueFactory(new PropertyValueFactory<>("name") );
        col_city.setCellValueFactory(new PropertyValueFactory<>("city") );
        col_gouvernorat.setCellValueFactory(new PropertyValueFactory<>("gouvernorat") );
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone") );
        col_mail.setCellValueFactory(new PropertyValueFactory<>("mail") );
        col_role.setCellValueFactory(new PropertyValueFactory<>("role") );
        col_montant.setCellValueFactory(new PropertyValueFactory<>("montant") );
        
        dataList =us.retrieveallUser();
          table.setItems(dataList);
        FilteredList<User> filteredData = new FilteredList<>(dataList, b -> true);
        // 2. Set the filter Predicate whenever the filter changes.
		searchInput.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(user -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (user.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches Cause.
				} else if (user.getRole().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                else if (user.getCity().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(user.getGouvernorat()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				else  
                                    return false; // Does not match.
			});
		});
                             SortedList<User> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(table.comparatorProperty());
                table.setItems(sortedData);
    
    }

    @FXML
    private void deleteuserAction(ActionEvent event) {
        
         UserService us = new UserService();
        if(table.getSelectionModel().getSelectedItem() != null) {
            User u = table.getSelectionModel().getSelectedItem();
            us.deleteUser(u.getUserId());
            //showEvents();
            filterTable();
            
            
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            String tilte = "Delete Successful";
            String message = "Utilisateur supprimé avec Succées !";
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(3));
            
            
        }
        else{
            new Alert(Alert.AlertType.WARNING, "Veuillez selectionner un évenement à supprimer de la table", new ButtonType[]{ButtonType.OK}).show();
            
        }
   
    }

    @FXML
    private void export_excel(ActionEvent event) throws SQLException  {
      UserService us = new UserService();
        try {
            us.export();
        } catch (IOException ex) {
            Logger.getLogger(UsersAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
    }

   
    
    
    
    
        
        
    

