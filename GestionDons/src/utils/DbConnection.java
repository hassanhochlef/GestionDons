/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author SeifD
 */
public class DbConnection {
    
    public String url="jdbc:mysql://localhost:3306/bugbustersprojet";
    public String login="root";
    public String pwd="";
    static DbConnection instance=null;
    Connection cnx;

    private DbConnection() {
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.out.println("Pas de connexion");
        }
    }

    public static DbConnection getInstance(){
        if(instance== null){
            instance = new DbConnection();
        }
        return instance ;
    }

    public Connection getConnection(){
        return cnx;
    }
    
}
