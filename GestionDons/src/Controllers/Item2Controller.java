/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.scene.Node;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Hassan
 */
public class Item2Controller implements Initializable {

    @FXML
    private Button print;
    @FXML
    private Label montant;
    @FXML
    private Label nom;
    @FXML
    private Label tel;
    @FXML
    private Label categ;
    @FXML
    private Label anne;
    @FXML
    private Button btnBack;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label labelRef;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> dataList = getDataList();
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        date.format(formatter);
        int i=0;
        for (String item : dataList){
            if (i==0){
                nom.setText(item);
            }
            
            if (i==1){
                categ.setText(item);
            }
            else if (i==2){
                tel.setText(item);
            }
            else if (i==3){
                montant.setText(item);
            }
            i++;
        }
        anne.setText(date.toString());
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        labelRef.setText(String.valueOf(number));
        

    }    
    @FXML
    private void printimage(ActionEvent event) throws Exception  {

        String filename = nom.getText() + categ.getText() + anne.getText()+"-"+labelRef.getText();
        Robot robot = new Robot();
        Rectangle rectangle = new Rectangle(310,120,753,305);
        BufferedImage image= robot.createScreenCapture(rectangle);
        WritableImage myImage = SwingFXUtils.toFXImage(image, null);
        ImageIO.write(image, "jpg", new File("C:\\Users\\SeifD\\Desktop\\GestionDons\\GestionDons\\src\\recu\\"+filename+".jpg"));
        print.setDisable(true);
        //Notification Succées       
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        String tilte = "Enregistrement";
        String message = "Votre reçu a bien été enregistré !";
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(3));
    }
        
       
    

    @FXML
    private void btnBackAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/EventsMain.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    
    public List<String> getDataList(){
        List<String> dataRecu = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(new File("C:\\Users\\SeifD\\Desktop\\GestionDons\\GestionDons\\src\\recu\\recucurrentSessionRecuData.txt"));
            while (scanner.hasNextLine()) {
                dataRecu.add(scanner.nextLine());
            }
            
            
        }catch(FileNotFoundException e){
            System.out.println("Error reading file");
        }
        
        return dataRecu;
    }
}

