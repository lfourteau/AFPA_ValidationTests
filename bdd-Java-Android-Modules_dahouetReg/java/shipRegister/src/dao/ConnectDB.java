/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author afpa
 */


public class ConnectDB {

    private static Connection conn = null;
    //TODO dbname user and pwd should come from a property file
    final static String URL = "jdbc:mysql://localhost/dahouetReg?noAccessToProcedureBodies=true";

    /**
     *
     * @return RunTimeException() if any pb
     */
    public static Connection getConnection() {
        if (conn == null) {

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                System.out.println("Driver O.K.");
                conn = DriverManager.getConnection(URL, "root", "lulastar37");
                System.out.println("Connexion effective !");         
            } catch (SQLException sqlE) {
                //TODO Logging
                System.out.println("Sql Erreur " + sqlE.getMessage());
                throw new RuntimeException();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }

        return conn;

    }

}

