package infrastructure;

import domain.targetDatabase.TargetDatabase;

/**
 * Created by lucas on 25-1-2017.
 */
public class DAOServiceImp implements IDAOService{
    @Override
    public TargetDatabase connectToDatabase(String type, String host, String databaseName, String username, String password) {
        TargetDatabaseDAO targetDatabaseDAO = new TargetDatabaseDAO(type, username, password,host, databaseName);
        return targetDatabaseDAO.createTargetDatabase();
    }

    @Override
    public String executeScript(String type, String host, String databaseName, String username, String password, String triggerCode) {
        TargetDatabaseDAO targetDatabaseDAO = new TargetDatabaseDAO(type, username, password,host, databaseName);
        return targetDatabaseDAO.executeScript(triggerCode);
    }
}
