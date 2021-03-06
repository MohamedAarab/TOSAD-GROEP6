package domain.targetDatabase;

import java.util.List;

/**
 * Created by lucas on 25-1-2017.
 */
public interface ITargetDatabaseService {
    List<Scheme> getAllSchemes(String host, String databaseName);

    List<Table> getTablesFromScheme(String host, String databaseName, String schemeName);

    List<Attribute> getAttributesFromTable(String host, String databaseName, String schemeName, String tableName);

    void connectToDatabase(String type, String host, int port, String databaseName, String username, String password);

    void addTargetDatabase(TargetDatabase targetDatabase);

    TargetDatabase getTargetDatabaseByHost(String host, String databaseName);

    List<TargetDatabase> getTargetDatabases();

    String executeScript(String host, String databaseName, String triggerCode);

    domain.targetDatabase.DataType getDatabaseTypeFromAttribute(String host, String databaseName, String schemeName, String tableName, String attributeName);

    int removeTargetDatabase(String host, String databaseName);

    boolean checkConnection(String type, String host, int port, String databaseName, String username, String password);
}
