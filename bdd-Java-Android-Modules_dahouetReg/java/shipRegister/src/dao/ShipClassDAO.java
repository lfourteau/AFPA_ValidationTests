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
import model.ShipClass;

/**
 *
 * @author lucas
 */
public class ShipClassDAO {
    public static List<ShipClass> findBySerieId(int ser_id) {

        Connection connection = ConnectDB.getConnection();
        Statement stm;  
        ArrayList<ShipClass> shipClasses = new ArrayList();
        try {
            stm = connection.createStatement();
            String sql = "select * from classe where ser_id =" + ser_id;
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int cla_id = rs.getInt("cla_id");
                String cla_nom = rs.getString("cla_nom");
                ShipClass sc = new ShipClass(cla_id, cla_nom);
                shipClasses.add(sc);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return shipClasses;
    }
}
