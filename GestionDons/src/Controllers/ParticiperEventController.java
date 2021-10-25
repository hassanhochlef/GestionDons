/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import animatefx.animation.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.EvenementService;
import services.UserSession;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author SeifD
 */
public class ParticiperEventController implements Initializable {

    @FXML
    private TableColumn<Evenement, String> idEventCol;
    @FXML
    private TableColumn<Evenement, String> nameEventCol;
    @FXML
    private TableView<Evenement> eventTable;
    @FXML
    private Label labelAssoc;
    @FXML
    private Label labelNumParti;
    @FXML
    private Label labelCollect;
    @FXML
    private VBox vboxEventItem;
    @FXML
    private Label labelCateg;
    @FXML
    private TextField searchInput;
    
    private ObservableList<Evenement> listM;
    private ObservableList<Evenement> dataList;
    @FXML
    private ImageView imageCateg;
    @FXML
    private JFXTextArea descriptionText;
    @FXML
    private VBox vboxDescript;
    
    Image imageSante = new Image("/images/sante.png");
    Image imageTransport = new Image("/images/transport-public.png");
    Image imageEnvi = new Image("/images/environnement-artificiel.png");
    Image imagePauverete = new Image("/images/la-pauvrete.png");
    @FXML
    private JFXButton btnRetourAcceuil;
    @FXML
    private JFXButton btnParticiper;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private JFXButton btnEditProfil;
    @FXML
    private ImageView imgPhotoSession;
    @FXML
    private Label labelNomSession;
    
    UserSession us = new UserSession();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EvenementService es = new EvenementService(); 
        Evenement e = es.getFirstEvent();
        labelAssoc.setText("Association Organisatrice : " + es.getAssocNameById(e.getAssociationId()));
        labelCateg.setText("Catégorie de l'évènement : " + e.getDonCategorie());
        labelNumParti.setText("Nombre de Participants : " + e.getNum_participants());
        labelCollect.setText("Dons Collectés : " + e.getMontant_collecte() + "k");
        descriptionText.setText(e.getDescription());
        if (e.getDonCategorie().equals("Santé")){
            imageCateg.setImage(imageSante);
            vboxEventItem.setStyle("-fx-background-color: red;");
        }
        else if (e.getDonCategorie().equals("Environnement")){
            imageCateg.setImage(imageEnvi);
            vboxEventItem.setStyle("-fx-background-color: green;");
        }
        else if (e.getDonCategorie().equals("Pauvreté")){
            imageCateg.setImage(imagePauverete);
            vboxEventItem.setStyle("-fx-background-color: gray;");
        }
        else if (e.getDonCategorie().equals("Transport")){
            imageCateg.setImage(imageTransport);
            vboxEventItem.setStyle("-fx-background-color: #244154;");
        }
        
        filterTable();
        
        labelNomSession.setText(us.getActualUserName());
    }    
    
    
    public void showEvents(){
        ObservableList<Evenement> eventList= FXCollections.observableArrayList();
        EvenementService es = new EvenementService();
        eventList = es.showAllEvents();
        
        idEventCol.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
        nameEventCol.setCellValueFactory(new PropertyValueFactory<>("cause"));
        
        eventTable.setItems(eventList);
        
    }

    @FXML
    private void mouseClickedTable(MouseEvent event) {
       
        showItemEventSelected();
        
        vboxEventItem.setVisible(true);
        vboxDescript.setVisible(true);
        new FadeIn(vboxEventItem).play();
        new FadeIn(vboxDescript).play();
        
    }
    
    public void showItemEventSelected(){
        Evenement e = eventTable.getSelectionModel().getSelectedItem();
        EvenementService es = new EvenementService();
        labelAssoc.setText("Association Organisatrice : " + es.getAssocNameById(e.getAssociationId()));
        labelCateg.setText("Catégorie de l'évènement : " + e.getDonCategorie());
        labelNumParti.setText("Nombre de Participants : " + e.getNum_participants());
        labelCollect.setText("Dons Collectés : " + e.getMontant_collecte() + "k");
        descriptionText.setText(e.getDescription());
        if (e.getDonCategorie().equals("Santé")){
            imageCateg.setImage(imageSante);
            vboxEventItem.setStyle("-fx-background-color: red;");
        }
        else if (e.getDonCategorie().equals("Environnement")){
            imageCateg.setImage(imageEnvi);
            vboxEventItem.setStyle("-fx-background-color: green;");
        }
        else if (e.getDonCategorie().equals("Pauvreté")){
            imageCateg.setImage(imagePauverete);
            vboxEventItem.setStyle("-fx-background-color: gray;");
        }
        else if (e.getDonCategorie().equals("Transport")){
            imageCateg.setImage(imageTransport);
            vboxEventItem.setStyle("-fx-background-color: #244154;");
        }
        
    }
    

    
    public void filterTable(){
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
    
    }

    @FXML
    private void RetourAcceuilAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/EventsMain.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void ParticiperAction(ActionEvent event) {
        Evenement e = eventTable.getSelectionModel().getSelectedItem();
        EvenementService es = new EvenementService();
        
        if(eventTable.getSelectionModel().getSelectedItem() == null){
            new Alert(Alert.AlertType.WARNING, "Veuillez selectionner un évenement de la table afin d'y participer !", new ButtonType[]{ButtonType.YES}).show();
            
        }
        else{
            UserSession us = new UserSession();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de participation");
            alert.setContentText("Vous êtes sur le point de participer à un évènement");
            ButtonType okButton = new ButtonType("Oui", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("Non", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(okButton, noButton);
            alert.showAndWait().ifPresent(type -> {
                if (type == okButton) {
                    //Consommer Service User Session
                    es.participerEvent(e.getEventId(), us.getActualUserId());
                    es.updateNumParticipants(e);
                    filterTable();
                    TrayNotification tray = new TrayNotification();
                    AnimationType animType = AnimationType.POPUP;
                    tray.setAnimationType(animType);
                    String tilte = "Participation réussie";
                    String message = "Participation faite avec succées !";
                    tray.setTitle(tilte);
                    tray.setMessage(message);
                    tray.setNotificationType(NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.seconds(3));
                } 
                else if (type == noButton) {
                    
                } 
            });         
        }
    }

    @FXML
    private void btnEditProfilAction(ActionEvent event) {
    }
    
}
