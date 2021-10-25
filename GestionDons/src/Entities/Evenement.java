/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author SeifD
 */
public class Evenement {
    private int eventId;
    private int associationId;
    private String donCategorie;
    private String cause;
    private String Region;
    private int num_participants;
    private Date date_creation;
    private float montant_collecte;
    private String description;

    public Evenement(){
        
    }

    public Evenement(int associationId, String donCategorie, String cause, String Region, int num_participants, Date date_creation, float montant_collecte, String description) {
        this.associationId = associationId;
        this.donCategorie = donCategorie;
        this.cause = cause;
        this.Region = Region;
        this.num_participants = num_participants;
        this.date_creation = date_creation;
        this.montant_collecte = montant_collecte;
        this.description = description;
    }

    public Evenement(int eventId, int associationId, String donCategorie, String cause, String Region, int num_participants, Date date_creation, float montant_collecte, String description) {
        this.eventId = eventId;
        this.associationId = associationId;
        this.donCategorie = donCategorie;
        this.cause = cause;
        this.Region = Region;
        this.num_participants = num_participants;
        this.date_creation = date_creation;
        this.montant_collecte = montant_collecte;
        this.description = description;
    }

   

    public int getAssociationId() {
        return associationId;
    }

    public void setAssociationId(int associationId) {
        this.associationId = associationId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    
    
    

    public String getDonCategorie() {
        return donCategorie;
    }

    public void setDonCategorie(String donCategorie) {
        this.donCategorie = donCategorie;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String Region) {
        this.Region = Region;
    }
    
    

    public int getNum_participants() {
        return num_participants;
    }

    public void setNum_participants(int num_participants) {
        this.num_participants = num_participants;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public float getMontant_collecte() {
        return montant_collecte;
    }

    public void setMontant_collecte(float montant_collecte) {
        this.montant_collecte = montant_collecte;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Evenement{" + "eventId=" + eventId + ", associationId=" + associationId + ", donCategorie=" + donCategorie + ", cause=" + cause + ", Region=" + Region + ", num_participants=" + num_participants + ", date_creation=" + date_creation + ", montant_collecte=" + montant_collecte + ", description=" + description + '}';
    }

    
    

   

    
    
    
    
    
    
    
    
    
}
