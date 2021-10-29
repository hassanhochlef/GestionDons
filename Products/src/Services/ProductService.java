/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Product;
import Utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */

public class ProductService {
    public Connection cnx;
    private PreparedStatement ste;


public ProductService() {
        cnx = MyConnection.getInstance().getConnection();
    }
    
    public void ajouterProduit(Product p){
        String req ="INSERT INTO produit (donorId,name,quantite,prix_approx)values (?,?,?,?)";
        try {
            ste = cnx.prepareStatement(req);
            ste.setInt(1, p.getDonorId());
            ste.setString(2, p.getName());
            ste.setInt(3, p.getQuantite());
            ste.setInt(4, p.getPrix_approx());
            ste.executeUpdate();
            System.out.println("Produit ajouté");
            
        } catch (SQLException ex) {
            System.out.println("Erreur Ajout produit");
            System.out.println(ex.getMessage());
        }
        
    }
    
   public List<Product> afficherProduit(){
    List<Product> Produits = new ArrayList<>();
            String sql = "select * from product";
        try {
             
     
             ste = cnx.prepareStatement(sql);
             ResultSet rs = ste.executeQuery();
             Product p = new Product();
             while (rs.next()) {   
                
                 p.setProductId(rs.getInt("productId"));
                 p.setDonorId(rs.getInt("donorId"));
                 p.setName(rs.getString("name"));
                 p.setQuantite(rs.getInt("quantite"));
                 p.setPrix_approx(rs.getInt("prix_approx"));
                 Produits.add(p);
               
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    return  Produits;
    
}
  
    public void SupprimerProduit(int ProductId){
        
            String req = "DELETE FROM Produit WHERE p.ProductId="+ProductId;
            try {
            ste = cnx.prepareStatement(req);
            ste.executeUpdate();
            
            System.out.println("product deleted");
        } catch (SQLException ex) {
                System.out.println("Erreur suppression produit");
        }
    }
    
   /* public List<Product> SupprimerProduit(int productId){
        List<Product> Produits = new ArrayList<>() ;
    try {

       String sql = "DELETE FROM Produit WHERE productId=?";
       

PreparedStatement statement = cnx.prepareStatement(sql);
statement.setInt(1,productId);

int rowsDeleted = statement.executeUpdate();
if (rowsDeleted ==1) {
    System.out.println("A product was deleted ");
}


        System.out.println(Produits);
    } 
        catch (SQLException ex) {
        Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
    }
    return Produits;
}*/
    
public List<Product> ModifierProduit(int productId, String Name){
        List<Product> Produits = new ArrayList<>() ;
    try {

       String sql = "UPDATE Produit SET productId='"+productId+"',"+"Name='"+Name+"'where productId='"+productId+"'";

PreparedStatement statement = cnx.prepareStatement(sql);


int rowsDeleted = statement.executeUpdate();
if (rowsDeleted ==1) {
    System.out.println("A product was updated");
}


        System.out.println(Produits);
    } 
        catch (SQLException ex) {
        Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
    }
    return Produits;
}



    
}

