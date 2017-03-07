/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Owner;
import model.Ship;
import model.ShipClass;

/**
 *
 * @author afpa
 */
public class ShipDAO {

    public static void insert(Ship s) throws Exception {
        Connection connection = ConnectDB.getConnection();
        PreparedStatement stmCreateFormation;
        try {
            connection.setAutoCommit(false);
            stmCreateFormation = connection.prepareStatement("INSERT INTO voilier (voi_num_voile, voi_nom, cla_id, pro_id) VALUES (?,?,?,?);");
            stmCreateFormation.setInt(1, s.getVoi_num_voile());
            stmCreateFormation.setString(2, s.getVoi_nom());
            stmCreateFormation.setInt(3, s.getShipClass().getCla_id());
            stmCreateFormation.setInt(4, s.getOwner().getPro_id());
            stmCreateFormation.execute();
            connection.commit();
            stmCreateFormation.close();
            stmCreateFormation.close();
        } catch (SQLException e) {
            //pb if here
            connection.rollback();
            throw new Exception("error while creating ship " + e.getMessage());
        }
    }
    
    public static List<Ship> findAll() {
        Connection connection = ConnectDB.getConnection();
        Statement stm;  
        ArrayList<Ship> ships = new ArrayList();
        try {
            stm = connection.createStatement();
            String sql = "select voi_num_voile, voi_nom, p.per_nom, p.per_prenom, c.cla_nom from voilier v inner join proprietaire prop on v.pro_id = prop.pro_id inner join personne p on p.per_id = prop.per_id inner join classe c on c.cla_id = v.cla_id";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int voi_num_voile = rs.getInt("voi_num_voile");
                String voi_nom = rs.getString("voi_nom");
                String pro_nom = rs.getString("per_nom");
                String pro_prenom = rs.getString("per_prenom");
                String cla_nom = rs.getString("cla_nom");
                ShipClass sc = new ShipClass(cla_nom);
                Owner o = new Owner(pro_nom, pro_prenom);
                Ship s = new Ship(voi_num_voile, voi_nom, sc, o);
                ships.add(s);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return ships;
    }    
}
