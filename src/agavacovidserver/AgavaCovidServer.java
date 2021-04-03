/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agavacovidserver;
import java.sql.*;
/**
 *
 * @author agapo
 */
public class AgavaCovidServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            Connection conn = null;
            conn =  DriverManager.getConnection("jdbc:mariadb://localhost:3327/agavacovid?" +
                "user=root&password=tuberculo");
            System.out.println("AAAA");
            
            Statement stmt = null;
            ResultSet rs = null;
            //SQL query command
            String SQL = "SELECT * FROM ids_infectados";
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
            } catch (ClassNotFoundException cE) {
                System.out.println("Class Not Found Exception: "+ cE.toString());
            } catch (Exception ex) {
                System.out.println("Exception: "+ ex.toString());
            }
        
        
    
    }
    
    }
