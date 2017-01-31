package domain.targetDatabase;

import infrastructure.DAOServiceImp;
import infrastructure.IDAOService;
import infrastructure.SyntaxManager;

import javax.validation.constraints.Null;
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
    public List<Scheme> getAllSchemes(String host, String databaseName) throws NullPointerException {
        return getTargetDatabaseByHost(host, databaseName).getSchemes();
    }

    @Override
    public List<Table> getTablesFromScheme(String host, String dataName, String schemeName) throws NullPointerException{
        return getTargetDatabaseByHost(host, dataName).getSchemeByName(schemeName).getTables();
    }

    @Override
    public List<Attribute> getAttributesFromTable(String host, String databaseName, String schemeName, String tableName) throws NullPointerException {
        return getTargetDatabaseByHost(host, databaseName).getSchemeByName(schemeName).getTableByName(tableName).getAttributes();
    }

    @Override
    public void connectToDatabase(String type, String host, int port, String databaseName, String username, String password) throws NullPointerException {
        removeTargetDatabase(host, databaseName);
        addTargetDatabase(daoService.connectToDatabase(type, host, port, databaseName, username,password));
    }

    @Override
    public void addTargetDatabase(TargetDatabase targetDatabase) {
        targetDatabases.add(targetDatabase);
    }

    @Override
    public TargetDatabase getTargetDatabaseByHost(String host, String databaseName) throws NullPointerException {
        for(TargetDatabase targetDatabase : getTargetDatabases()){
            if(targetDatabase.getHost().equals(host) && targetDatabase.getName().equals(databaseName))
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

    @Override
    public String executeScript(String host, String databaseName, String triggerCode) {
        TargetDatabase targetDatabase = getTargetDatabaseByHost(host, databaseName);
        return daoService.executeScript(targetDatabase.getType(),targetDatabase.getHost(), targetDatabase.getPort(), targetDatabase.getName(), targetDatabase.getUsername(),targetDatabase.getPassword(), triggerCode);
    }

    @Override
    public SyntaxManager.DataType getDatabaseTypeFromAttribute(String host, String databaseName, String schemeName, String tableName, String attributeName) {
        return getTargetDatabaseByHost(host,databaseName).getSchemeByName(schemeName).getTableByName(tableName).getAttributeByName(attributeName).getType();
    }

    @Override
    public int removeTargetDatabase(String host, String databaseName) {
        try{
            targetDatabases.remove(getTargetDatabaseByHost(host, databaseName));
        } catch (NullPointerException e){};
        return targetDatabases.size();
    }
}
