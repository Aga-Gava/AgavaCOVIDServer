/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 
package agavacovidserver;

import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.ConnectionPool;


public class MulticastServerThread extends QuoteServerThread {

    private long FIVE_SECONDS = 5000;

    public MulticastServerThread() throws IOException {
        super("MulticastServerThread");
    }

    public void run() {
        while (moreQuotes) {
            
            try {
                byte[] buf = new byte[256];

                    // construct quote
                String dString = null;
                ConnectionPool conn = ConnectionPool.getInstance();
                List<String> enviar = new ArrayList<>();
                Statement stmt;
                ResultSet rs;
                String SQL = "SELECT * FROM ids_infectados";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(SQL);
                if (in == null){      
                while (rs.next()) {
                    enviar.add(rs.getString("clave_gen") 
                    + "," + rs.getString("fecha_gen")); 
//                  
                }
                
                for(String s: enviar){
                    dString=s;
                     buf = dString.getBytes();

                // send it
                    InetAddress group = InetAddress.getByName("224.0.0.251");
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4446);

                    socket.send(packet);
                }
                
                }else{
                    dString = getNextQuote();
                    buf = dString.getBytes();

                // send it
                    InetAddress group = InetAddress.getByName("224.0.0.251");
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4446);

                    socket.send(packet);
                }
                System.out.println("Enviando...");
            // sleep for a while
        try {
            sleep((long)(FIVE_SECONDS));
        } catch (InterruptedException e) { }
            } catch (IOException e) {
                e.printStackTrace();
        moreQuotes = false;
            } catch (SQLException ex) {
                Logger.getLogger(MulticastServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    socket.close();
    }
}