/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Hassan
 */
public class Don {
    private int donId;
    private int donorId;
    private int eventId;
    private String donationDate;
    private float montant;
    private String Categorie;

    public Don() {
    }

    public Don(int donId, String donationDate, float montant, String Categorie) {
        this.donId = donId;
        this.donationDate = donationDate;
        this.montant = montant;
        this.Categorie = Categorie;
    }

    public Don(int donId, int donorId, int eventId, String donationDate, float montant, String Categorie) {
        this.donId = donId;
        this.donorId = donorId;
        this.eventId = eventId;
        this.donationDate = donationDate;
        this.montant = montant;
        this.Categorie = Categorie;
    }

    public int getDonId() {
        return donId;
    }

    public void setDonId(int donId) {
        this.donId = donId;
    }

    public int getDonorId() {
        return donorId;
    }

    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String Categorie) {
        this.Categorie = Categorie;
    }
    
    
}
