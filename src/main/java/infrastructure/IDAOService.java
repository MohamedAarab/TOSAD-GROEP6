package infrastructure;

import domain.targetDatabase.TargetDatabase;

/**
 * Created by lucas on 25-1-2017.
 */
public interface IDAOService {
    TargetDatabase connectToDatabase(String type, String host, int port, String databaseName, String username, String password);
    String executeScript(String type, String host, int port, String databaseName, String username, String password, String triggerCode);
}
