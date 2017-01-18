package infrastructure;

import java.sql.Connection;

/**
 * Created by Gebruiker on 18-1-2017.
 */
public class BusinessRuleDAO extends BaseDAO{
    public BusinessRuleDAO(Connection connection) {
        super.setConnection(connection);
    }
}
