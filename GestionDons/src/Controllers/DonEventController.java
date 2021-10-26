/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import entities.Evenement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.EvenementService;
import services.UserSession;

/**
 * FXML Controller class
 *
 * @author SeifD
 */
public class DonEventController implements Initializable {

    @FXML
    private JFXButton btnEditProfil;
    @FXML
    private JFXToggleButton toggleDarkDayMode;
    @FXML
    private ImageView imgPhotoSession;
    @FXML
    private Label labelNomSession;
    @FXML
    private TableColumn<Evenement, String> idEventCol;
    @FXML
    private TableColumn<Evenement, String> nameEventCol;
    @FXML
    private TableView<Evenement> eventTable;
    @FXML
    private VBox vboxDescript;
    @FXML
    private JFXTextArea descriptionText;
    
    private ObservableList<Evenement> listM;
    private ObservableList<Evenement> dataList;
    @FXML
    private HBox vBoxDisplay1;
    @FXML
    private Label labelDonCollectes;
    @FXML
    private Label labelObjectif;
    @FXML
    private HBox vBoxDisplay2;
    @FXML
    private JFXTextField inputMontantDon;
    
    UserSession userSession = new UserSession();
    EvenementService es = new EvenementService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Evenement e = es.getMyFirstEvent(userSession.getActualUserId());
        labelDonCollectes.setText(String.valueOf(e.getMontant_collecte()) + "k");
        labelObjectif.setText("5000k");
        descriptionText.setText(e.getDescription());
        showEvents();
        
    }    

    @FXML
    private void btnEditProfilAction(ActionEvent event) {
    }

    @FXML
    private void toggleDarkDayModeAction(ActionEvent event) {
    }

   public void showEvents(){
        ObservableList<Evenement> eventList= FXCollections.observableArrayList();
        EvenementService es = new EvenementService();
        eventList = es.getMyParticipations(userSession.getActualUserId());
        
        idEventCol.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
        nameEventCol.setCellValueFactory(new PropertyValueFactory<>("cause"));
        
        eventTable.setItems(eventList);
        
    }
   
   public void showEventDetails(){
       Evenement e = eventTable.getSelectionModel().getSelectedItem();
       labelDonCollectes.setText(String.valueOf(e.getMontant_collecte()) + "k");
       labelObjectif.setText("5000k");
       descriptionText.setText(e.getDescription());
   }

    @FXML
    private void mouseClickedTable(MouseEvent event) {
        showEventDetails();
        new FadeIn(vBoxDisplay1).play();
        new FadeIn(vBoxDisplay2).play();
        new FadeIn(vboxDescript).play();
       

        
    }
    
   /* public void filterTable(){
        EvenementService es = new EvenementService();        
        idEventCol.setCellValueFactory(new PropertyValueFactory<>("Region"));
        nameEventCol.setCellValueFactory(new PropertyValueFactory<>("cause"));
        
        dataList = es.showAllEvents();
        eventTable.setItems(dataList);
        
        
        eventTable.setItems(dataList);
        FilteredList<Evenement> filteredData = new FilteredList<>(dataList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		searchInput.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(evenement -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (evenement.getCause().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches Cause.
				} 
				else  
                                    return false; // Does not match.
			});
		});
                
                SortedList<Evenement> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(eventTable.comparatorProperty());
                eventTable.setItems(sortedData);
    
    }*/
    
}
