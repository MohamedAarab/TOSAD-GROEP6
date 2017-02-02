package infrastructure.dao;

import infrastructure.syntax.SyntaxManager;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Gebruiker on 18-1-2017.
 */
public class BaseDAO {
    private int port;
    private String databaseType;
    private String targetUsername;
    private String targetPassword;
    private String targetUrl;
    private String databaseName;
    private SyntaxManager syntaxManager = SyntaxManager.getInstance();

    public BaseDAO(String databaseType, String targetUsername, String targetPassword, String targetUrl, String databaseName, int port) {
        this.databaseType = databaseType;
        this.targetUsername = targetUsername;
        this.targetPassword = targetPassword;
        this.targetUrl = targetUrl;
        this.databaseName = databaseName;
        this.port = port;
    }

    protected final Connection getConnection() {
        Connection result = null;
        try {
            Class.forName(syntaxManager.getDriverPath(databaseType));
            result = DriverManager.getConnection(syntaxManager.getJDBCUrl(databaseType) + this.targetUrl + ":" + this.port + "/" + this.databaseName, this.targetUsername, this.targetPassword);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

    protected String getDatabaseType() {
        return databaseType;
    }

    protected String getTargetUsername() {
        return targetUsername;
    }

    protected String getTargetPassword() {
        return targetPassword;
    }

    protected String getTargetUrl() {
        return targetUrl;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public int getPort() {
        return port;
    }
}
