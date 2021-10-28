/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author SeifD
 */
public class Recompense {
    
    int recompenseId;
    int donorId;
    int nbrePoints;
    
    public Recompense(){
        
    }

    public Recompense(int donorId, int nbrePoints) {
        this.donorId = donorId;
        this.nbrePoints = nbrePoints;
    }

    public Recompense(int recompenseId, int donorId, int nbrePoints) {
        this.recompenseId = recompenseId;
        this.donorId = donorId;
        this.nbrePoints = nbrePoints;
    }

    public int getRecompenseId() {
        return recompenseId;
    }

    public void setRecompenseId(int recompenseId) {
        this.recompenseId = recompenseId;
    }

    public int getDonorId() {
        return donorId;
    }

    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }

    public int getNbrePoints() {
        return nbrePoints;
    }

    public void setNbrePoints(int nbrePoints) {
        this.nbrePoints = nbrePoints;
    }

    @Override
    public String toString() {
        return "Recompense{" + "recompenseId=" + recompenseId + ", donorId=" + donorId + ", nbrePoints=" + nbrePoints + '}';
    }
    
    
            
    
}
