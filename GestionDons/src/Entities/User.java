/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;



/**
 *
 * @author LENOVO
 */
public class User {
    
    private int userId ;
    private String name;
    private String photo;
    private String password;
    private String city;
    private String gouvernorat ;
    private String phone;
    private String mail;
    private String role;
    private float montant_donne;

    public User() {
    }

    public User(int userId, String name,String password, String city, String gouvernorat, String phone, String mail, String role, float montant_donne) {
        this.userId = userId;
        this.name = name;
        this.password=password;
        this.city = city;
        this.gouvernorat = gouvernorat;
        this.phone = phone;
        this.mail = mail;
        this.role = role;
        this.montant_donne = montant_donne;
    }

    public User(String name, String city, String gouvernorat, String phone, String mail, String role) {
        this.name = name;
        this.password=password;
        this.city = city;
        this.gouvernorat = gouvernorat;
        this.phone = phone;
        this.mail = mail;
        this.role = role;
    }

    public User(String name, String password, String city, String gouvernorat, String phone, String mail, String role, float montant_donne) {
        this.name = name;
        this.password = password;
        this.city = city;
        this.gouvernorat = gouvernorat;
        this.phone = phone;
        this.mail = mail;
        this.role = role;
        this.montant_donne = montant_donne;
    }

    public User(String name, String photo, String password, String city, String gouvernorat, String phone, String mail, String role) {
        this.name = name;
        this.photo = photo;
        this.password = password;
        this.city = city;
        this.gouvernorat = gouvernorat;
        this.phone = phone;
        this.mail = mail;
        this.role = role;
        this.montant_donne = montant_donne;
    }
    
    

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public float getMontant_donne() {
        return montant_donne;
    }

    public void setMontant_donne(float montant_donne) {
        this.montant_donne = montant_donne;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", name=" + name+", password=" + password + ", city=" + city + ", gouvernorat=" + gouvernorat + ", phone=" + phone + ", mail=" + mail + ", role=" + role + ", montant_donne=" + montant_donne + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.userId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }
    
    
    
    
   
    
}


