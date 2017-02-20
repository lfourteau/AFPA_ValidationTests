package com.example.afpa.garageapp.model;

/**
 * Created by afpa on 09/02/17.
 */

public class Garage {
    int id;
    String nom;
    Double latitude;
    Double longitude;
    String concessionnaire;

    public Garage() {
    }

    public Garage(int id, String nom, Double latitude, Double longitude, String concessionnaire) {
        this.id = id;
        this.nom = nom;
        this.latitude = latitude;
        this.longitude = longitude;
        this.concessionnaire = concessionnaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getConcessionnaire() {
        return concessionnaire;
    }

    public void setConcessionnaire(String concessionnaire) {
        this.concessionnaire = concessionnaire;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}

