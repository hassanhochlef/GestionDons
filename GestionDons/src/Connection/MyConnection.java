/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.*;

/**
 *
 * @author Hassan
 */
public class MyConnection {
    public String url="jdbc:mysql://localhost:3306/bugbustersprojet";
    public String user="root";
    public String password="";
    public Connection cnx;
    public static MyConnection ct ;
    private MyConnection(){
        try {
            
            cnx = DriverManager.getConnection(url, user, password);
            System.out.print("Success");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

   
    
    public Connection getConnection(){
        return cnx;
    }

    public static MyConnection getInstance() {
        if(ct==null){
            ct= new MyConnection();
        }
      
            return ct;
        
    }
    
    
}
