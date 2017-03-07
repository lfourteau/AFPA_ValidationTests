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
public class ShipSerie {
    int ser_id;
    String ser_nom;

    public ShipSerie(int ser_id, String ser_nom) {
        this.ser_id = ser_id;
        this.ser_nom = ser_nom;
    }

    public int getSer_id() {
        return ser_id;
    }

    public String getSer_nom() {
        return ser_nom;
    }

    @Override
    public String toString() {
        return ser_nom;
    }
    
    
    
}
