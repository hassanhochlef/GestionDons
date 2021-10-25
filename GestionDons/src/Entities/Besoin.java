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
public class Besoin {
    private int  idBesoin ;
    private float montant;
    private int quantite;
    private String categorie;
    private String description;
    private float montantactuel;

    public Besoin(int idBesoin, float montant, int quantite, String categorie, String description, float montantactuel) {
        this.idBesoin = idBesoin;
        this.montant = montant;
        this.quantite = quantite;
        this.categorie = categorie;
        this.description = description;
        this.montantactuel = montantactuel;
    }

    public Besoin(int idBesoin, String description) {
        this.idBesoin = idBesoin;
        this.description = description;
    }

    public Besoin() {
    }

    

    public int getIdBesoin() {
        return idBesoin;
    }

    public void setIdBesoin(int idBesoin) {
        this.idBesoin = idBesoin;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getMontantactuel() {
        return montantactuel;
    }

    public void setMontantactuel(float montantactuel) {
        this.montantactuel = montantactuel;
    }
    
    
}
