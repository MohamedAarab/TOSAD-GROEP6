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
    private static TargetDatabaseServiceImp instance;
    private IDAOService daoService;

    public TargetDatabaseServiceImp(){
        targetDatabases = new ArrayList<TargetDatabase>();
        daoService = new DAOServiceImp();
    }

    @Override
    public List<Scheme> getAllSchemes(String host) {
        return getTargetDatabaseByHost(host).getSchemes();
    }

    @Override
    public List<Table> getTablesFromScheme(String host, String schemeName) {
        return getTargetDatabaseByHost(host).getSchemeByName(schemeName).getTables();
    }

    @Override
    public List<Attribute> getAttributesFromTable(String host, String schemeName, String tableName) {
        return getTargetDatabaseByHost(host).getSchemeByName(schemeName).getTableByName(tableName).getAttributes();
    }

    @Override
    public void connectToDatabase(String type, String host, String username, String password) {
        addTargetDatabase(daoService.connectToDatabase(type, host,username,password));
    }

    @Override
    public void addTargetDatabase(TargetDatabase targetDatabase) {
        targetDatabases.add(targetDatabase);
    }

    @Override
    public TargetDatabase getTargetDatabaseByHost(String host) {
        for(TargetDatabase targetDatabase : getTargetDatabases()){
            if(targetDatabase.getHost().equals(host))
                return targetDatabase;
        }
        return null;
    }

    public static TargetDatabaseServiceImp getInstance(){
        if(instance == null)
            instance = new TargetDatabaseServiceImp();
        return instance;
    }

    @Override
    public List<TargetDatabase> getTargetDatabases() {
        return targetDatabases;
    }
}
