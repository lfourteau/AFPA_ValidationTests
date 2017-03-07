/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author afpa
 */
public class Commissaire extends Personne {
    String commite;

    public Commissaire(String commite, String nom, String prenom, String email, int anneeNaissance) {
        super(nom, prenom, email, anneeNaissance);
        this.commite = commite;
    }

    
    
    

    @Override
    public String toString() {
        return  super.toString() + " est commissaire du comité de " + commite;
    }
    

    
    
}
