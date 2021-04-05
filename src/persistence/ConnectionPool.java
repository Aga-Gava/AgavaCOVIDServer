/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan
 */
public class ConnectionPool {
    private static ConnectionPool pool = null;
    
    private Connection conn;
    
    private ConnectionPool(String url, String user, String password) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn =  DriverManager.getConnection("jdbc:mariadb://localhost:3327/agavacovid", user, password);
        } catch (SQLException e) {
            System.out.println("SQL Exception: "+ e.toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /***** SINGLETON *****/
    /**
     * Devuelve la instancia única de la clase.
     * @return Instancia
     */
    public static ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool("jdbc:mariadb://localhost:3327/agavacovid", 
                    AgavaPreferences.getUsername(), AgavaPreferences.getPassword());  
        }
        
        return pool;
    }

    public Statement createStatement() throws SQLException {
        return conn.createStatement();
    }
    
    
}

