/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import utils.DbConnection;

/**
 *
 * @author SeifD
 */
public class RecompenseService {
    
    private Connection cnx;
    private PreparedStatement ste;
    
    public RecompenseService(){
        cnx = DbConnection.getInstance().getConnection();
    }
    
    
    //Permet d'inserer ou faire une mise a jour de la table recompense
    public void updateRecompense(int id){
        
        int newPts = getNbrePts(id);
        //Update de la table avec le nouveau nbre de points
        if (checkExist(id)){
            
            String req = "UPDATE recompense SET nbrePoints=? WHERE donorId=" + id;
        
            try{
                ste = cnx.prepareStatement(req);
                ste.setInt(1, newPts);
                ste.executeUpdate();
                System.out.println("NbPoints updated");           
            }
            catch(SQLException ex){
                System.out.println("Erreur update NbPoints");

            }
            
        }
        
        //Insertion d'une nouvelle recompense
        else{
            String req = "INSERT INTO recompense(donorId,nbrePoints)"+" VALUES (?,?)";
            try {
            ste = cnx.prepareStatement(req);
            ste.setInt(1, id);
            ste.setInt(2, newPts);
            ste.executeUpdate();
            System.out.println("Recompense ajouté avec succée !");
            
        } catch (SQLException ex) {
            System.out.println("Erreur Ajout Recompense");
        }
            
        }
        
    }
    
    public boolean checkExist(int id){
        Set<Integer> ids = new HashSet<>();
        try {
            String req = "SELECT * from recompense";
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                ids.add(rs.getInt("donorId"));
            }
            
        } catch (SQLException ex) {
            System.out.println("Erreur Recompense Exists");
        }
        
        if (ids.contains(id))
            return true;
        else
            return false;
    }
    
    //Permet de retourner le nombre de points de l'utilisateur connecté dont l'id est en parametre
    public int getNbrePts(int id){
        int pts;
        float montant=0;
        try {
            String req = "SELECT * from user where userId="+id;
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                montant = rs.getFloat("montant_donne");
            }
            
        } catch (SQLException ex) {
            System.out.println("Erreur select montantDonne ");
        }
        
        pts = (int)montant/10;
        return pts;
    }
    
    
    
    ///////////////////////////////////////
    public void updateUserMontant(int id, float montant){
        float newMontant=0;
        try {
            String req0 = "SELECT * from user where userId="+id;
            ste = cnx.prepareStatement(req0);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                newMontant = rs.getFloat("montant_donne");
            }
            
        } catch (SQLException ex) {
            System.out.println("Erreur select montantDonne ");
        }
        
        newMontant += montant;
        
        
        
        String req = "UPDATE user SET montant_donne=? WHERE userId=" + id;
        
        try{
            ste = cnx.prepareStatement(req);
            ste.setFloat(1, newMontant);
            ste.executeUpdate();
            System.out.println("Montant updated");           
        }
        catch(SQLException ex){
            System.out.println("Erreur update Montant");
            
        }
        
        String req2 = "UPDATE user_connected SET montant_donne=? WHERE userId=" + id;
        
        try{
            ste = cnx.prepareStatement(req2);
            ste.setFloat(1, newMontant);
            ste.executeUpdate();
            System.out.println("Montant updated");           
        }
        catch(SQLException ex){
            System.out.println("Erreur update Montant");
            
        }
    }
    
}
