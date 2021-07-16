package agavacovidserver;

/**
 *
 * @author Juan Velazquez Garcia
 * @author Maria Ruiz Molina
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

}
