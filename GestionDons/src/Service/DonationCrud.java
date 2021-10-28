/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Controllers.DonsScreenController;
import Connection.MyConnection;
import Entities.Besoin;

import Entities.Don;

import Entities.User;
//import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tray.notification.TrayNotification;

import tray.animations.*;
import tray.notification.NotificationType;
//import java.util.Date;


/**
 *
 * @author Hassan
 */
public class DonationCrud {
     public Connection cnx;
public PreparedStatement ste;
public DonationCrud(){
        cnx = MyConnection.getInstance().getConnection();
        
}
public void AjouterDons(Don d){
    try {
        String sql="insert into Don(donorId,eventId,categorie,montant,donationDate)" + "values(?,?,?,?,?)";
        ste =cnx.prepareStatement(sql);
        ste.setInt(1,d.getDonorId());
        ste.setInt(2,d.getEventId());
        ste.setString(3,d.getCategorie());
        ste.setFloat(4,d.getMontant());
        ste.setString(5,d.getDonationDate());
        ste.executeUpdate();
        System.out.println("\nSucces d'ajout");
    } catch (SQLException ex) {
       System.out.println(ex.getMessage());
       System.out.println("no");
    }
    }
public List<Don> afficherDons(){
        List<Don> donation = new ArrayList<>() ;
    try {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String sql = "Select * from Don";
        ResultSet rs ;
        ste = cnx.prepareStatement(sql);
        rs = ste.executeQuery();
        
        while (rs.next()) {
            
            Don p = new Don() ;
            p.setDonId(rs.getInt("donId"));
            p.setCategorie(rs.getString(2));
            
            p.setMontant(rs.getFloat(3));
            p.setDonationDate(rs.getString(4));
            donation.add(p);
            
    }
        System.out.println(donation);
    } 
        catch (SQLException ex) {
        Logger.getLogger(DonationCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
    return donation;
}
public List<Don> SupprimerDons(){
        List<Don> donation = new ArrayList<>() ;
    try {
        
       String sql = "DELETE FROM Don WHERE donId=?";
    
PreparedStatement statement = cnx.prepareStatement(sql);
statement.setInt(1, 7);
 
int rowsDeleted = statement.executeUpdate();
if (rowsDeleted > 0) {
    System.out.println("A donation was deleted successfully!");
}
        
       
        System.out.println(donation);
    } 
        catch (SQLException ex) {
        Logger.getLogger(DonationCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
    return donation;
}
public List<Don> ModifierDons(){
        List<Don> donation = new ArrayList<>() ;
    try {
        
       String sql = "UPDATE Don SET categorie=?,montant=?,donationDate=? where donId=5";
    
PreparedStatement statement = cnx.prepareStatement(sql);

statement.setInt(1,300);
statement.setFloat(2,800);

 
int rowsDeleted = statement.executeUpdate();
if (rowsDeleted > 0) {
    System.out.println("A donation was updated!");
}
        
       
        System.out.println(donation);
    } 
        catch (SQLException ex) {
        Logger.getLogger(DonationCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
    return donation;
}
public List<Besoin> ModifierBesoin(String x,float y){
    float d = 0 ;
        List<Besoin> besoin = new ArrayList<>() ;
        ObservableList<Besoin> Transport = FXCollections.observableArrayList();
    try {
        String sql2 = "SELECT montant from Besoin where description='"+x+"'" ;
        String sql1 = "SELECT montantactuel,montant from Besoin where description='"+x+"'" ;
        PreparedStatement statement1 = cnx.prepareStatement(sql1);
       PreparedStatement statement2 = cnx.prepareStatement(sql2);
        ResultSet rs2 = statement2.executeQuery();
        ResultSet rs = statement1.executeQuery();
        while(rs2.next()){
         d = rs2.getFloat("montant");
        }
       String sql = "UPDATE Besoin SET montantactuel=? where description='"+x+"'";
   
PreparedStatement statement = cnx.prepareStatement(sql);
while(rs.next()){
float montantac = rs.getFloat("montantactuel");
        System.out.println(montantac);
 if(d < montantac + y )
 {
   TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            String tilte = "ERROR";
            String message = "Le montant a depassé l'objectif";
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(3));
            
 }
 else {
montantac += y ;
statement.setFloat(1, montantac);
 }
}
 
int rowsDeleted = statement.executeUpdate();
if (rowsDeleted > 0) {
    System.out.println("A donation was updated!");
}
        
       
        System.out.println(besoin);
    } 
        catch (SQLException ex) {
        Logger.getLogger(DonationCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
    return besoin;
}
public int CalculStatsDonneurs() throws SQLException{
    int nombre = 0;
     String Requete_nbr_donneur = "SELECT count(*) from user where montant_donne <>0 " ;
     //String Requete_nbr_dons = "Select count(*) from don";
     //String Requete_montant_total = "Select montantactuel from Besoin ";
      PreparedStatement statement_donneur = cnx.prepareStatement(Requete_nbr_donneur);
      ResultSet rs_donneur = statement_donneur.executeQuery();
      while(rs_donneur.next()){
          nombre = rs_donneur.getInt(1);
      }
      return nombre;
}
public int CalculStatsNbrDons() throws SQLException{
    int nombre = 0;
     
     String Requete_nbr_dons = "Select count(*) from don";
     //String Requete_montant_total = "Select montantactuel from Besoin ";
      PreparedStatement statement_donneur = cnx.prepareStatement(Requete_nbr_dons);
      ResultSet rs_don = statement_donneur.executeQuery();
      while(rs_don.next()){
          nombre = rs_don.getInt(1);
      }
      return nombre;
}
public float CalculMontant() throws SQLException{
    int nombre = 0;
    
     String Requete_montant_total = "Select montantactuel from Besoin ";
      PreparedStatement statement_donneur = cnx.prepareStatement(Requete_montant_total);
      ResultSet rs_montanttot = statement_donneur.executeQuery();
      while(rs_montanttot.next()){
          
          nombre += rs_montanttot.getInt(1);
      }
      return nombre;
}
public void afficherrecu(Label fieldDate, Label fieldMontant, Label fieldCategorie , Label fieldName, Label fieldTel ){
      
    try {
        
        String sql = "SELECT donationDate,montant,Categorie,name,phone  FROM recu INNER JOIN don ON recu.donationId = don.donId INNER JOIN user ON user.userId=don.donorId ";
        ResultSet rs ;
        ste = cnx.prepareStatement(sql);
        rs = ste.executeQuery();
        String date;
        float montant;
        String Categorie;
        String name;
        String tel;
        while (rs.next()) {
            
            
            date = rs.getString(1);
            montant = rs.getFloat(2);
            Categorie = rs.getString(3);
            name = rs.getString(4);
            tel = rs.getString(5);
            fieldDate.setText(String.valueOf(date));
            fieldMontant.setText(String.valueOf(montant));
            fieldCategorie.setText(String.valueOf(Categorie));
            fieldName.setText(String.valueOf(name));
            fieldTel.setText(String.valueOf(tel));
          
    }
       
    } 
        catch (SQLException ex) {
        Logger.getLogger(DonationCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
   
}
public List<User> ModifierDonneur(UserSession u,Don d){
        List<User> user = new ArrayList<>() ;
    try {
        int x = u.getActualUserId();
        float y = 0;
        String sql1 = "SELECT SUM(montant) from Don where donorId='"+x+"'"; 
       String sql = "UPDATE User SET montant_donne=? where userId='"+x+"'";
    
PreparedStatement statement = cnx.prepareStatement(sql);
PreparedStatement statement1 =cnx.prepareStatement(sql1);
        //ResultSet rs = statement.executeQuery();
        ResultSet rs1 = statement1.executeQuery();
while(rs1.next()){
    y=rs1.getFloat(1);
    System.err.println(y);
}
statement.setFloat(1, y);

 
int rowsDeleted = statement.executeUpdate();
if (rowsDeleted > 0) {
    System.out.println("A donation was updated!");
}
        
       
        System.out.println(user);
    } 
        catch (SQLException ex) {
        Logger.getLogger(DonationCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
    return user;
} 
  
/*public void participerEnchere(UserSession us){
   int id = us.getActualUserId();
   String pass = us.getActualUserPassword();

}*/
   public boolean passIsValid(UserSession u,String pwd){
       if(u.getActualUserPassword().equals(pwd)){
           return true;
       }
       TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            String tilte = "ERROR";
            String message = "Le mot de passe est incorrect";
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(3));
       return false;
   } 
   public void participant(UserSession u ,Label name,Label participantTel,Label participantmontant,Float montant){
       name.setText(u.getActualUserName());
       participantTel.setText(u.getActualUserPhone());
       participantmontant.setText(String.valueOf(montant));
   
   }
   public boolean dejaParticipe(UserSession us,Label participantNom ,Label participantTel ){
       if((participantNom.getText().equals(us.getActualUserName())) && (participantTel.getText().equals(us.getActualUserPhone())) ){
           
           return true;
       }
       return false;
   }
   public float montantGagnant(Label participant1,Label participant2,Label participant3){
       float max0 = 0;
       float max1 = 0;
       if(!"Pas de participant".equals(participant1.getText()) &&  "Pas de participant".equals(participant2.getText())){
           return max1= Float.parseFloat(participant1.getText()) ;
       }
       else if(!"Pas de participant".equals(participant1.getText()) &&  !"Pas de participant".equals(participant2.getText()) && "Pas de participant".equals(participant3.getText()) )
        return max1 = Math.max(Float.parseFloat(participant1.getText()), Float.parseFloat(participant2.getText())) ;
        
       else{ 
           
           max0 = Math.max(Float.parseFloat(participant1.getText()), Float.parseFloat(participant2.getText()));
           return max1 = Math.max(max0, Float.parseFloat(participant3.getText()));
       
       }

       
     
   

    
}
   public String Whoisthewinner(float max,Label montantParticipant1,Label montantParticipant2,Label montantParticipant3,Label phoneParticipant1,Label phoneParticipant2,Label phoneParticipant3){
       
       if(max == Float.parseFloat(montantParticipant1.getText())){
          return phoneParticipant1.getText(); 
       }
       else if (max == Float.parseFloat(montantParticipant2.getText())){
          return phoneParticipant2.getText(); 
       }
       return phoneParticipant3.getText();
   }
public void gagne(String winner){
    TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            String tilte = "Félicitation";
            String message = "Num tel : " +winner ;
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.seconds(3));
            
}
public Boolean VerifyEventOver(Text finish){
    
       if("Finished".equals(finish.getText()))
       { 
          return true;
          }
       return false;
}
public void AjouterWinnerDons(String winner,Don d,float x){
    try {
         LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        User u = new User();
        String  mywinner = "SELECT * from user where user.phone='"+winner+"'";
       PreparedStatement statement = cnx.prepareStatement(mywinner);
       ResultSet rs = statement.executeQuery();
       while(rs.next()){
           
           u.setUserId((rs.getInt("userId")));
           u.setName(rs.getString("name"));
           u.setPhone(rs.getString("phone"));
           
       }
            
        String sql="insert into Don(donorId,eventId,categorie,montant,donationDate)" + "values(?,?,?,?,?)";
        ste =cnx.prepareStatement(sql);
        ste.setInt(1,u.getUserId());
        ste.setInt(2,0);
        ste.setString(3,"Enchere");
        ste.setFloat(4,x);
        ste.setString(5,date.format(formatter));
        ste.executeUpdate();
        System.out.println("\nSucces d'ajout");
    } catch (SQLException ex) {
       System.out.println(ex.getMessage());
       System.out.println("no");
    }
    }
public void AjouterBesoinD(Besoin b){
    try {
        String sql="insert into Besoin(idBesoin,montant,quantite,categorie,description,montantactuel,photo)" + "values(?,?,?,?,?,?,?)";
        ste =cnx.prepareStatement(sql);
        ste.setInt(1,b.getIdBesoin());
        ste.setFloat(2,b.getMontant());
        ste.setInt(3,b.getQuantite());
        ste.setString(4,b.getCategorie());
        ste.setString(5,b.getDescription());
        ste.setFloat(6,b.getMontantactuel());
        ste.setString(7,b.getPhoto());
        ste.executeUpdate();
        System.out.println("\nSucces d'ajout");
    } catch (SQLException ex) {
       System.out.println(ex.getMessage());
       System.out.println("no");
    }
    }


public List<Besoin> afficherObjet(){
        List<Besoin> objet = new ArrayList<>() ;
    try {
       
        String sql = "Select montant,categorie,description,photo from besoin";
        ResultSet rs ;
        ste = cnx.prepareStatement(sql);
        rs = ste.executeQuery();
        
        while (rs.next()) {
            
            Besoin p = new Besoin() ;
            p.setIdBesoin(rs.getInt("idBesoin"));
            p.setCategorie(rs.getString(2));
            p.setDescription(rs.getString(3));
            p.setPhoto(rs.getString(4));
            
            objet.add(p);
            
    }
      
    } 
        catch (SQLException ex) {
        Logger.getLogger(DonationCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
    return objet;
}
public void affObjet(Label x,Label y , ImageView z) throws SQLException{
   
 String sql = "Select montant,description,photo from besoin where categorie='Enchere' LIMIT 1";
 ste = cnx.prepareStatement(sql);
  ResultSet rs = ste.executeQuery();
        try {
       
        while(rs.next()){
                 x.setText(Float.toString(rs.getFloat(1)));
                  y.setText(rs.getString(2));
           z.setImage(new Image("file:///"+rs.getString(3)));
       
        }
      
    } 
        catch (SQLException ex) {
            System.out.println("");
    }
    }
 public void SupprimerObjects(){
       
    try {
        
       String sql = "DELETE FROM besoin WHERE categorie='Enchere'";
    
PreparedStatement statement = cnx.prepareStatement(sql);

 
int rowsDeleted = statement.executeUpdate();
if (rowsDeleted > 0) {
    System.out.println("A donation was deleted successfully!");
}
        
       
 
    } 
        catch (SQLException ex) {
        Logger.getLogger(DonationCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
   
}
}
 
 /*if(Float.parseFloat(participant1.getText()) < Float.parseFloat(participant2.getText()))
           max = Float.parseFloat(participant2.getText());
       else {
           max = Float.parseFloat(participant1.getText());
       }  */