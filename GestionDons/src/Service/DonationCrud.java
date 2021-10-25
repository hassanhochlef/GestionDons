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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
/******************************************************************************/
  public List<User> ModifierDonneur(User u,Don d){
        List<User> user = new ArrayList<>() ;
    try {
        int x = d.getDonorId();
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

   

    
}
