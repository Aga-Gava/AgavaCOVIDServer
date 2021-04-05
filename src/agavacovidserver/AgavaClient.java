/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agavacovidserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import sockets.AgavaSocket;

/**
 *
 * @author Juan
 */
public class AgavaClient extends AgavaSocket {
   public AgavaClient() throws IOException{super("cliente");} //Se usa el constructor para cliente de Conexion

    public void startClient(){ //Método para iniciar el cliente
        try{
        
            //Flujo de datos hacia el servidor
            salidaServidor = new DataOutputStream(cs.getOutputStream());
salidaServidor.writeUTF("INSERT INTO ids_infectados (clave_gen, fecha_gen, fecha_rec)"
        + " VALUES ('empoleon', '2021-03-04', '2015-02-02')");
            //Se enviarán dos mensajes
            for (int i = 0; i < 2; i++)
            {
                //Se escribe en el servidor usando su flujo de datos
                salidaServidor.writeUTF("Este es el mensaje número " + (i+1) + "\n");
            }
            
            

            cs.close();//Fin de la conexión

        }
        catch (Exception e){
        
            System.out.println(e.getMessage());
        }
    } 
}
