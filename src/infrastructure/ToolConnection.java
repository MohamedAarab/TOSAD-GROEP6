package infrastructure;

import javax.naming.InitialContext;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Gebruiker on 18-1-2017.
 */
public class ToolConnection {
    private String toolUsername;
    private String toolPassword;
    private String toolUrl;
    private Connection connection = null;

    public ToolConnection() {
        this.toolUsername = toolUsername;
        this.toolPassword = toolPassword;
        this.toolUrl = toolUrl;
        try {
            InitialContext ic = new InitialContext();
            connection = DriverManager.getConnection("jdbc:default:connection:");

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void setToolUsername(String toolUsername) {
        this.toolUsername = toolUsername;
    }

    public void setToolPassword(String toolPassword) {
        this.toolPassword = toolPassword;
    }

    public void setToolUrl(String toolUrl) {
        this.toolUrl = toolUrl;
    }

    public Connection getConnection() {
        return connection;
    }

}
