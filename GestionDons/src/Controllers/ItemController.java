/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;
import Service.DonationCrud;
import Service.UserSession;
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
import javafx.scene.Node;
import java.io.IOException;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DonationCrud dc = new DonationCrud();
        UserSession us = new UserSession();
        dc.afficherrecu(us, anne,  montant,  categ ,  nom,  tel );
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
    }}

