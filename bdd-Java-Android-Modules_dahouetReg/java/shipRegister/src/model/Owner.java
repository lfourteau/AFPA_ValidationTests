/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author lucas
 */
public class Owner extends Personne {

    int pro_id;
    int pro_num_club;
    int pers_id;

    public Owner(int pro_id, String per_nom, String per_prenom) {
        super(per_nom, per_prenom);
        this.pro_id = pro_id;
    }

    public Owner(String per_nom, String per_prenom) {
        super(per_nom, per_prenom);
    }

    public Owner(int pro_num_club, String per_nom, String per_prenom, Date per_date) {
        super(per_nom, per_prenom, per_date);
        this.pro_id = pro_id;
        this.pro_num_club = pro_num_club;        
        this.per_date = per_date;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public int getPro_num_club() {
        return pro_num_club;
    }

    public void setPro_num_club(int pro_num_club) {
        this.pro_num_club = pro_num_club;
    }

    public int getPers_id() {
        return pers_id;
    }

    public void setPers_id(int pers_id) {
        this.pers_id = pers_id;
    }

    public String getPer_nom() {
        return per_nom;
    }

    public void setPer_nom(String per_nom) {
        this.per_nom = per_nom;
    }

    public String getPer_prenom() {
        return per_prenom;
    }

    public void setPer_prenom(String per_prenom) {
        this.per_prenom = per_prenom;
    }

    public Date getPer_date() {
        return per_date;
    }
    
    

    @Override
    public String toString() {
        return super.toString();
    }

}
