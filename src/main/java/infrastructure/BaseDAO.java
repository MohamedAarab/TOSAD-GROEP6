package infrastructure;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Gebruiker on 18-1-2017.
 */
public class BaseDAO {

    private Connection connection;

    public BaseDAO() {
        this.connection = null;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }
}
