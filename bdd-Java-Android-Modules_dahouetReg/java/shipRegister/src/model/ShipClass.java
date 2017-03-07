/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lucas
 */
public class ShipClass {
    int cla_id;
    float cla_coefficient;
    String cla_nom;
    int ser_id;

    public ShipClass(int cla_id, String cla_nom) {
        this.cla_id = cla_id;
        this.cla_nom = cla_nom;
    }
    
    public ShipClass(String cla_nom) {        
        this.cla_nom = cla_nom;
    }

    public int getCla_id() {
        return cla_id;
    }

    public void setCla_id(int cla_id) {
        this.cla_id = cla_id;
    }

    public float getCla_coefficient() {
        return cla_coefficient;
    }

    public void setCla_coefficient(float cla_coefficient) {
        this.cla_coefficient = cla_coefficient;
    }

    public String getCla_nom() {
        return cla_nom;
    }

    public void setCla_nom(String cla_nom) {
        this.cla_nom = cla_nom;
    }

    public int getSer_id() {
        return ser_id;
    }

    public void setSer_id(int ser_id) {
        this.ser_id = ser_id;
    }

    @Override
    public String toString() {
        return cla_nom;
    }
    
    
    
}
