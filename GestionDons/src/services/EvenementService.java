/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Don;
import entities.Evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DbConnection;

/**
 *
 * @author SeifD
 */
public class EvenementService {
    
    private Connection cnx;
    private PreparedStatement ste;
    
    public EvenementService(){
        cnx = DbConnection.getInstance().getConnection();
    }
    
    public void ajouterEvenement(Evenement event){
        
            String req = "INSERT INTO evenement(associationId, donCategorie,cause,Region,num_participants,date_creation,montant_collecte,description)"+" VALUES (?,?,?,?,?,?,?,?)";
            try {
            ste = cnx.prepareStatement(req);
            ste.setInt(1, event.getAssociationId());
            ste.setString(2, event.getDonCategorie());
            ste.setString(3, event.getCause());
            ste.setString(4, event.getRegion());
            ste.setInt(5, event.getNum_participants());
            ste.setString(6, event.getDate_creation().toString());
            ste.setFloat(7, event.getMontant_collecte());
            ste.setString(8, event.getDescription());
            ste.executeUpdate();
            System.out.println("Evenment ajouté avec succée !");
            
        } catch (SQLException ex) {
            System.out.println("Erreur Ajout Evenement");
        }
          
    }
   
    public void supprimerEvenement(int id){
        
            String req = "DELETE FROM evenement WHERE evenement.eventId="+id;
            try {
            ste = cnx.prepareStatement(req);
            ste.executeUpdate();
            System.out.println("Event deleted");
        } catch (SQLException ex) {
                System.out.println("Erreur suppression event");
        }
    }
     
    public Evenement getEvent(int id){
        Evenement e = new Evenement();
        String req = "SELECT * from evenement where eventId ="+id;
            try {
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                
                e.setEventId(rs.getInt("evenement.eventId"));
                e.setAssociationId(rs.getInt("evenement.associationId"));
                e.setDonCategorie(rs.getString("evenement.donCategorie"));
                e.setCause(rs.getString("evenement.cause"));
                e.setRegion(rs.getString("evenement.Region"));
                e.setNum_participants(rs.getInt("evenement.num_participants"));
                e.setDate_creation(rs.getDate("evenement.date_creation"));
                e.setMontant_collecte(rs.getFloat("evenement.montant_collecte"));
                e.setDescription(rs.getString("evenement.description"));

            }
            System.out.println("getMyParticipations Success");
            
        } catch (SQLException ex) {
                System.out.println("Erreur getMyParticipations");
        }
        return e;
    }
    
    public void updateEvent(int id, Evenement event){
        
        String req = "UPDATE evenement SET associationId=?,donCategorie=?,cause=?,Region=?,num_participants=?,date_creation=?,montant_collecte=?,description=? WHERE evenement.eventId=" + id;
        
        try{
            ste = cnx.prepareStatement(req);
            ste.setInt(1, event.getAssociationId());
            ste.setString(2, event.getDonCategorie());
            ste.setString(3, event.getCause());
            ste.setString(4, event.getRegion());
            ste.setInt(5, event.getNum_participants());
            ste.setString(6, event.getDate_creation().toString());
            ste.setFloat(7, event.getMontant_collecte());
            ste.setString(8, event.getDescription());
            ste.executeUpdate();
            System.out.println("Event updated");           
        }
        catch(SQLException ex){
            System.out.println("Erreur update event");
            
        }
        
    }
    
    public ObservableList<Evenement> showAllEvents(){
        ObservableList<Evenement> events = FXCollections.observableArrayList();
        try{
            String req = "select * from evenement";
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                Evenement e = new Evenement();
                e.setEventId(rs.getInt("eventId"));
                e.setAssociationId(rs.getInt("associationId"));
                e.setDonCategorie(rs.getString("donCategorie"));
                e.setCause(rs.getString("cause"));
                e.setRegion(rs.getString("Region"));
                e.setNum_participants(rs.getInt("num_participants"));
                e.setDate_creation(rs.getDate("date_creation"));
                e.setMontant_collecte(rs.getFloat("montant_collecte"));
                e.setDescription(rs.getString("description"));
                events.add(e);   
            }      
        }
        catch(SQLException ex){
            System.out.println("Erreur affichage evenements");
        }
        return events;
    }
    
    public void updateNumParticipants(Evenement e){
        String req = "UPDATE evenement SET num_participants=? WHERE evenement.eventId=" + e.getEventId();
        
        try{
            ste = cnx.prepareStatement(req);
            ste.setInt(1, e.getNum_participants() + 1);
            ste.executeUpdate();
            System.out.println("NbParticipants updated");           
        }
        catch(SQLException ex){
            System.out.println("Erreur update NbParticipants");
            
        }
        
        
    }
    
    
    //Initialiser l'interface ParticiperEvent avec le premier evenement présent
    public Evenement getFirstEvent(){
        Evenement e = new Evenement();
        try{
            String req = "select * from evenement LIMIT 1";
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                e.setEventId(rs.getInt("eventId"));
                e.setAssociationId(rs.getInt("associationId"));
                e.setDonCategorie(rs.getString("donCategorie"));
                e.setCause(rs.getString("cause"));
                e.setNum_participants(rs.getInt("num_participants"));
                e.setDate_creation(rs.getDate("date_creation"));
                e.setMontant_collecte(rs.getFloat("montant_collecte"));
                e.setDescription(rs.getString("description"));               
            }      
        }
        catch(SQLException ex){
            System.out.println("Erreur affichage evenements");
        }
        return e;
    }
    
    
    public int getNombreEvents(){
        int nombre = 0;
        String req = "SELECT COUNT(*) from evenement";
            try {
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            if(rs.next()){
                nombre = rs.getInt(1);           
            }
            
        } catch (SQLException ex) {
                System.out.println("Erreur getNombreEvents");
        }
        return nombre;
    }
    
    public int getNombreParticipants(){
        int nombre = 0;
        String req = "SELECT COUNT(*) from event_user";
            try {
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            if(rs.next()){
                nombre = rs.getInt(1);           
            }
            
        } catch (SQLException ex) {
                System.out.println("Erreur getNombreEvents");
        }
        return nombre;
    }
    
    
    
    
    //Suggestions for autocompletition seach field
    public Set<String> getSuggests(){
        
        Set<String> categories = new HashSet<>();
        Set<String> causes = new HashSet<>();
        Set<String> Suggests = new HashSet<>();
        try{
            String req = "select donCategorie from evenement";
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                String categ;
                categ = rs.getString("donCategorie");              
                categories.add(categ);               
            }      
        }
        catch(SQLException ex){
            System.out.println("Erreur Suggestion Cause");
        }
        
        
        try{
            String req = "select cause from evenement";
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                String cause;
                cause = rs.getString("cause");              
                causes.add(cause);               
            }      
        }
        catch(SQLException ex){
            System.out.println("Erreur Suggestion Categories");
        }
        
        Suggests.addAll(causes);
        Suggests.addAll(categories);
        
        return Suggests;   
    }
    
    public Set<Integer> getAllAssociationId(){
        Set<Integer> ids = new HashSet<>();
        try{
        String req = "select userId from user where role='Association'";
        ste = cnx.prepareStatement(req);
        ResultSet rs = ste.executeQuery();
        while (rs.next()){
            int assocId;
            assocId = rs.getInt("userId");              
            ids.add(assocId);               
            }      
        }
        catch(SQLException ex){
            System.out.println("Erreur retrieving Association IDS");
        }
        
        return ids;
    }
    
    public Set<String> getRegions(){
        Set<String> names = new HashSet<>();
        try{
        String req = "select name from regions";
        ste = cnx.prepareStatement(req);
        ResultSet rs = ste.executeQuery();
        while (rs.next()){
            String name;
            name = rs.getString("name");              
            names.add(name);               
            }      
        }
        catch(SQLException ex){
            System.out.println("Erreur retrieving Regions");
        }
        
        return names;
    }
    
    
    
    
    //Permet d'extraire tous les nom des associations ayant créer des évenements
    public Set<String> getAssocActive(){
        Set<String> names = new HashSet<>();
        String assocName;
        String req = "SELECT user.name from evenement INNER JOIN user ON user.userId = evenement.associationId ORDER BY evenement.eventId DESC";
            try {
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while(rs.next()){
                assocName = rs.getString("user.name");
                names.add(assocName);
            }
            
            
        } catch (SQLException ex) {
                System.out.println("Erreur getNombreEvents");
        }
        
        return names;
    }
    
    public String getAssocActive1(){
        Set<String> names = getAssocActive();
        String name;
        name = names.iterator().next();
        return name;
    }
    
    public String getAssocActive2(){
        Set<String> names = getAssocActive();
        int i = 0;
        for (String assoc : names){
            if (i==1)
                return assoc;
            i++;
        }
        return null;
    }
    
    public String getAssocActive3(){
        Set<String> names = getAssocActive();
        int i = 0;
        for (String assoc : names){
            if (i==2)
                return assoc;
            i++;
        }
        return null;
    }
    
    public String getAssocNameById(int id){
        String name="";
        String req = "select user.name from user INNER JOIN evenement on evenement.associationId=user.userId WHERE user.userId = "+id;
            try {
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            if(rs.next()){
                name = rs.getString("name");   
            }
            
            
        } catch (SQLException ex) {
                System.out.println("Erreur getNombreEvents");
        }
            
            return name;
        
    }
    
    public void participerEvent(int eventId, int userId){
        String req = "INSERT INTO event_user (eventId, userId)"+" VALUES (?,?)";
        try {
            ste = cnx.prepareStatement(req);
            ste.setInt(1, eventId);
            ste.setInt(2, userId);
            ste.executeUpdate();
            
            System.out.println("Participation avec succées !");
            
        } catch (SQLException ex) {
            System.out.println("Erreur Participation Evenement");
        }
    }
    
    public Set<String> getUsersParticipated(){
        Set<String> names = new HashSet<>();
        String name;
        String req = "SELECT user.name from user INNER JOIN event_user ON user.userId = event_user.userId";
            try {
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while(rs.next()){
                name = rs.getString("name");
                names.add(name); 
            }
            
            
        } catch (SQLException ex) {
                System.out.println("Erreur get Nom des Participants");
        }
        
        return names;
        
    }
    
    public Set<String> getEmailByRegion(String region){
        
        Set<String> emails = new HashSet<>();
        String email;
        String req = "SELECT mail from user WHERE city ='"+region+"'";
            try {
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while(rs.next()){
                email = rs.getString("user.mail");
                emails.add(email); 
            }
            
            
        } catch (SQLException ex) {
                System.out.println("Erreur aqcuisition mailList");
        }
        
        return emails;
        
    }
    
    
    
    //Tous les evenements que l'utilisateur courant à participé
    public ObservableList<Evenement> getMyParticipations(int id){
 
        ObservableList<Evenement> myEvents = FXCollections.observableArrayList();
        //List<Evenement> myEvents = new ArrayList<>();
        String req = "SELECT * from evenement INNER JOIN event_user ON evenement.eventId = event_user.eventId where event_user.userId ="+id;
            try {
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                Evenement e = new Evenement();
                e.setEventId(rs.getInt("evenement.eventId"));
                e.setAssociationId(rs.getInt("evenement.associationId"));
                e.setDonCategorie(rs.getString("evenement.donCategorie"));
                e.setCause(rs.getString("evenement.cause"));
                e.setRegion(rs.getString("evenement.Region"));
                e.setNum_participants(rs.getInt("evenement.num_participants"));
                e.setDate_creation(rs.getDate("evenement.date_creation"));
                e.setMontant_collecte(rs.getFloat("evenement.montant_collecte"));
                e.setDescription(rs.getString("evenement.description"));
                myEvents.add(e);
            }
            System.out.println("getMyParticipations Success");
            
        } catch (SQLException ex) {
                System.out.println("Erreur getMyParticipations");
        }
        return myEvents;

}
    
    // Pour initialiser l'affichage de l'interface Donscreen Avec le premier evenement que l'utilisateur courant ayant deja participé
    public Evenement getMyFirstEvent(int id){
        Evenement e = new Evenement();
        try{
            String req = "SELECT * from evenement INNER JOIN event_user ON evenement.eventId = event_user.eventId where event_user.userId ="+id+" LIMIT 1";
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                e.setEventId(rs.getInt("eventId"));
                e.setAssociationId(rs.getInt("associationId"));
                e.setDonCategorie(rs.getString("donCategorie"));
                e.setCause(rs.getString("cause"));
                e.setNum_participants(rs.getInt("num_participants"));
                e.setDate_creation(rs.getDate("date_creation"));
                e.setMontant_collecte(rs.getFloat("montant_collecte"));
                e.setDescription(rs.getString("description"));               
            }      
        }
        catch(SQLException ex){
            System.out.println("Erreur affichage evenements");
        }
        return e;
    }
    
    
    //Retourne le montant actuel d'un besoin selon la categorie
    public float getBesoinByCategorie(String categ){
        
        float montant=0;
        String req = "SELECT montantactuel from besoin WHERE categorie ='"+categ+"'";
            try {
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while(rs.next()){
                montant += rs.getFloat("montantactuel");
            }
            
            
        } catch (SQLException ex) {
                System.out.println("Erreur aqcuisition BesoinByCategorie");
        }
            
            return montant;
        
    }
 
    
    
    
    
    
    /*
    Permet de :
        -Inserer un nouveau don dans la table Don 
        -Mise a jour de la table Besoin (Diminution du besoin total de la categorie de l'évenement)
        -Mise a jour de la table evenement (Montant donné)
        -Inserer un nouveau recu dans la table don
    */
    public void ajouterDon(int idUser, int idEvent, float montant, String categ){
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        String req = "INSERT INTO don (donorId, eventId,donationDate,montant,Categorie)"+" VALUES (?,?,?,?,?)";
        try {
            ste = cnx.prepareStatement(req);
            ste.setInt(1, idUser);
            ste.setInt(2, idEvent);
            ste.setString(3, date.format(formatter));
            ste.setFloat(4, montant);
            ste.setString(5, categ);
            ste.executeUpdate();
            System.out.println("Don ajouté avec succées");
            
        } catch (SQLException ex) {
            System.out.println("Erreur Ajout Don");
        }
        
        //*************************************************************************
        
        float montantBesoin = getBesoinByCategorie(categ) + montant;
        String req2 = "UPDATE besoin SET montantactuel=? WHERE categorie ='"+categ+"'";
        
        try{
            ste = cnx.prepareStatement(req2);
            ste.setFloat(1, montantBesoin);
            
            ste.executeUpdate();
            System.out.println("Besoin updated");           
        }
        catch(SQLException ex){
            System.out.println("Erreur update Besoin");
            
        }
        
        //*************************************************************************
        
        String req3 = "UPDATE evenement SET montant_collecte=? WHERE evenement.eventId=" + idEvent;
        float montant_collecte = getEvent(idEvent).getMontant_collecte() + montant;
        try{
            ste = cnx.prepareStatement(req3);
            ste.setFloat(1, montant_collecte);
            ste.executeUpdate();
            System.out.println("Event updated");           
        }
        catch(SQLException ex){
            System.out.println("Erreur update event");
            
        }
        
        //*************************************************************************
        
           
        
    }
    
    //Retourne tous les categorie des besoins existants
    public Set<String> getAllBesoinCategories(){
        String categ;
        Set<String> categories = new HashSet<>();
        
        try{
            String req = "SELECT * from besoin";
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                categ = rs.getString("categorie");
                categories.add(categ);
            }
            
        }catch(SQLException ex){
            System.out.println("Erreur getAllBesoinCategories");
        }
        return categories;
    }
    
    //Vérifie si lebesoin de la categorie donné existe dans la BD 
    public boolean besoinExist(String categ){
        final boolean exists = false;
        Set<String> allBesoinCateg = getAllBesoinCategories();
        if (allBesoinCateg.contains(categ))
            return true;
        else{
            return false;
        }
    }
    
    //Retourne le montant a atteindre d'un besoin selon la categorie
    public float getBesoinTotalByCategorie(String categ){
        
        float montant=0;
        String req = "SELECT montant from besoin WHERE categorie ='"+categ+"'";
            try {
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while(rs.next()){
                montant += rs.getFloat("montant");
            }
            
            
        } catch (SQLException ex) {
                System.out.println("Erreur aqcuisition BesoinTotalByCategorie");
        }
            
            return montant;
        
    }
    
    //Mise a jour du besoin total de la categorie mentionnée lors de la création d'un évènement
    public void updateBesoinTotal(String categ, float montant){
        //String req2 = "UPDATE besoin SET montant=? WHERE categorie ='"+categ+"'";
        String req2 = "update besoin SET montant=? where categorie='"+categ+"'";
        Float newMontant = getBesoinTotalByCategorie(categ) + montant;
        
        try{
            ste = cnx.prepareStatement(req2);
            ste.setFloat(1, newMontant);
            
            ste.executeUpdate();
            System.out.println("Besoin updated");           
        }
        catch(SQLException ex){
            System.out.println("Erreur update Besoin");
            
        }
        
    }
    
    //Inserer une nouvelle ligne dans la table besoin lors de la création d'un évènement
    public void addBesoin(float montant, String categorie, float montantActuel){
        
        String req = "INSERT INTO besoin(montant, quantite,categorie,description,montantactuel)"+" VALUES (?,?,?,?,?)";
            try {
            ste = cnx.prepareStatement(req);
            ste.setFloat(1, montant);
            ste.setString(2, null);
            ste.setString(3, categorie);
            ste.setString(4, null);
            ste.setInt(5, 0);
            ste.executeUpdate();
            System.out.println("Besoin ajouté avec succée !");
            
        } catch (SQLException ex) {
            System.out.println("Erreur Ajout Besoin");
        }
        
    }
    
    
    //Permet de retourner la somme total des dons collectés par tous les evenements créers
    public float getAllDonsFromEvents(){
        float montant = 0;
        
        try{
            String req = "SELECT montant_collecte from evenement";
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                montant += rs.getFloat("montant_collecte");
            }
            
        }catch(SQLException ex){
            System.out.println("Erreur getAllDonsFromEvents");
        }
        
        return montant;
        
    }
    
    
}
    

