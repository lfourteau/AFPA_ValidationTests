/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Owner;
import model.Ship;
import model.ShipClass;
import model.ShipSerie;

/**
 *
 * @author afpa
 */
public class ShipSerieDAO {
    public static List<ShipSerie> findAll() {
        Connection connection = ConnectDB.getConnection();
        Statement stm;  
        ArrayList<ShipSerie> shipSeries = new ArrayList();
        try {
            stm = connection.createStatement();
            String sql = "select * from serie";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int ser_id = rs.getInt("ser_id");
                String ser_nom = rs.getString("ser_nom");
                ShipSerie ss = new ShipSerie(ser_id, ser_nom);
                shipSeries.add(ss);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return shipSeries;
    }    
}
