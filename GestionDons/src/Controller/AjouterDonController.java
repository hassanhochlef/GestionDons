/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Entité.Don;
import Service.DonService;
import animatefx.animation.FadeIn;
import com.jfoenix.svg.SVGGlyph;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.TextToSpeach;



/**
 * FXML Controller class
 *
 * @author 21654
 */
public class AjouterDonController implements Initializable {
    
    private ObservableList<Don> DonList;
    private ObservableList<Don> dataList;


    @FXML
    private JFXTextField ajouterdonneurid;

    @FXML
    private JFXTextField ajouterdéscription;

    @FXML
    private JFXTextField ajouterlieu;

    @FXML
    private JFXButton Validerbutton;

    @FXML
    private JFXComboBox<String> ajoutertypeservice;

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

    private JFXTextField recherchertext;


    @FXML
    private JFXButton supprimerbutton;

    @FXML
    private JFXButton modifierbutton;

   
    @FXML
    private JFXButton showbutton;
    @FXML
    private DatePicker date;
    @FXML
    private JFXButton homeBtn;
    
            private Stage stage;
    private Scene scene;
    private Parent root;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TextToSpeach
        
        TextToSpeach.speak("Bienveunue dans la gestion de services ");
        DonService ds = new DonService();
        ajoutertypeservice.getItems().add("Transport");
        ajoutertypeservice.getItems().add("Construction");
        ajoutertypeservice.getItems().add("Santé");
        ajoutertypeservice.getItems().add("Education");
        ajoutertypeservice.getItems().add("Autre");
        //Recherche();
        //showbutton();    
    }
    @FXML
    void SaveDon(ActionEvent event) {
        TextToSpeach.speak("Votre Don est ajoutée ");
        if (ajoutertypeservice.getSelectionModel() == null ||ajouterdonneurid.getText().isEmpty() || ajouterlieu.getText().isEmpty() || date.getValue() == null)
        {
            new Alert(Alert.AlertType.ERROR, "Veuillez remplir tous les champs !", new ButtonType[]{ButtonType.OK}).show();
        }           
        else {         
        int donorId = Integer.parseInt(ajouterdonneurid.getText());
        String TypeService= ajoutertypeservice.getValue();
        String lieu = ajouterlieu.getText();
        Date DateDisponibilité =Date.valueOf(date.getValue().toString());
        String déscription = ajouterdéscription.getText();
         
        Don d = new Don(donorId, donorId, TypeService, lieu, DateDisponibilité, déscription);
        DonService ds = new DonService();
        ds.ajouterDon(d);
     
        //Notification
         String title =  " Félicitations " ;
         String message =  " Vous avez créé avec succès votre don " ;
        TrayNotification tay = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        
        tay.setAnimationType(type);
        tay.setTitle(title);
        tay.setMessage(message);
        tay.setNotificationType(NotificationType.SUCCESS);
        tay.showAndDismiss(Duration.millis(1));
     }
    
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

    void savebesoin(ActionEvent event) {

    }

    void saveretour1(ActionEvent event) {

    }

    void saveréclamation(ActionEvent event, String recepient, String objet, String texte) {
        
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
/*		recherchertext.textProperty().addListener((observable, oldValue, newValue) -> {
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
    
    }*/

    }

    @FXML
    private void homeBtnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/HomeScreen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
      
}


