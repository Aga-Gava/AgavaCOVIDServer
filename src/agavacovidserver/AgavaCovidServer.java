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
    }
}
