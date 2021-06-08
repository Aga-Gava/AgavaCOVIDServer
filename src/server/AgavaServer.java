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
import java.util.Arrays;
import java.util.List;
import persistence.ConnectionPool;
import security.Encriptado;
import security.Encriptado2;
import sockets.AgavaSocket;


/**
 *
 * @author Juan
 */
public class AgavaServer extends AgavaSocket{ //Se hereda de conexión para hacer uso de los sockets y demás

    private String[] aceptados = {"123456789012", "273384273384","133713371337"};
    
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
                mensajeServidor = Encriptado.desencriptar(mensajeServidor, "cacnea");
                System.out.println(mensajeServidor);
                String[] arrSplit = mensajeServidor.split(",");
                boolean continua = false;
                if(Arrays.asList(aceptados).contains(arrSplit[0])){ //Comprobamos que el codigo este en los aceptados
                    continua = true;
                }
                
                if(continua){
                    ConnectionPool conn = ConnectionPool.getInstance();
                    Statement stmt;
                    ResultSet rs;
                    String fechaRec = arrSplit[1];
                    for(int i=2; i < arrSplit.length; i++){
                        String claveGen = arrSplit[i];
                        String fechaGen = arrSplit[i+1];
                        stmt = conn.createStatement();
                        String query = "INSERT INTO ids_infectados (clave_gen, fecha_gen, fecha_rec) "
                                + "VALUES ('"+ claveGen +"','"+ fechaGen +"','"+ fechaRec +"');";
                        rs = stmt.executeQuery(query);
                        i=i+1;
                    }
                }

                    try {
            //System.out.println("Estoy pero sin conn");
            ConnectionPool conn = ConnectionPool.getInstance();
            
            Statement stmt;
            ResultSet rs;
            //SQL query command
            //SQL = "SELECT * FROM ids_infectados WHERE clave_gen = 'empoleon'";
            //System.out.println(stmt + " el stmt");
            //System.out.println(rs + " el rs");
            //System.out.println(SQL +" la sql");
            String SQL = "SELECT * FROM ids_infectados";
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

            ss.close();//Se finaliza la conexión con el cliente
        }
        catch (Exception e){
        
            System.out.println(e.getMessage());
        }
    }
}
