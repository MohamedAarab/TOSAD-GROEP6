package infrastructure;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Gebruiker on 18-1-2017.
 */
public class BaseDAO {
    private String targetUsername;
    private String targetPassword;
    private String targetUrl;

    public BaseDAO(String targetUsername, String targetPassword, String targetUrl) {
        this.targetUsername = targetUsername;
        this.targetPassword = targetPassword;
        this.targetUrl = targetUrl;
    }

    protected final Connection getConnection() {
        Connection result = null;
        try {
            InitialContext ic = new InitialContext();
            result = DriverManager.getConnection(this.targetUrl , this.targetUsername, this.targetPassword);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }
}
