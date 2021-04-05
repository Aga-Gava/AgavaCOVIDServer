/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.util.prefs.Preferences;

/**
 *
 * @author Juan
 */
public class AgavaPreferences {
    
    static Preferences preferences = 
        Preferences.userNodeForPackage(AgavaPreferences.class);

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
}
