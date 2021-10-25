/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Connection.MyConnection;
import Entities.Besoin;
import Service.DonationCrud;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import animatefx.animation.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
/**
 * FXML Controller class
 *
 * @author Hassan
 */
public class StatsDashController implements Initializable {

    @FXML
    private PieChart chart;
    @FXML
    private Label totDonneur;
    @FXML
    private Label nbrDons;
    @FXML
    private Label montantDons;
    @FXML
    private LineChart<String,Float> linechart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new FadeIn(chart).play();
        try {
            StatsFill();
        } catch (SQLException ex) {
            Logger.getLogger(StatsDashController.class.getName()).log(Level.SEVERE, null, ex);
        }
        piechartSetup();
        Linechart();
        
    }
     public Connection cnx;
    ObservableList<PieChart.Data> pieChartData;
     ResultSet rs ;
    public void Linechart(){
        linechart.setTitle(" Dons / Jour ");
        String sql = "select donationDate,montant from don ORDER BY donationDate asc";
        XYChart.Series<String,Float> series = new XYChart.Series<>();
        try {
           cnx = MyConnection.getInstance().getConnection();
            PreparedStatement statement = cnx.prepareStatement(sql);
            rs = statement.executeQuery();
            while(rs.next()){
                series.getData().add(new XYChart.Data<>(rs.getString(1),rs.getFloat(2)));
            }
            linechart.getData().add(series);
        }catch(Exception e){
            
        }
        
        
    }
    public void StatsFill() throws SQLException{
        DonationCrud dc = new DonationCrud();
      int x =  dc.CalculStatsDonneurs();
      int y = dc.CalculStatsNbrDons();
      float z = dc.CalculMontant();
      totDonneur.setText(String.valueOf(x));
      nbrDons.setText(String.valueOf(y));
      montantDons.setText(String.valueOf(z));
              }
             
              
              
     public void piechartSetup(){
   pieChartData = FXCollections.observableArrayList();
        try {
            Statement ste ;                  
            String sql = "Select montantactuel,description from Besoin";
            ResultSet rs ;
            MyConnection cnx = MyConnection.getInstance();
            ste = cnx.getConnection().prepareStatement(sql);
            rs = ste.executeQuery(sql);
            while (rs.next()) {            
            pieChartData.add(new PieChart.Data(rs.getString("description"),rs.getFloat("montantactuel")));       
           chart.setLabelsVisible(true);
           chart.setTitle("Dons Collectés");
            }
            chart.setData(pieChartData);
        } catch (SQLException e) {
            System.out.println("erour");
        }
        
        
    }
    
}
