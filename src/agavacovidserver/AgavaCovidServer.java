/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agavacovidserver;
import java.io.IOException;
import java.sql.*;
import persistence.ConnectionPool;
import server.AgavaServer;
/**
 *
 * @author agapo
 */
public class AgavaCovidServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        //AgavaPreferences.setCredentials("root", "agava");
        
        AgavaServer serv = new AgavaServer(); //Se crea el servidor

        System.out.println("Iniciando servidor\n");
        serv.startServer(); //Se inicia el servidor
        
        // TODO code application logic here
        try {
            
            ConnectionPool conn = ConnectionPool.getInstance();
        
            Statement stmt = null;
            ResultSet rs = null;
            //SQL query command
            String SQL = serv.query;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
            SQL = "SELECT * FROM ids_infectados";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(rs.getString("id") 
            + " " + rs.getString("clave_gen") 
            + " : " + rs.getString("fecha_gen") 
            + " : " + rs.getString("fecha_rec"));
                }
            
            } catch (SQLException e) {
                System.out.println("SQL Exception: "+ e.toString());
            } catch (Exception ex) {
                System.out.println("Exception: "+ ex.toString());
            }
        
        
    
    }
    
    }
