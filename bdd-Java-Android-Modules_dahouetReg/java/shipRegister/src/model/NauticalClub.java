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
public class NauticalClub {
    int club_id;
    String club_nom;

    public NauticalClub(int club_id, String club_nom) {
        this.club_id = club_id;
        this.club_nom = club_nom;
    }

    public int getClub_id() {
        return club_id;
    }

    public String getClub_nom() {
        return club_nom;
    }

    @Override
    public String toString() {
        return club_nom;
    }
    
    
    
}
