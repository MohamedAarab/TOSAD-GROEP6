package infrastructure;

import domain.targetDatabase.TargetDatabase;

/**
 * Created by lucas on 25-1-2017.
 */
public class DAOServiceImp implements IDAOService{
    @Override
    public TargetDatabase connectToDatabase(String host, String username, String password) {
        TargetDatabaseDAO targetDatabaseDAO = new TargetDatabaseDAO(host,password,username);
        return targetDatabaseDAO.createTargetDatabase();
    }

    @Override
    public void executeScript(String host, String triggerCode) {

    }
}
