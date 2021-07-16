
package persistence;

import java.util.prefs.Preferences;

/**
 *
 * @author Juan Velazquez Garcia
 * @author Maria Ruiz Molina
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
