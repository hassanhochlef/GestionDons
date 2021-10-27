/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Connection.MyConnection;
import Entité.Don;
import Service.DonService;
import java.util.Date;
//import java.sql.Date;


/**
 *
 * @author khoubaib
 */
public class GestionDon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyConnection mc = MyConnection.getInstance();
        DonService ds = new DonService();
        
       //Don d2 = new Don(2,"transport","Manouba","01-02-2021","je suis un chauffeur de taxi");
 
        
       //ds.ajouterDon(d2);
       
   /*   d1.setServiceId(25);
        d1.setLieu("Ariana");         
        d1.setTypeService("construction");
        ds.modifierDone(d1);
        ds.supprimerDone(31);
        System.out.println(ds.afficherDoneService());
    */
  
    }
    
    
}
