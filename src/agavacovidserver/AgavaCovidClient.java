/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agavacovidserver;

import java.io.IOException;

/**
 *
 * @author Juan
 */
public class AgavaCovidClient {
    public static void main(String[] args) throws IOException {
        AgavaClient cli = new AgavaClient(); //Se crea el cliente

        System.out.println("Iniciando cliente\n");
        cli.startClient(); //Se inicia el cliente
    
    }
}
