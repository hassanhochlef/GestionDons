/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author ahmed
 */
public class MyConnection {  
    /**
     *
     */
    public String URL="jdbc:mysql://localhost:3306/bugbustersprojet";
    public String User="root";
    public String Password="";
    public Connection cnx;
    public static MyConnection ct;
    private MyConnection() {
        try {
            cnx = DriverManager.getConnection(URL, User, Password);
            System.out.println("Connection établie");
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.err.println("Problème de connection");
        }
        
    }
    //interdire la creation des plusieurs instance dans la base de donnée
    public Connection getConnection(){
            return cnx;
        }
    public static MyConnection getInstance(){
        if(ct==null){
            ct=new MyConnection();
        }
       
        return ct;
    }
    
    
    
    
    
}
