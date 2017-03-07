package com.example.afpa.dahouetregresults.model;

import java.util.Date;

/**
 * Created by afpa on 28/02/17.
 */

public class Regate {

    int reg_id;
    String reg_libelle;
    Date reg_date;
    double reg_distance;
    int cha_id;
    String cha_nom;

    public Regate() {
    }

    public Regate(int reg_id, String reg_libelle, Date reg_date, double reg_distance, int cha_id) {
        this.reg_id = reg_id;
        this.reg_libelle = reg_libelle;
        this.reg_date = reg_date;
        this.reg_distance = reg_distance;
        this.cha_id = cha_id;
    }
    public Regate(int reg_id, String reg_libelle, Date reg_date, double reg_distance, String cha_nom) {
        this.reg_id = reg_id;
        this.reg_libelle = reg_libelle;
        this.reg_date = reg_date;
        this.reg_distance = reg_distance;
        this.cha_nom = cha_nom;
    }


    public String getCha_nom() {
        return cha_nom;
    }

    public int getReg_id() {
        return reg_id;
    }

    public void setReg_id(int reg_id) {
        this.reg_id = reg_id;
    }

    public String getReg_libelle() {
        return reg_libelle;
    }

    public void setReg_libelle(String reg_libelle) {
        this.reg_libelle = reg_libelle;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public double getReg_distance() {
        return reg_distance;
    }

    public void setReg_distance(double reg_distance) {
        this.reg_distance = reg_distance;
    }

    public int getCha_id() {
        return cha_id;
    }

    public void setCha_id(int cha_id) {
        this.cha_id = cha_id;
    }
}
