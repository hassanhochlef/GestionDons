/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.User;
import Service.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AdminsViewController implements Initializable {

    @FXML
    private TableView<User> tableAdmin;
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
    private Button deleteAdmin;
    @FXML
    private Button AddAdmin;
    @FXML
    private Button ModifyAdmin;
    @FXML
    private TextField searchInput;

    
    private ObservableList<User> dataList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filterTable();
    }    

    @FXML
    private void deleteuserAction(ActionEvent event) {
        UserService us = new UserService();
        if(tableAdmin.getSelectionModel().getSelectedItem() != null) {
            User u = tableAdmin.getSelectionModel().getSelectedItem();
            us.deleteUser(u.getUserId());
            //showEvents();
            filterTable();
            
            
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            String tilte = "Delete Successful";
            String message = "Administrateur supprimé avec Succées !";
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(3));
            
            
        }
        else{
            new Alert(Alert.AlertType.WARNING, "Veuillez selectionner un évenement à supprimer de la table", new ButtonType[]{ButtonType.OK}).show();
            
        }
   
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
        
        dataList =us.retrieveallAdmin();
          tableAdmin.setItems(dataList);
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
                sortedData.comparatorProperty().bind(tableAdmin.comparatorProperty());
                tableAdmin.setItems(sortedData);
    
    }
    
}
