
package agavacovidserver;
import persistence.AgavaPreferences;
import server.AgavaServer;
import java.io.*;
/**
 *
 * @author Juan Velazquez Garcia
 * @author Maria Ruiz Molina
 */
public class AgavaCovidServer {

   
    public static void main(String[] args) throws IOException {
        
        AgavaPreferences.setCredentials("root", "agava");
        
            AgavaServer serv = new AgavaServer(); //Se crea el servidor
         
        new MulticastServerThread().start();
       
        System.out.println("Iniciando servidor\n");
        serv.startServer(); //Se inicia el servidor
        
        // TODO code application logic here
    }
}
