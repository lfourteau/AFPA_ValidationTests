/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author afpa
 */
public class Licencie extends Personne {

    int numLicence;
    double pointsFFV;
    int anneeLicence;
    double nbPoints = 0;

    public Licencie(int numLicence, double pointsFFV, int anneeLicence, String nom, String prenom, String email, int anneeNaissance) {
        super(nom, prenom, email, anneeNaissance);
        this.numLicence = numLicence;
        this.pointsFFV = pointsFFV;
        this.anneeLicence = anneeLicence;
    }   

    public double calculPoints(double points, Calendar annee) {
        
        if (anneeLicence == annee.get(Calendar.YEAR)) {
            nbPoints = pointsFFV + points;
            System.out.println("Après ajout des derniers resultats, " + getPrenom() + " " + getNom() + " possède : " + nbPoints);
        } else {
            System.out.print("La date ne corresponde à l'année de la licence pour " + getPrenom() + " " + getNom());
        }
        return nbPoints;
    }

    public int getAnneeLicence() {
        return anneeLicence;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
    
    
    
    
    
    @Override
    public String toString() {
        return super.toString() + " est un licencié. Il possède le numéro de licence n° " + numLicence + ". A l'instant présent, il est crédité de " + pointsFFV + " points au classement FFV";
    }

}
