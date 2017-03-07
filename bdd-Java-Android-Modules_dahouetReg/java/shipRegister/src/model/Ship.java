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
public class Ship {
    
    int voi_id;    
    int voi_num_voile;
    String voi_nom;
    ShipClass shipClass;
    Owner owner;

    public Ship(int voi_num_voile, String voi_nom, ShipClass shipClass, Owner owner) {       
        this.voi_num_voile = voi_num_voile;
        this.voi_nom = voi_nom;
        this.shipClass = shipClass;
        this.owner = owner;
    }

    public int getVoi_id() {
        return voi_id;
    }

    public void setVoi_id(int voi_id) {
        this.voi_id = voi_id;
    }

    public int getVoi_num_voile() {
        return voi_num_voile;
    }

    public void setVoi_num_voile(int voi_num_voile) {
        this.voi_num_voile = voi_num_voile;
    }

    public String getVoi_nom() {
        return voi_nom;
    }

    public void setVoi_nom(String voi_nom) {
        this.voi_nom = voi_nom;
    }

    public ShipClass getShipClass() {
        return shipClass;
    }

    public void setShipClass(ShipClass shipClass) {
        this.shipClass = shipClass;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
    
    
    
    
    
}
