/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import java.util.Calendar;
import java.util.Date;
import Connection.MyConnection;
import Service.DonationCrud;
import Service.progressCalculator;
import Entities.Besoin;
import Entities.Don;

import Entities.User;
import Service.MailSend;
import Service.UserSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import animatefx.animation.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.time.LocalDate;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
/**
 * FXML Controller class
 *
 * @author Hassan
 */

public class DonsScreenController extends Thread implements Initializable,Runnable {
public ComboBox ComboBesoin ;
public ComboBox ComboBesoinTransport;
public ComboBox ComboBesoin2;
    UserSession us = new UserSession();
    private HBox TimerPane;
    private AnchorPane pane;
    @FXML
    private TableView<Besoin> table2;
    private TableColumn<Besoin, Integer> col_id2;
    private TableColumn<Besoin, String> col_desc2;
    @FXML
    private TextField montantHosp;
    @FXML
    private Button btnconfirm1;
    @FXML
    private Button print1;
    @FXML
    private Button btnconfirm2;
    @FXML
    private Button print2;
    @FXML
     private Button print;
    @FXML
    private TextField montant;
    @FXML
    private Button btnconfirm;
    @FXML
    private Label labeldesc;
    @FXML
    private Label labelacc;
    @FXML
    private Label labelbesoin;
    @FXML
    private Label labeldesc1;
    @FXML
    private Label labelacc1;
    @FXML
    private Label labelbesoin1;
    @FXML
    private ProgressBar prog;
    @FXML
    private ProgressBar prog1;
    @FXML
    private TableView<Besoin> table;
    @FXML
    private TableView<Besoin> table1;
    @FXML
    private TableColumn<Besoin,Integer> col_id;
    @FXML
    private TableColumn<Besoin,String> col_desc ;
    @FXML
    private TableColumn<Besoin,Integer> col_id1;
    @FXML
    private TableColumn<Besoin,String> col_desc1 ; 
    @FXML
    private Button statsbutton;
    @FXML
    private Pane panerejoindre;
    @FXML
    private Button participer;
    @FXML
    private ImageView object;
    @FXML
    private TextField montantfamille;
    @FXML
    private ProgressBar progfam;
    @FXML
    private Label labelFamilleObj;
    @FXML
    private Label labelFamilleAcc;
    @FXML
    private TableColumn<?, ?> col_id_fam;
    @FXML
    private TableColumn<?, ?> cold_desc_fam;
    @FXML
    private Label labeldescFamille;
    @FXML
    private Label hour;
    @FXML
    private Label minute;
    @FXML
    private Label second;
    @FXML
    private Label NomParticipant1;
    @FXML
    private Label telParticipant1;
    @FXML
    private Label montantParticipant1;
    @FXML
    private Label NomParticipant2;
    @FXML
    private Label telParticipant2;
    @FXML
    private Label montantParticipant2;
    @FXML
    private Label NomParticipant3;
    @FXML
    private Label telParticipant3;
    @FXML
    private Label montantParticipant3;
    @FXML
    private PasswordField motdepassparticipant;
    @FXML
    private TextField montantparticipant;
    @FXML
    private ImageView participantProfilepic1;
    @FXML
    private ImageView participantProfilepic2;
    @FXML
    private ImageView participantProfilepic3;
    @FXML
    private Text finished;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
       increaseprogress2();
       FillBesoincombo();
       FillBesoincomboTransport();
       FillTableView();
       increaseprogress();
       FilltextField();
       FilltextField1();
       FillTableView1();
       FillTableView2();
       FillBesoincombo2();
       FilltextFieldFam();
       increaseprogressFam();
       print2.setVisible(false);
       print1.setVisible(false);
       print.setVisible(false);
       panerejoindre.setVisible(true);
       new RollIn(object).setCycleCount(25).setDelay(Duration.valueOf("500ms")).play();
     

     }
      
       
     ObservableList<Besoin> oblist = FXCollections.observableArrayList();
     ObservableList<Besoin> Transport = FXCollections.observableArrayList();
     ObservableList<Besoin> Familles = FXCollections.observableArrayList();
     
    @FXML
    private void getmontant(ActionEvent event){
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        DonationCrud dc = new DonationCrud();
        Don d = new Don(); 
        //User u = new User();
            float montantdonne = Float.parseFloat(montant.getText());
            d.setDonorId(us.getActualUserId()); // us.getactualuserid();
            
            //d.setEventId(0);
            d.setCategorie("Transport");
            d.setDonationDate(date.format(formatter));
            d.setMontant(montantdonne);
            dc.AjouterDons(d);
            dc.ModifierDonneur(us,d);
            System.out.println("clicked");
           String x = (String)ComboBesoinTransport.getValue();
          
           float y=montantdonne;
            dc.ModifierBesoin(x,y);
            FilltextField1();
            increaseprogress2();
            
            print.setVisible(true);
           
            
            
    }
    @FXML
    public void FilltextField(){
        try {
            Statement ste ;
          
            String sql = "Select montantactuel,montant,description from Besoin where (categorie='Hopiteaux'and (montant-montantactuel) <> 0) order by montant desc LIMIT 1 ";
            ResultSet rs ;
           MyConnection cnx = MyConnection.getInstance();
            ste = cnx.getConnection().prepareStatement(sql);
            rs = ste.executeQuery(sql);
            
            while (rs.next()) {
               float montantac = (rs.getFloat("montantactuel"));
               float montant =(rs.getFloat("montant"));
               String desc = rs.getString("description");
                labelacc.setText(String.format("%.0f",montantac));
                labelbesoin.setText(String.format("%.0f",montant));
                labeldesc.setText(desc);
                
                
                
            }  
                 
        
    } catch (SQLException ex) {
        Logger.getLogger(DonsScreenController.class.getName()).log(Level.SEVERE, null, ex);
    }}
    @FXML
    public void FilltextField1(){
        try {
            Statement ste ;
            ResultSet rs ;
            String sql = "Select montantactuel,montant,description from Besoin where (categorie='Transport'and (montant-montantactuel) <> 0) order by montant desc LIMIT 1 ";
              
           MyConnection cnx = MyConnection.getInstance();
            ste = cnx.getConnection().prepareStatement(sql);
            rs = ste.executeQuery(sql);
        
            
            while (rs.next()) {
               float montantac = (rs.getFloat("montantactuel"));
               float montant =(rs.getFloat("montant"));
               String desc = rs.getString("description");
                labelacc1.setText(String.format("%.0f",montantac));
                labelbesoin1.setText(String.format("%.0f",montant));
                labeldesc1.setText(desc);
                
                
            }  
                 
        
    } catch (SQLException ex) {
        Logger.getLogger(DonsScreenController.class.getName()).log(Level.SEVERE, null, ex);
    }}
    public void FillTableView(){
        col_id.setCellValueFactory(new PropertyValueFactory<>("idBesoin"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        /**************************************************************/
         try {
            Statement ste ;
            
            
            String sql = "Select idBesoin,description from Besoin where categorie='Hopiteaux' ";
            ResultSet rs ;
            MyConnection cnx = MyConnection.getInstance();
            ste = cnx.getConnection().prepareStatement(sql);
            rs = ste.executeQuery(sql);
            
            while (rs.next()) {
               
               
                oblist.add(new Besoin(rs.getInt("idBesoin"),rs.getString("description")));
                
            }  
            table.setItems(oblist);
        } catch (SQLException ex) {
            Logger.getLogger(DonsScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }}    
    @FXML
    public void FillTableView1(){
        col_id1.setCellValueFactory(new PropertyValueFactory<>("idBesoin"));
        col_desc1.setCellValueFactory(new PropertyValueFactory<>("description"));
        /**************************************************************/
         try {
            Statement ste ;
            
            
            String sql = "Select idBesoin,description from Besoin where categorie='Transport' ";
            ResultSet rs ;
            MyConnection cnx = MyConnection.getInstance();
            ste = cnx.getConnection().prepareStatement(sql);
            rs = ste.executeQuery(sql);
            
            while (rs.next()) {
               
               
                Transport.add(new Besoin(rs.getInt("idBesoin"),rs.getString("description")));
                
            }  
            table1.setItems(Transport);
        } catch (SQLException ex) {
            Logger.getLogger(DonsScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        /****************************************************************/
    }     
     public void FillTableView2(){
          
        col_id_fam.setCellValueFactory(new PropertyValueFactory<>("idBesoin"));
        cold_desc_fam.setCellValueFactory(new PropertyValueFactory<>("description"));
        /**************************************************************/
         try {
            Statement ste ;
            
            
            String sql = "Select idBesoin,description from Besoin where categorie='Familles' ";
            ResultSet rs ;
            MyConnection cnx = MyConnection.getInstance();
            ste = cnx.getConnection().prepareStatement(sql);
            rs = ste.executeQuery(sql);
            
            while (rs.next()) {
               
               
                Familles.add(new Besoin(rs.getInt("idBesoin"),rs.getString("description")));
                
            }  
            table2.setItems(Familles);
        } catch (SQLException ex) {
            Logger.getLogger(DonsScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        /****************************************************************/
    }    
    @FXML
    public void increaseprogress() {
       
    try {
        Statement ste ;
        String sql = "Select montant,montantactuel,description from Besoin where categorie='Hopiteaux' and (montant-montantactuel) <> 0 order by montant desc  LIMIT 1 ";
        ResultSet rs ;
        MyConnection cnx = MyConnection.getInstance();
        ste = cnx.getConnection().prepareStatement(sql);
        rs = ste.executeQuery(sql);
        while(rs.next()){
         Float montant = rs.getFloat("montant");
         Float montantac = rs.getFloat("montantactuel");
          progressCalculator pc = new progressCalculator();
          float x = pc.progressCalculator(montant, montantac);
          prog.setProgress(x);
            System.out.println(x);

         
      
        }
        
    } catch (SQLException ex) {
        Logger.getLogger(DonsScreenController.class.getName()).log(Level.SEVERE, null, ex);
    }

}
    @FXML
    public void increaseprogress2() {
       
    try {
        Statement ste ;
        String sql = "Select montant,montantactuel,description from Besoin where categorie='Transport' and (montant-montantactuel) <> 0  order by montant desc  LIMIT 1 ";
        ResultSet rs ;
        MyConnection cnx = MyConnection.getInstance();
        ste = cnx.getConnection().prepareStatement(sql);
        rs = ste.executeQuery(sql);
        while(rs.next()){
         float montant = rs.getFloat("montant");
         float montantac = rs.getFloat("montantactuel");
         progressCalculator pc = new progressCalculator();
         float x = pc.progressCalculator(montant, montantac);                  
         prog1.setProgress(x);
       
        }
        
    } catch (SQLException ex) {
        Logger.getLogger(DonsScreenController.class.getName()).log(Level.SEVERE, null, ex);
    }

}
    @FXML
    public void FillBesoincomboTransport(){
          ObservableList Transport = FXCollections.observableArrayList();
        try {
            Statement ste ;
          
            String sql = "Select description from Besoin where categorie='Transport' ";
            ResultSet rs ;
            MyConnection cnx = MyConnection.getInstance();
            ste = cnx.getConnection().prepareStatement(sql);
            rs = ste.executeQuery(sql);
            
            while (rs.next()) {
               
               
                Transport.add(rs.getString("description"));
                
            }  
            ComboBesoinTransport.setItems(Transport);
        } catch (SQLException ex) {
            Logger.getLogger(DonsScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void FillBesoincombo(){
          ObservableList besoin = FXCollections.observableArrayList();
        try {
            Statement ste ;
            
            
            String sql = "Select description from Besoin where categorie='Hopiteaux' ";
            ResultSet rs ;
            MyConnection cnx = MyConnection.getInstance();
            ste = cnx.getConnection().prepareStatement(sql);
            rs = ste.executeQuery(sql);
            
            while (rs.next()) {
               
               
                besoin.add(rs.getString("description"));
                
            }  
            ComboBesoin.setItems(besoin);
        } catch (SQLException ex) {
            Logger.getLogger(DonsScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionDonsPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void persist1(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionDonsPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void persist2(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionDonsPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    @FXML
    public void openscene(ActionEvent event) throws IOException{
         Parent part = FXMLLoader.load(getClass().getResource("/Views/Item.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(part);
        stage.setScene(scene);
        stage.show();
       
    }

    @FXML
    private void getmontant1(ActionEvent event) throws Exception {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        DonationCrud dc = new DonationCrud();
        Don d = new Don();   
        User u = new User();
            float montantdonne = Float.parseFloat(montantHosp.getText());
             d.setDonorId(us.getActualUserId());
            
            d.setCategorie("Hopiteaux");
            d.setDonationDate(date.format(formatter));
            d.setMontant(montantdonne);
            dc.AjouterDons(d);
            System.out.println("clicked");
           String x = (String)ComboBesoin.getValue();
          
           float y=montantdonne;
            dc.ModifierBesoin(x,y);
            dc.ModifierDonneur(us,d);
            FilltextField();
            increaseprogress();
            print1.setVisible(true);
           // MailSend.sendMail("kaidoro.hh@gmail.com");
               
    }

    @FXML
    private void openscenestat(ActionEvent event) throws IOException {
         Parent part = FXMLLoader.load(getClass().getResource("/Views/StatsDash.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(part);
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void participerencher(ActionEvent event) throws InterruptedException {
        panerejoindre.setVisible(true);
        new Flash(panerejoindre).play();
        int amountOfHours = Integer.parseInt(hour.getText());
          int amountOfMinutes = Integer.parseInt(minute.getText());
        int amountOfSeconds = Integer.parseInt(second.getText());
        int time = amountOfHours *3600 +  amountOfMinutes * 60 + amountOfSeconds;
        ParticiperEnchere(us);
        if(timeline.getStatus() == Status.STOPPED )
         showCountDown(time);
          finished.setText("Temps restant");
          
        
    }
    private Timeline timeline = new Timeline();
        private void showCountDown(int time) {
       DonationCrud dc = new DonationCrud();
Don d = new Don();
        int nextFrameTime = 0;

        for (int i = 0; i <= time; time--) {
            int finalTime = time;

            timeline.getKeyFrames().add(new KeyFrame(
                            Duration.millis(nextFrameTime),
                            action -> {
                                //hour.setText(String.format("%02d", finalTime / 3600));
                                minute.setText(String.format("%02d", (finalTime / 120)));
                                second.setText(String.format("%02d", (finalTime % 60)));
                                if("00".equals(minute.getText()) && "01".equals(second.getText()) ){
              float x =dc.montantGagnant(montantParticipant1, montantParticipant2, montantParticipant3);
              String winner =dc.Whoisthewinner(x, montantParticipant1, montantParticipant2, montantParticipant3, telParticipant1, telParticipant2, telParticipant3);
              dc.gagne(winner);
              dc.AjouterWinnerDons(winner,d,x);
                   NomParticipant1.setText("Pas de participant");
          NomParticipant2.setText("Pas de participant");
          NomParticipant3.setText("Pas de participant");
          telParticipant1.setText("Pas de participant");
          telParticipant2.setText("Pas de participant");
          telParticipant3.setText("Pas de participant");
          montantParticipant1.setText("Pas de participant");
          montantParticipant2.setText("Pas de participant");
          montantParticipant3.setText("Pas de participant");
         }
                            }
                    )
            );
            nextFrameTime += 1000;
         
        }
         
        timeline.setCycleCount(1);
        timeline.setOnFinished(event -> 
                finished.setText("Finished") );
        
        timeline.play();
       
     
             
    }
        /*public void eventOver(){
            DonationCrud dc = new DonationCrud();
            if(dc.VerifyEventOver(finished)){
             float x =dc.montantGagnant(montantParticipant1, montantParticipant2, montantParticipant3);
              String winner =dc.Whoisthewinner(x, montantParticipant1, montantParticipant2, montantParticipant3, telParticipant1, telParticipant2, telParticipant3);
              dc.gagne(winner);
            }
        }*/
     
  
          
 
    @FXML
    private void getmontant2(ActionEvent event) {
         LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        DonationCrud dc = new DonationCrud();
        Don d = new Don();    
        User u =new User();
            float montantdonne = Float.parseFloat(montantfamille.getText());
            d.setDonorId(1);
            d.setEventId(4);
            d.setCategorie("Familles");
            d.setDonationDate(date.format(formatter));
            d.setMontant(montantdonne);
            dc.AjouterDons(d);
            dc.ModifierDonneur(us,d);
            System.out.println("clicked");
           String x = (String)ComboBesoin2.getValue();
          
           float y=montantdonne;
            dc.ModifierBesoin(x,y);
            FilltextField();
            increaseprogress();
            print2.setVisible(true);
    }

    
    private void FillBesoincombo2() {
         ObservableList Familles = FXCollections.observableArrayList();
        try {
            Statement ste ;
          
            String sql = "Select description from Besoin where categorie='Familles' ";
            ResultSet rs ;
            MyConnection cnx = MyConnection.getInstance();
            ste = cnx.getConnection().prepareStatement(sql);
            rs = ste.executeQuery(sql);
            
            while (rs.next()) {
               
               
                Familles.add(rs.getString("description"));
                
            }  
            ComboBesoin2.setItems(Familles);
        } catch (SQLException ex) {
            Logger.getLogger(DonsScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void increaseprogressFam() {
         try {
        Statement ste ;
        String sql = "Select montant,montantactuel,description from Besoin where categorie='Familles' and (montant-montantactuel) <> 0  order by montant desc  LIMIT 1 ";
        ResultSet rs ;
        MyConnection cnx = MyConnection.getInstance();
        ste = cnx.getConnection().prepareStatement(sql);
        rs = ste.executeQuery(sql);
        while(rs.next()){
         float montant = rs.getFloat("montant");
         float montantac = rs.getFloat("montantactuel");
         progressCalculator pc = new progressCalculator();
         float x = pc.progressCalculator(montant, montantac);                  
         progfam.setProgress(x);
       
        }
        
    } catch (SQLException ex) {
        Logger.getLogger(DonsScreenController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void FilltextFieldFam() {
        try {
            Statement ste ;
          
            String sql = "Select montantactuel,montant,description from Besoin where (categorie='Familles'and (montant-montantactuel) <> 0) order by montant desc LIMIT 1 ";
            ResultSet rs ;
           MyConnection cnx = MyConnection.getInstance();
            ste = cnx.getConnection().prepareStatement(sql);
            rs = ste.executeQuery(sql);
            
            while (rs.next()) {
               float montantac = (rs.getFloat("montantactuel"));
               float montant =(rs.getFloat("montant"));
               String desc = rs.getString("description");
                labelFamilleAcc.setText(String.format("%.0f",montantac));
                labelFamilleObj.setText(String.format("%.0f",montant));
                labeldescFamille.setText(desc);
                
                
                
            }  
                 
        
    } catch (SQLException ex) {
        Logger.getLogger(DonsScreenController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    public void ParticiperEnchere(UserSession us){
        DonationCrud dc = new DonationCrud();
     float montantpartic = Float.parseFloat(montantparticipant.getText());
     String motDePasse = motdepassparticipant.getText();
     if(dc.passIsValid(us,motDePasse)==true){
         if(dc.dejaParticipe(us, NomParticipant1, telParticipant1)){
             dc.participant(us ,NomParticipant1,telParticipant1,montantParticipant1,montantpartic);
         }
         else if(dc.dejaParticipe(us, NomParticipant2, telParticipant2)){
             dc.participant(us ,NomParticipant2,telParticipant2,montantParticipant2,montantpartic);
         }
         else if(dc.dejaParticipe(us, NomParticipant3, telParticipant3)){
             dc.participant(us ,NomParticipant3,telParticipant3,montantParticipant3,montantpartic);
         }
         
    else {
             if("Pas de participant".equals(NomParticipant1.getText())){
             dc.participant(us,NomParticipant1,telParticipant1,montantParticipant1,montantpartic);
            
               }
                    else if("Pas de participant".equals(NomParticipant2.getText())){
                    dc.participant(us,NomParticipant2,telParticipant2,montantParticipant2,montantpartic);
                    }
                    else if("Pas de participant".equals(NomParticipant3.getText())){
                    dc.participant(us,NomParticipant3,telParticipant3,montantParticipant3,montantpartic);
            
                     }
            else {
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            String tilte = "ERROR";
            String message = "Session pleine";
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(3));
     }
             
         }
        
     }
           
       
         
     }
    
    }
    



 
   
   

