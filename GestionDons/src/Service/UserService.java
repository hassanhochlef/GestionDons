/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Connection.MyConnection;
import Entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */

    public class UserService {
      private Connection cnx;
        private PreparedStatement ste;

        
        
        public UserService() {
        cnx = MyConnection.getInstance().getConnection();
    }

        
        public void addUser(User u){
        String req ="INSERT INTO user (name,password,city,phone,mail,role)"+"values (?,?,?,?,?,?)";
        try {
            ste = cnx.prepareStatement(req);
            ste.setString(1, u.getName());
            ste.setString(2, u.getPassword());
            ste.setString(3, u.getCity());
           
            ste.setString(4, u.getPhone());
            ste.setString(5, u.getMail());
            ste.setString(6, u.getRole());
            ste.executeUpdate();
            System.out.println("User added");
            
        } catch (SQLException ex) {
            System.out.println("error add");
            
        }
        
    }
      /* 
         public User VerifyUser(String mail, String password) {
        User u = new User();

         //query
            String sql = "SELECT * FROM user Where mail = '"+mail+"'" +" and password = '" +password+"'";
            try {
                ste = cnx.prepareStatement(sql);
               
                 ResultSet rs = ste.executeQuery();
                 u.setMail(rs.getString("mail"));
                 u.setPassword(rs.getString("password"));
                if (!rs.next()) {
                    System.out.println("user not found");
                 
                } else {
                    System.out.println("user found!");
         
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
            
            return u;
        }
*/
    }





    