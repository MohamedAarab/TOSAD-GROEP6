package infrastructure;

import domain.targetDatabase.TargetDatabase;

/**
 * Created by lucas on 25-1-2017.
 */
public interface IDAOService {
    TargetDatabase connectToDatabase(String type, String host, String username, String password);
    void executeScript(String host, String triggerCode);
}
