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
import java.util.Set;
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
import org.controlsfx.control.textfield.TextFields;
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
    private TextField searchInput;

    
    private ObservableList<User> dataList;
    @FXML
    private Button UpdateAdmin;
    @FXML
    private TextField inputName;
    @FXML
    private TextField inputCity;
    @FXML
    private TextField inputGouvernorat;
    @FXML
    private TextField inputPhone;
    @FXML
    private TextField inputmail;
    @FXML
    private TextField inputRole;
    @FXML
    private TextField inputpassword;
    @FXML
    private Button cofirmupdate;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filterTable();
         UserService us = new UserService();
 Set<String> suggets = us.getSuggests();
       TextFields.bindAutoCompletion(searchInput, suggets);
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

    @FXML
    private void AddAdminAction(ActionEvent event) {
          UserService us = new UserService();
          String name = inputName.getText();
                    String password = inputpassword.getText();
                    String phone = inputPhone.getText();
                    String mail = inputmail.getText();
                    String city = inputCity.getText();
                    String role = inputRole.getText();
                    String gouvernorat = inputGouvernorat.getText();
         if (inputName.getText().isEmpty() || inputPhone.getText().isEmpty()|| inputpassword.getText().isEmpty() || inputmail.getText().isEmpty()|| inputCity.getText().isEmpty()||  inputGouvernorat.getText().isEmpty())
        {
            new Alert(Alert.AlertType.ERROR, "Veuillez verifier les champs", new ButtonType[]{ButtonType.OK}).show();
               }
          else if (! inputRole.getText() .equalsIgnoreCase("Admin")) {
               new Alert(Alert.AlertType.ERROR, "Le role Admin est Obligatoire !", new ButtonType[]{ButtonType.OK}).show();
        }
          else if (!mail.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" )) {
               new Alert(Alert.AlertType.ERROR, "Merci de saisir une adresse mail valide.", new ButtonType[]{ButtonType.OK}).show();
           }
            
        else {         
                 
                    User u = new User();
            u.setName(name);
            u.setPassword(password);
            u.setPhone(phone);
            u.setMail(mail);
            u.setCity(city);
            u.setRole(role);
            u.setGouvernorat(gouvernorat);
            us.addAdmin(u);
             filterTable();
                     
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            String tilte = "Ajout Admin avec succés";
            String message = "Un nouveau Admin est ajouté !";
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(3));
          }
        
    }

    @FXML
    private void UpdateAdminAction(ActionEvent event) {
        
        if(tableAdmin.getSelectionModel().getSelectedItem() == null) {
            new Alert(Alert.AlertType.WARNING, "Veuillez selectionner un administrateur à modifier de la table", new ButtonType[]{ButtonType.OK}).show();         
        }
        else{
            User u = tableAdmin.getSelectionModel().getSelectedItem();
            inputName.setText(u.getName());
            inputCity.setText(u.getCity());
            inputGouvernorat.setText(u.getGouvernorat());
            inputPhone.setText(u.getPhone());
            inputmail.setText(u.getMail());
            inputRole.setText(u.getRole());
            AddAdmin.setDisable(true);
            deleteAdmin.setDisable(true);
            tableAdmin.setDisable(true);
            UpdateAdmin.setDisable(true);
            cofirmupdate.setVisible(true);
            
            
       
    }
    }

    @FXML
    private void cofirmupdateAction(ActionEvent event) {
          UserService us = new UserService();
             User u1 = new User();
       
         String name = inputName.getText();
                    String password = inputpassword.getText();
                    String phone = inputPhone.getText();
                    String mail = inputmail.getText();
                    String city = inputCity.getText();
                    String role = inputRole.getText();
                    String gouvernorat = inputGouvernorat.getText();
          if (! inputRole.getText() .equalsIgnoreCase("Admin")) {
               new Alert(Alert.AlertType.ERROR, "Le role Admin est Obligatoire !", new ButtonType[]{ButtonType.OK}).show();
        }
          else if (!mail.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" )) {
               new Alert(Alert.AlertType.ERROR, "Merci de saisir une adresse mail valide.", new ButtonType[]{ButtonType.OK}).show();
           }
            
        else {         
                  u1.setName(name);
                  u1.setPassword(password);
                  u1.setCity(city);
                  u1.setGouvernorat(gouvernorat);
                   u1.setPhone(phone);
                    u1.setRole(role);
us.updateAdmin(mail, u1);
AddAdmin.setDisable(false);
        deleteAdmin.setDisable(false);
        tableAdmin.setDisable(false);
        filterTable();
    }
    
    
    }
    

}
