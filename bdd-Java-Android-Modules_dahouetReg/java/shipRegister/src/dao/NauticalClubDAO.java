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
import model.NauticalClub;
import model.Owner;

/**
 *
 * @author afpa
 */
public class NauticalClubDAO {
    public static List<NauticalClub> findAll() {

        Connection connection = ConnectDB.getConnection();
        Statement stm;  

        ArrayList<NauticalClub> clubs = new ArrayList();

        try {
            stm = connection.createStatement();

            String sql = "select * from club_nautique";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int clu_id = rs.getInt("clu_id");
                String clu_nom = rs.getString("clu_nom");
               

                NauticalClub nc = new NauticalClub(clu_id, clu_nom);

                clubs.add(nc);
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return clubs;
    }
}
