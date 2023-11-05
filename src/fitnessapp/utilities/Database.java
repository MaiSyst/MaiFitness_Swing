/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author orion90
 */
public class Database {

    private Connection connection = null;
    private static Database database = null;
    private static Database getDatabase(){
        database=new Database();
        return database;
    }
    private Database(){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+Constants.URL_DB);
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }

    }

    public static Connection getInstance() {
        if (database == null) {
            return getDatabase().connection;
        }
        return database.connection;
    }
}
