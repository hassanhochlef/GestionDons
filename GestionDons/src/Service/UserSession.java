/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Connection.MyConnection;

/**
 *
 * @author SeifD
 */
public class UserSession {
    
    private Connection cnx;
    private PreparedStatement ste;
    
    public UserSession(){
        cnx = MyConnection.getInstance().getConnection();
    }
    
    public User getUserConnected(String mail){
        User u = new User();
        String req = "SELECT * from user WHERE user.mail='"+mail+"'";
            try {
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                u.setUserId(rs.getInt("userId"));
                u.setName(rs.getString("name"));
                u.setPhoto(rs.getString("photo"));
                u.setPassword(rs.getString("password"));
                u.setCity(rs.getString("city"));
                u.setGouvernorat(rs.getString("gouvernorat"));
                u.setPhone(rs.getString("phone"));
                u.setMail(rs.getString("mail"));
                u.setRole(rs.getString("role"));
                u.setMontant_donne(rs.getFloat("montant_donne"));
            }
            
        } catch (SQLException ex) {
                System.out.println("Erreur Get User Connected");
        }
            return u;
    }
    
    public boolean verifyUser(String mail, String password){
        boolean exists = false;
        List<User> users = new ArrayList<>();
        String req = "SELECT * from user WHERE user.mail='"+mail+"' AND user.password='"+password+"'";
            try {
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()){
                User u = new User();
                u.setUserId(rs.getInt("userId"));
                u.setName(rs.getString("name"));
                u.setPhoto(rs.getString("photo"));
                u.setPassword(rs.getString("password"));
                u.setCity(rs.getString("city"));
                u.setGouvernorat(rs.getString("gouvernorat"));
                u.setPhone(rs.getString("phone"));
                u.setMail(rs.getString("mail"));
                u.setRole(rs.getString("role"));
                u.setMontant_donne(rs.getFloat("montant_donne"));
                users.add(u);
            }
            
            
        } catch (SQLException ex) {
                System.out.println("Erreur Verify User");
        }
            if (users.size()==1)
                exists=true;
            return exists;
        
    }
    
    public void addUserSession(User u){
        deleteUserSession();
        String req = "INSERT INTO user_connected(userId,name,photo,password,city,gouvernorat,phone,mail,role,montant_donne)"+" VALUES (?,?,?,?,?,?,?,?,?,?)";
            try {
            ste = cnx.prepareStatement(req);
            ste.setInt(1, u.getUserId());
            ste.setString(2, u.getName());
            ste.setString(3, u.getPhone());
            ste.setString(4, u.getPassword());
            ste.setString(5, u.getCity());
            ste.setString(6, u.getGouvernorat());
            ste.setString(7, u.getPhone());
            ste.setString(8, u.getMail());
            ste.setString(9, u.getRole());
            ste.setFloat(10, u.getMontant_donne());
            ste.executeUpdate();
            System.out.println("Utilisateur connecté ajouté");
            
        } catch (SQLException ex) {
            System.out.println("Erreur Ajout user connected");
        }
        
    }
    
    public void deleteUserSession(){
        String req = "DELETE FROM user_connected";
            try {
            ste = cnx.prepareStatement(req);
            ste.executeUpdate();
            System.out.println("UserSession deleted");
        } catch (SQLException ex) {
                System.out.println("Erreur suppression UserSession");
        }
    }
    
    public int getActualUserId(){
        String req = "SELECT user_connected.userId from user_connected";
        int id=0;
            try {
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            
            while (rs.next()){
                id = rs.getInt("userId");
            }
            
            
        } catch (SQLException ex) {
                System.out.println("Erreur Verify User");
        }
            return id;
    }
    
    public String getActualUserName(){
        
        String req = "SELECT * from user_connected";
        String name = "";
            try {
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            
            while (rs.next()){
                name = rs.getString("name");
            }
            
            
        } catch (SQLException ex) {
                System.out.println("Erreur getActualUserName");
        }
            return name;
    }
    
    public String getActualUserPhoto(){
        
        String req = "SELECT * from user_connected";
        String photo = "";
            try {
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            
            while (rs.next()){
                photo = rs.getString("photo");
            }
            
            
        } catch (SQLException ex) {
                System.out.println("Erreur getActualUserName");
        }
            return photo;
    }
    
    public String getActualUserPassword(){
        
        String req = "SELECT * from user_connected";
        String password = "";
            try {
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            
            while (rs.next()){
                password = rs.getString("password");
            }
            
            
        } catch (SQLException ex) {
                System.out.println("Erreur getActualUserPhoto");
        }
            return password;
    }
    
    public String getActualUserCity(){
        
        String req = "SELECT * from user_connected";
        String city = "";
            try {
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            
            while (rs.next()){
                city = rs.getString("city");
            }
            
            
        } catch (SQLException ex) {
                System.out.println("Erreur getActualUserCity");
        }
            return city;
    }
    
    public String getActualUserGouvernorat(){
        
        String req = "SELECT * from user_connected";
        String gouvernorat = "";
            try {
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            
            while (rs.next()){
                gouvernorat = rs.getString("gouvernorat");
            }
            
            
        } catch (SQLException ex) {
                System.out.println("Erreur getActualUserGouvernorat");
        }
            return gouvernorat;
    }
    
    public String getActualUserPhone(){
        
        String req = "SELECT * from user_connected";
        String phone = "";
            try {
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            
            while (rs.next()){
                phone = rs.getString("phone");
            }
            
            
        } catch (SQLException ex) {
                System.out.println("Erreur getActualUserPhone");
        }
            return phone;
    }
    
    public String getActualUserMail(){
        
        String req = "SELECT * from user_connected";
        String mail = "";
            try {
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            
            while (rs.next()){
                mail = rs.getString("mail");
            }
            
            
        } catch (SQLException ex) {
                System.out.println("Erreur getActualUserMail");
        }
            return mail;
    }
    
    public String getActualUserRole(){
        
        String req = "SELECT * from user_connected";
        String role = "";
            try {
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            
            while (rs.next()){
                role = rs.getString("role");
            }
            
            
        } catch (SQLException ex) {
                System.out.println("Erreur getActualUserRole");
        }
            return role;
    }
    
    public Float getActualUserMontantDonne(){
        
        String req = "SELECT * from user_connected";
        float montant_donne = 0;
            try {
            ste = cnx.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            
            while (rs.next()){
                montant_donne = rs.getFloat("montant_donne");
            }
            
            
        } catch (SQLException ex) {
                System.out.println("Erreur getActualUserMontantDonne");
        }
            return montant_donne;
    }
    
        
    
    
    
    
}
