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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Owner;
import model.Ship;

/**
 *
 * @author lucas
 */
public class OwnerDAO {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static List<Owner> findAll() {

        Connection connection = ConnectDB.getConnection();
        Statement stm;

        ArrayList<Owner> owners = new ArrayList();

        try {
            stm = connection.createStatement();

            String sql = "select p.per_prenom, p.per_nom, prop.pro_id from personne p inner join proprietaire prop on p.per_id = prop.per_id";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int pro_id = rs.getInt("pro_id");
                String pro_nom = rs.getString("per_nom");
                String pro_prenom = rs.getString("per_prenom");

                Owner o = new Owner(pro_id, pro_nom, pro_prenom);

                owners.add(o);
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return owners;
    }

    public static void insert(Owner o) throws Exception {
        Connection connection = ConnectDB.getConnection();

        PreparedStatement stmCreatePersonne;
        PreparedStatement stmCreateStagiaire;

        try {
            connection.setAutoCommit(false);
            stmCreatePersonne = connection.prepareStatement("INSERT INTO personne (per_nom, per_prenom, per_date_naissance) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            stmCreatePersonne.setString(1, o.getPer_nom());
            stmCreatePersonne.setString(2, o.getPer_prenom());
            stmCreatePersonne.setDate(3, new java.sql.Date(o.getPer_date().getTime()));
            stmCreatePersonne.execute();

            //Permet d'obtenir l'id auto-incrémenté
            ResultSet rs = stmCreatePersonne.getGeneratedKeys();
            if (!rs.next()) {
                throw new Exception("humm cannot get generated personne id");
            }
            o.setPers_id(rs.getInt(1));

            stmCreateStagiaire = connection.prepareStatement("INSERT INTO proprietaire (per_id, clu_id) VALUES (?,?);");
            stmCreateStagiaire.setString(1, Integer.toString(o.getPers_id()));
            stmCreateStagiaire.setInt(2, o.getPro_num_club());
            stmCreateStagiaire.execute();
            connection.commit();
            stmCreatePersonne.close();
            stmCreateStagiaire.close();

        } catch (SQLException e) {
            //pb if here
            connection.rollback();
            throw new Exception("error while creating personne " + e.getMessage());
        }

    }
}
