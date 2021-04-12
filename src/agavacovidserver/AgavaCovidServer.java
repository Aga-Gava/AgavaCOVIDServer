/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agavacovidserver;
//import java.io.IOException;
import java.sql.*;
import persistence.AgavaPreferences;
import persistence.ConnectionPool;
import server.AgavaServer;
import java.io.*;
/**
 *
 * @author agapo
 */
public class AgavaCovidServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        AgavaPreferences.setCredentials("root", "sunrise");
        
        AgavaServer serv = new AgavaServer(); //Se crea el servidor
         
        new MulticastServerThread().start();
       
        System.out.println("Iniciando servidor\n");
        serv.startServer(); //Se inicia el servidor
        
        // TODO code application logic here
        try {
            //System.out.println("Estoy pero sin conn");
            ConnectionPool conn = ConnectionPool.getInstance();
            
            Statement stmt;
            ResultSet rs;
            //SQL query command
            String SQL = serv.query;
            //SQL = "SELECT * FROM ids_infectados WHERE clave_gen = 'empoleon'";
            stmt = conn.createStatement();
            //System.out.println(stmt + " el stmt");
            rs = stmt.executeQuery(SQL);
            //System.out.println(rs + " el rs");
            //System.out.println(SQL +" la sql");
            SQL = "SELECT * FROM ids_infectados";
            stmt = conn.createStatement();
            //System.out.println(stmt + " el stmt despues de pedirselect *");
            rs = stmt.executeQuery(SQL);
            //System.out.println("Tengo to pa imprimir leches");
            while (rs.next()) {
                System.out.println(rs.getString("id") 
            + " " + rs.getString("clave_gen") 
            + " : " + rs.getString("fecha_gen") 
            + " : " + rs.getString("fecha_rec"));
                }
            //System.out.println("Pa mi");
            } catch (SQLException e) {
                System.out.println("SQL Exception: "+ e.toString());
            } catch (Exception ex) {
                System.out.println("Exception: "+ ex.toString());
            }
        
        
    
    }
    
    }
