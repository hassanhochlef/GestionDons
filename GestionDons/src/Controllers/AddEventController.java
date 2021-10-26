/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entities.Evenement;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.textfield.TextFields;
import services.EvenementService;
import services.UserSession;


import tray.animations.*;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.JavaMail;

/**
 * FXML Controller class
 *
 * @author SeifD
 */
public class AddEventController implements Initializable {

    @FXML
    private JFXTextField causeInput;
    @FXML
    private DatePicker dateInput;
    @FXML
    private JFXComboBox<String> categInput;
    @FXML
    private JFXTextArea descInput;
    @FXML
    private JFXButton addEventButton;
    @FXML
    private JFXComboBox<Integer> idAssocInput;
    @FXML
    private JFXButton deleteEventButton;
    @FXML
    private JFXButton updateEventButton;
    @FXML
    private TableView<Evenement> eventTable;
    @FXML
    private TableColumn<Evenement, Integer> colID;
    @FXML
    private TableColumn<Evenement, Integer> colIDAssociation;
    
    private TableColumn<Evenement, Integer> colIDAssoc;
    
    @FXML
    private TableColumn<Evenement, String> colCategorie;
    @FXML
    private TableColumn<Evenement, String> colCause;
    @FXML
    private TableColumn<Evenement, Integer> colParticip;
    @FXML
    private TableColumn<Evenement, String> colDate;
    @FXML
    private TableColumn<Evenement, Integer> colMontant;
    @FXML
    private TableColumn<Evenement, String> colDescription;
    @FXML
    private JFXButton confirmUpdate;
    @FXML
    private TextField searchInput;
    
    //Filtering Data
    private ObservableList<Evenement> listM;
    private ObservableList<Evenement> dataList;
    ////////////////
    
    EvenementService eventService = new EvenementService();
    //AutoCompletition Set
    private Set<String> suggets = eventService.getSuggests();
    @FXML
    private JFXComboBox<String> regionInput;
    @FXML
    private JFXButton btnEditProfil;
    @FXML
    private ImageView imgPhotoSession;
    @FXML
    private Label labelNomSession;
    
    UserSession us = new UserSession();
    @FXML
    private JFXTextField montantnput;
    
    
 
  
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EvenementService es = new EvenementService();
        categInput.getItems().add("Santé");
        categInput.getItems().add("Transport");
        categInput.getItems().add("Pauvreté");
        categInput.getItems().add("Environnement");
        Set<Integer> setIdAssoc = es.getAllAssociationId();
        idAssocInput.getItems().addAll(setIdAssoc);
        Set<String> setRegions = es.getRegions();
        regionInput.getItems().addAll(setRegions);
        confirmUpdate.setVisible(false);
        //showEvents();
        filterTable();
        TextFields.bindAutoCompletion(searchInput, suggets);
        labelNomSession.setText(us.getActualUserName());
        
        
        
        
        
    }    

    @FXML
    private void addEventActionHandler(ActionEvent event) {
        
        EvenementService es = new EvenementService();

        
        if (categInput.getSelectionModel() == null ||causeInput.getText().isEmpty() || dateInput.getValue() == null)
        {
            new Alert(Alert.AlertType.ERROR, "Veuillez verifier les champs", new ButtonType[]{ButtonType.OK}).show();
        }
            
        else {           
            String donCateg = categInput.getSelectionModel().getSelectedItem();
            int assocId = idAssocInput.getSelectionModel().getSelectedItem();
            String region = regionInput.getSelectionModel().getSelectedItem();
            String cause = causeInput.getText();
            Float montantDonne = Float.parseFloat(montantnput.getText());
            
            String desc = descInput.getText();
            Evenement e = new Evenement();
            e.setAssociationId(assocId);
            e.setDonCategorie(donCateg);
            e.setCause(cause);
            e.setRegion(region);
            e.setNum_participants(0);
            e.setDate_creation(Date.valueOf(dateInput.getValue().toString()));
            e.setMontant_collecte(0);
            e.setDescription(desc);       
            es.ajouterEvenement(e);
            
            if (!es.besoinExist(donCateg)){
                es.addBesoin(montantDonne, donCateg, assocId);
            }
            else{
                es.updateBesoinTotal(donCateg, montantDonne);
            }
            
            //showEvents();
            filterTable();
            
            Set<String> mails = es.getEmailByRegion(region);
        
            for (String mail : mails){
                 try {
                     JavaMail.sendMail(mail);
                 } catch (Exception ex) {
                     Logger.getLogger(AddEventController.class.getName()).log(Level.SEVERE, null, ex);
                 }
            }
  
            //Notification Succées       
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            String tilte = "Added Successful";
            String message = "Evenement ajouté avec Succées !";
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(3));

            
        }
        
        
        
        
        
        
 
    }
    
    
    public void showEvents(){
        
        ObservableList<Evenement> eventList= FXCollections.observableArrayList();
        EvenementService es = new EvenementService();
        eventList = es.showAllEvents();
        
        colID.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        colIDAssociation.setCellValueFactory(new PropertyValueFactory<>("associationId"));
        colCategorie.setCellValueFactory(new PropertyValueFactory<>("donCategorie"));
        colCause.setCellValueFactory(new PropertyValueFactory<>("cause"));
        colParticip.setCellValueFactory(new PropertyValueFactory<>("num_participants"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
        colMontant.setCellValueFactory(new PropertyValueFactory<>("montant_collecte"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        eventTable.setItems(eventList);
        

    }

    @FXML
    private void deleteEventActionHandler(ActionEvent event) {
        EvenementService es = new EvenementService();
        if(eventTable.getSelectionModel().getSelectedItem() != null) {
            Evenement e = eventTable.getSelectionModel().getSelectedItem();
            es.supprimerEvenement(e.getEventId());
            //showEvents();
            filterTable();
            
            
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            String tilte = "Delete Successful";
            String message = "Evenement supprimé avec Succées !";
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
    private void updateEventActionHandler(ActionEvent event) {
        if(eventTable.getSelectionModel().getSelectedItem() == null) {
            new Alert(Alert.AlertType.WARNING, "Veuillez selectionner un évenement à modifier de la table", new ButtonType[]{ButtonType.OK}).show();         
        }
        else{
            Evenement e = eventTable.getSelectionModel().getSelectedItem();
            causeInput.setText(e.getCause());
            dateInput.setValue(LocalDate.parse(e.getDate_creation().toString()));
            descInput.setText(e.getDescription());
            categInput.setValue(e.getCause());
            addEventButton.setDisable(true);
            deleteEventButton.setDisable(true);
            eventTable.setDisable(true);
            updateEventButton.setDisable(true);
            confirmUpdate.setVisible(true);
            
           
            
        }
    }

    
    @FXML
    private void confirmUpdateActionHandler(ActionEvent event) {
         EvenementService es = new EvenementService();
        
        Evenement e = eventTable.getSelectionModel().getSelectedItem();
        Evenement e1 = new Evenement();
        String donCateg = categInput.getSelectionModel().getSelectedItem();
        String cause = causeInput.getText();
        String region = regionInput.getSelectionModel().getSelectedItem();
        String desc = descInput.getText();
            
        e1.setDonCategorie(donCateg);
        e1.setCause(cause);
        e1.setRegion(region);
        e1.setDate_creation(Date.valueOf(dateInput.getValue().toString()));
        e1.setMontant_collecte(100);
        e1.setDescription(desc);       
            
        es.updateEvent(e.getEventId(), e1);
        addEventButton.setDisable(false);
        deleteEventButton.setDisable(false);
        eventTable.setDisable(false);
        updateEventButton.setDisable(false);
        confirmUpdate.setVisible(false);
        
        
        //showEvents();
        filterTable();
    }
    
    
    @FXML
    public void filterTable(){
        EvenementService es = new EvenementService();        
        colID.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        colIDAssociation.setCellValueFactory(new PropertyValueFactory<>("associationId"));
        colCategorie.setCellValueFactory(new PropertyValueFactory<>("donCategorie"));
        colCause.setCellValueFactory(new PropertyValueFactory<>("cause"));
        colParticip.setCellValueFactory(new PropertyValueFactory<>("num_participants"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
        colMontant.setCellValueFactory(new PropertyValueFactory<>("montant_collecte"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        dataList = es.showAllEvents();
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
				} else if (evenement.getDonCategorie().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                else if (evenement.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(evenement.getNum_participants()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				else  
                                    return false; // Does not match.
			});
		});
                
                SortedList<Evenement> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(eventTable.comparatorProperty());
                eventTable.setItems(sortedData);
    
    }

    @FXML
    private void btnEditProfilAction(ActionEvent event) {
    }
    
}
