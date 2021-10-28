/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entité.Don;
import Service.DonService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.TextToSpeach;

/**
 *
 * @author 21654
 */
public class ListeDonController {
    private ObservableList<Don> DonList;
    private ObservableList<Don> dataList;

    @FXML
    private TableView<Don> table;

    @FXML
    private TableColumn<Don, Integer> colserviceid;

    @FXML
    private TableColumn<Don, Integer> coldonneurid;

    @FXML
    private TableColumn<Don, String> coltypedeservice;

    @FXML
    private TableColumn<Don, String> coldéscription;

    @FXML
    private TableColumn<Don, String> coldatededisponibilité;

    @FXML
    private TableColumn<Don, String> collieu;

    @FXML
    private JFXTextField recherchertext;


    @FXML
    private JFXButton supprimerbutton;

    @FXML
    private JFXButton modifierbutton;

   
    @FXML
    private JFXButton showbutton;
  
    @FXML
    private AnchorPane page;
    @FXML
    private Pane context;
    @FXML
    private Pane context1;
    
     
    public void initialize(URL location, ResourceBundle resources) {
        //TextToSpeach
       Recherche();
       
}

     @FXML
    private void showbutton() {
        
        ObservableList<Don> DonList = FXCollections.observableArrayList();
        DonService ds = new DonService();
        DonList = ds.afficherDoneService();
      
        colserviceid.setCellValueFactory(new PropertyValueFactory<>("serviceId") );
        coldonneurid.setCellValueFactory(new PropertyValueFactory<>("donorId") );
        coltypedeservice.setCellValueFactory(new PropertyValueFactory<>("TypeService") );
        collieu.setCellValueFactory(new PropertyValueFactory<>("lieu") ); 
        coldatededisponibilité.setCellValueFactory(new PropertyValueFactory<>("DateDisponibilité") );
        coldéscription.setCellValueFactory(new PropertyValueFactory<>("déscription") );           
        table.setItems(DonList);          
}       
    
        public void Recherche(){
       
        DonService ds = new DonService();  
        colserviceid.setCellValueFactory(new PropertyValueFactory<>("serviceId") );
        coldonneurid.setCellValueFactory(new PropertyValueFactory<>("donorId") );
        coltypedeservice.setCellValueFactory(new PropertyValueFactory<>("TypeService") );
        collieu.setCellValueFactory(new PropertyValueFactory<>("lieu") ); 
        coldatededisponibilité.setCellValueFactory(new PropertyValueFactory<>("DateDisponibilité") );
        coldéscription.setCellValueFactory(new PropertyValueFactory<>("déscription") );
        
        
        dataList = ds.afficherDoneService();
        table.setItems(dataList);
        FilteredList<Don> filteredData = new FilteredList<>(dataList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherchertext.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Don -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
                                
				
				 if (Don.getTypeService().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
                                else if (Don.getLieu().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
				else if (String.valueOf(Don.getDéscription()).indexOf(lowerCaseFilter)!=-1)
				     return true;
                                
				else  
                                    return false; // Does not match.
			});
		});
                
                SortedList<Don> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(table.comparatorProperty());
                table.setItems(sortedData);

    }
 @FXML
    private void SaveDelete(ActionEvent event) {
        
            DonService ds = new DonService();
            Don d = table.getSelectionModel().getSelectedItem();
            ds.supprimerDone(d.getServiceId());      
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            String tilte = "Don Supprimé";
            String message = "Don supprimé avec Succées !";
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(3));
                    
    }

    @FXML
    private void SaveUpdate(ActionEvent event) throws IOException {
       
 
            Don d = table.getSelectionModel().getSelectedItem();           
            coltypedeservice.setText((d.getTypeService()));
            coldéscription.setText(d.getDéscription());
           // date.setValue(LocalDate.parse(d.getDateDisponibilité().toString()));
    }

    @FXML
    private void filterTable(InputMethodEvent event) {
    }

}
