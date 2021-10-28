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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.EvenementService;
import services.RecompenseService;
import services.UserSession;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


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
    @FXML
    private JFXButton btnFaireDon;
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    UserSession us = new UserSession();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        labelNomSession.setText(us.getActualUserName());
        Evenement e = es.getMyFirstEvent(userSession.getActualUserId());
        labelDonCollectes.setText(String.valueOf(e.getMontant_collecte()) + " TND");
        labelObjectif.setText(String.valueOf(es.getBesoinTotalByCategorie(e.getDonCategorie())) + " TND");
        descriptionText.setText(e.getDescription());
        showEvents();
        
        inputMontantDon.textProperty().addListener(new ChangeListener<String>() {               
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (!newValue.matches("\\d*")) {
                        inputMontantDon.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            });
        
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
       labelDonCollectes.setText(String.valueOf(e.getMontant_collecte()) + " TND");
       labelObjectif.setText(String.valueOf(es.getBesoinTotalByCategorie(e.getDonCategorie())) + " TND");
       descriptionText.setText(e.getDescription());
   }

    @FXML
    private void mouseClickedTable(MouseEvent event) {
        showEventDetails();
        new FadeIn(vBoxDisplay1).play();
        new FadeIn(vBoxDisplay2).play();
        new FadeIn(vboxDescript).play();
       

        
    }
    
 

    @FXML
    private void btnFaireDonAction(ActionEvent event) throws IOException {
        
        RecompenseService serviceRec = new RecompenseService();

        if(eventTable.getSelectionModel().getSelectedItem() != null){
            
            
            
            int idUser = userSession.getActualUserId();
            int idEvent = eventTable.getSelectionModel().getSelectedItem().getEventId();
            float montantDon;
            String categ = eventTable.getSelectionModel().getSelectedItem().getDonCategorie();
            if (inputMontantDon.getText().equals("")){
                new Alert(Alert.AlertType.WARNING, "Veuillez vérifier le champ du montant du don !", new ButtonType[]{ButtonType.OK}).show();
                
            }
            else{
                montantDon = Float.parseFloat(inputMontantDon.getText());
                es.ajouterDon(idUser, idEvent, montantDon, categ);
                serviceRec.updateUserMontant(idUser, montantDon);
                saveDonorData();
                showEvents();

                //Notification Succées       
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                String tilte = "Don Enregistré";
                String message = "Votre don à bien été effectué !";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.seconds(3));

                Parent root = FXMLLoader.load(getClass().getResource("/Views/Item2.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();  
                }
            
            
        
            
        }
        else{
            new Alert(Alert.AlertType.WARNING, "Veuillez selectionner un évenement de la table", new ButtonType[]{ButtonType.OK}).show();
        }
        
    }
    
    public void saveDonorData() {
        
        String donorName = userSession.getActualUserName();
        String montant = inputMontantDon.getText();
        String donorPhone = userSession.getActualUserPhone();
        String donCategorie = eventTable.getSelectionModel().getSelectedItem().getDonCategorie();
        try {
            File fileRecu = new File("C:\\Users\\SeifD\\Desktop\\GestionDons\\GestionDons\\src\\recu\\recucurrentSessionRecuData.txt");
            FileWriter myWriter = new FileWriter(fileRecu, false);
            myWriter.write(donorName);
            myWriter.write("\n");
            myWriter.write(donCategorie);
            myWriter.write("\n");
            myWriter.write(donorPhone);
            myWriter.write("\n");
            myWriter.write(montant + " DT");
            myWriter.close();

            }catch(Exception e){
            System.out.println("Error writing Recu Data");
        }
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
