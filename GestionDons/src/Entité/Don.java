/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entité;

import java.util.Date;


/**
 *
 * @author khoubaib
 */

public class Don {
    private int serviceId;
    private int donorId; 
    private String TypeService;
    private String lieu;
    private  Date DateDisponibilité ;
    private String déscription;
    

    public Don() {
        super();
    }

    public Don(int serviceId, int donorId, String TypeService, String lieu, Date DateDisponibilité,String déscription) {
        this.serviceId = serviceId;
        this.donorId = donorId;
        this.TypeService = TypeService;
        this.lieu = lieu;
        this.DateDisponibilité = DateDisponibilité;
        this.déscription = déscription;
    }

    public Don(int donorId, String TypeService, String lieu, Date DateDisponibilité, String déscription) {
        this.donorId = donorId;
        this.TypeService = TypeService;
        this.lieu = lieu;
        this.DateDisponibilité = DateDisponibilité;
        this.déscription = déscription;
    }
    
 
    public int getDonorId() {
        return donorId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getTypeService() {
        return TypeService;
    }

    public String getLieu() {
        return lieu;
    }

    

   public Date getDateDisponibilité() {
        return DateDisponibilité;
    }

    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public void setTypeService(String TypeService) {
        this.TypeService = TypeService;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setDateDisponibilité(Date DateDisponibilité) {
        this.DateDisponibilité = DateDisponibilité;
    }

    public void setDéscription(String déscription) {
        this.déscription = déscription;
    }

    public String getDéscription() {
        return déscription;
    }

    @Override
    public String toString() {
        return "Don{" + "serviceId=" + serviceId + ", donorId=" + donorId + ", TypeService=" + TypeService + ", lieu=" + lieu + ", DateDisponibilit\u00e9=" + DateDisponibilité + ", d\u00e9scription=" + déscription + '}';
    }

   

  


    
}
