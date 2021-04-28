/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import persistence.ConnectionPool;
import sockets.AgavaSocket;


/**
 *
 * @author Juan
 */
public class AgavaServer extends AgavaSocket{ //Se hereda de conexión para hacer uso de los sockets y demás

    public String query = null;
    
    public AgavaServer() throws IOException{
        super("servidor");
    } //Se usa el constructor para servidor de Conexion

    public void startServer(){//Método para iniciar el servidor
        try{
        
            System.out.println("Esperando..."); //Esperando conexión

            cs = ss.accept(); //Accept comienza el socket y espera una conexión desde un cliente

            System.out.println("Cliente en línea");

            //Se obtiene el flujo de salida del cliente para enviarle mensajes
            salidaCliente = new DataOutputStream(cs.getOutputStream());

            //Se le envía un mensaje al cliente usando su flujo de salida
            salidaCliente.writeUTF("Petición recibida y aceptada");

            //Se obtiene el flujo entrante desde el cliente
            DataInputStream entrada = new DataInputStream(cs.getInputStream());

            while((mensajeServidor = entrada.readUTF()) != null){ //Mientras haya mensajes desde el cliente
            
                //Se muestra por pantalla el mensaje recibido
                System.out.println(mensajeServidor);
                /*
                if(Long.parseLong(mensajeServidor)%2 != 0){
                    //System.out.println("Fin de la conexión");
                    
                }else */if(mensajeServidor.contains("INSERT")){
                    query = mensajeServidor;
                    try {
            //System.out.println("Estoy pero sin conn");
            ConnectionPool conn = ConnectionPool.getInstance();
            
            Statement stmt;
            ResultSet rs;
            //SQL query command
            String SQL = query;
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
        
                    System.out.println("Fin de la conexión");
                }
            }

            ss.close();//Se finaliza la conexión con el cliente
        }
        catch (Exception e){
        
            System.out.println(e.getMessage());
        }
    }
}
