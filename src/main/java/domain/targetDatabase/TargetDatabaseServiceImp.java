package domain.targetDatabase;

import infrastructure.DAOServiceImp;
import infrastructure.IDAOService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 25-1-2017.
 */
public class TargetDatabaseServiceImp implements ITargetDatabaseService {
    private List<TargetDatabase> targetDatabases;
    private IDAOService daoService;

    public TargetDatabaseServiceImp(){
        targetDatabases = new ArrayList<TargetDatabase>();
        daoService = new DAOServiceImp();
    }

    @Override
    public List<Scheme> getAllSchemes(String host) {
        return null;
    }

    @Override
    public List<Table> getTablesFromScheme(String host, String schemeName) {
        return null;
    }

    @Override
    public List<Attribute> getAttributesFromTable(String host, String schemeName, String tableName) {
        return null;
    }

    @Override
    public void connectToDatabase(String host, String username, String password) {
        addTargetDatabase(daoService.connectToDatabase(host,username,password));
    }

    @Override
    public void addTargetDatabase(TargetDatabase targetDatabase) {
        targetDatabases.add(targetDatabase);
    }

    @Override
    public TargetDatabase getTargetDatabaseByHost(String host) {
        return null;
    }

    @Override
    public List<TargetDatabase> getTargetDatabases() {
        return null;
    }
}
