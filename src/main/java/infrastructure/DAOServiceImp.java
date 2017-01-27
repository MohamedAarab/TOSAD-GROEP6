package infrastructure;

import domain.targetDatabase.TargetDatabase;

/**
 * Created by lucas on 25-1-2017.
 */
public class DAOServiceImp implements IDAOService{
    @Override
    public TargetDatabase connectToDatabase(String type, String host, String username, String password) {
        TargetDatabaseDAO targetDatabaseDAO = new TargetDatabaseDAO(type, username, password,host);
        return targetDatabaseDAO.createTargetDatabase();
    }

    @Override
    public void executeScript(String host, String triggerCode) {

    }
}
