/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agavacovidserver;

/**
 *
 * @author agapo
 */
import java.util.prefs.Preferences;

public class PreferencesAgava{
  static Preferences preferences = 
      Preferences.userNodeForPackage(PreferencesAgava.class);

  public static void setCredentials(String username, String password) {
    preferences.put("db_username", username);
    preferences.put("db_password", password);
  }

  public static String getUsername() {
    return preferences.get("db_username", null);
  }

  public static String getPassword() {
    return preferences.get("db_password", null);
  }
  //public static void main(String[] args) {
         
         
      

    
 
         
         
  //}   
  // your code here
}
