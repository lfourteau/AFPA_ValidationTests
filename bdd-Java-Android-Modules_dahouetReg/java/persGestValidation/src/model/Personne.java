/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;

/**
 *
 * @author afpa
 */
public class Personne {

    String nom;
    String prenom;
    String email;
    int anneeNaissance;

    public Personne(String nom, String prenom, String email, int anneeNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.anneeNaissance = anneeNaissance;
    }

    private int calculAge() {     
        int actualYear = Calendar.getInstance().get(Calendar.YEAR);
        int age = actualYear-anneeNaissance;
        return age;
    }

    public int getAnneeNaissance() {
        return anneeNaissance;
    }  

    @Override
    public String toString() {
        return prenom + " " + nom + " qui a " + calculAge() + " ans";
    }
    
    

}
