
package agavacovidserver;

import java.io.IOException;

/**
 *
 * @author Juan Velazquez Garcia
 * @author Maria Ruiz Molina
 */
public class AgavaCovidClient {
    public static void main(String[] args) throws IOException {
        AgavaClient cli = new AgavaClient(); //Se crea el cliente

        System.out.println("Iniciando cliente\n");
        cli.startClient(); //Se inicia el cliente
    
    }
}
