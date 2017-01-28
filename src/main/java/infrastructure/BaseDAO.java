package infrastructure;

import javax.naming.InitialContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

/**
 * Created by Gebruiker on 18-1-2017.
 */
public class BaseDAO {
    private String databaseType;
    private String targetUsername;
    private String targetPassword;
    private String targetUrl;
    private String databaseName;

    public BaseDAO(String databaseType, String targetUsername, String targetPassword, String targetUrl, String databaseName) {
        this.databaseType = databaseType;
        this.targetUsername = targetUsername;
        this.targetPassword = targetPassword;
        this.targetUrl = targetUrl;
        this.databaseName = databaseName;
    }

    protected final Connection getConnection() {
        Connection result = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            result = DriverManager.getConnection("jdbc:"+databaseType.toLowerCase()+ "://"+this.targetUrl +":3306/" + this.databaseName , this.targetUsername, this.targetPassword);
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
}
