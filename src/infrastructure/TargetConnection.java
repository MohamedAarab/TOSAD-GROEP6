package infrastructure;

import javax.naming.InitialContext;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Gebruiker on 18-1-2017.
 */
public class TargetConnection {
    private String targetUsername;
    private String targetPassword;
    private String targetUrl;
    private Connection connection = null;

    public TargetConnection() {
        try {
            InitialContext ic = new InitialContext();
            connection = DriverManager.getConnection(this.targetUrl , this.targetUsername, this.targetPassword);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void setTargetUsername(String targetUsername) {
        this.targetUsername = targetUsername;
    }

    public void setTargetPassword(String targetPassword) {
        this.targetPassword = targetPassword;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public Connection getConnection() {
        return connection;
    }
}
