package com.example.afpa.dahouetregresults.model;

/**
 * Created by afpa on 02/03/17.
 */

public class Result {
    String reg_nom;
    int res_place;
    String nom_voilier;
    int num_voile;
    String skipper;

    public Result() {
    }

    public Result(String reg_nom, int res_place, String nom_voilier, int num_voile, String skipper) {
        this.reg_nom = reg_nom;
        this.res_place = res_place;
        this.nom_voilier = nom_voilier;
        this.num_voile = num_voile;
        this.skipper = skipper;
    }

    public String getReg_nom() {
        return reg_nom;
    }

    public int getRes_place() {
        return res_place;
    }

    public String getNom_voilier() {
        return nom_voilier;
    }

    public int getNum_voile() {
        return num_voile;
    }

    public String getSkipper() {
        return skipper;
    }
}
