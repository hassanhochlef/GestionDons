/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;
import Service.DonationCrud;
import javafx.print.*;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.scene.Node;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.fxml.FXMLLoader;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Hassan
 */
public class ItemController implements Initializable {

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
        

    }    
@FXML
    private void printimage(ActionEvent event) throws IOException  {
        
        PrinterJob job = PrinterJob.createPrinterJob();
     
        Parent root = FXMLLoader.load(getClass().getResource("Item.fxml"));
  

        if(job != null ){
            boolean success = job.printPage(root);

                  PrinterJob.JobStatus x = job.getJobStatus();
                  System.out.println(x);
            if(success){
                job.endJob();
                }

            }
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

