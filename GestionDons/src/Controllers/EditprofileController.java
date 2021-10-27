/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.User;
import Service.UserService;
import Service.UserSession;
import com.jfoenix.controls.JFXComboBox;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class EditprofileController implements Initializable {

    @FXML
    private TextField inputname;

    @FXML
    private TextField inputcity;
    @FXML
    private TextField inputgouvernorat;
    @FXML
    private TextField inputphone;
    @FXML
    private TextField inputmail;
    @FXML
    private Button updateuser;
    @FXML
    private JFXComboBox<String> inputrole;
    @FXML
    private ImageView photo;
    @FXML
    private Button attach;
    @FXML
    private TextField inputphoto;
    @FXML
    private PasswordField inputpassword;
    
    String path;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        UserSession us = new UserSession();
       inputrole.getItems().add("Donneur");
        inputrole.getItems().add("Association ou Club");
//         photodb.setImage(new Image(us.getActualUserPhoto()));
    }    

    @FXML
    private void updateuseraction(ActionEvent event) {
        UserService us = new UserService();
               
        String name = inputname.getText();
        String password = inputpassword.getText();
        String city = inputcity.getText();
        String gouv = inputgouvernorat.getText();
        String phone = inputphone.getText();
        String mail = inputmail.getText();
        String role = inputrole.getSelectionModel().getSelectedItem();
        String photo = path;
        
         if (inputname.getText().isEmpty() || inputphone.getText().isEmpty() || inputmail.getText().isEmpty()|| inputgouvernorat.getText().isEmpty()|| inputcity.getText().isEmpty()|| inputrole.getSelectionModel()==null|| inputpassword.getText().isEmpty())
        {
            new Alert(Alert.AlertType.ERROR, "Veuillez verifier les champs", new ButtonType[]{ButtonType.OK}).show();
        }
          else if (!mail.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" )) {
               new Alert(Alert.AlertType.ERROR, "Merci de saisir une adresse mail valide.", new ButtonType[]{ButtonType.OK}).show();
           }
            
        else { 
        
        UserSession userSession = new UserSession();
 
        User u = new User(name, photo, password, city, gouv, phone, mail, role);
            
        us.updateUser(userSession.getActualUserId(), u);
        
                    TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            String tilte = "Modification du profil avec succés";
                String message = "Profile Modified !";
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.seconds(3));

       
            
       
         }
    }

    @FXML
    private void attachAction(ActionEvent event) {
        
         FileChooser fc = new FileChooser ();
        fc.setTitle("My file Chooser");
        
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.png","*.jpg"));
        File file = fc.showOpenDialog(null);
          if (file != null) { 
              path = file.getAbsolutePath();
              System.out.println(path);
              photo.setImage(new Image(file.toURI().toString()));
          }
          else {
              new Alert(Alert.AlertType.ERROR, "Fichier Invalide", new ButtonType[]{ButtonType.OK}).show();
          }
    
}
        
    }
    
 
