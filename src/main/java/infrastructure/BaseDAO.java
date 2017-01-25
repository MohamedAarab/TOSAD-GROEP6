package infrastructure;

import javax.naming.InitialContext;
import java.sql.Connection;
import java.sql.DriverManager;
import com.mysql.jdbc.Driver;

/**
 * Created by Gebruiker on 18-1-2017.
 */
public class BaseDAO {
    private String databaseType;
    private String targetUsername;
    private String targetPassword;
    private String targetUrl;

    public BaseDAO(String databaseType, String targetUsername, String targetPassword, String targetUrl) {
        this.databaseType = databaseType;
        this.targetUsername = targetUsername;
        this.targetPassword = targetPassword;
        this.targetUrl = targetUrl;
    }

    protected final Connection getConnection() {
        Connection result = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            InitialContext ic = new InitialContext();
            result = DriverManager.getConnection("jdbc:mysql://"+this.targetUrl +":3306" , this.targetUsername, this.targetPassword);
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
}
