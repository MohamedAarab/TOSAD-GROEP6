package domain.targetDatabase;

import infrastructure.SyntaxManager;

import java.util.List;

/**
 * Created by lucas on 25-1-2017.
 */
public interface ITargetDatabaseService {
    List<Scheme> getAllSchemes(String host);
    List<Table> getTablesFromScheme(String host, String schemeName);
    List<Attribute> getAttributesFromTable(String host, String schemeName, String tableName);
    void connectToDatabase(String type, String host, int port, String databaseName, String username, String password);
    void addTargetDatabase(TargetDatabase targetDatabase);
    TargetDatabase getTargetDatabaseByHost(String host);
    List<TargetDatabase> getTargetDatabases();
    String executeScript(String host, String triggerCode);
    SyntaxManager.DataType getDatabaseTypeFromAttribute(String host, String schemeName, String tableName, String attributeName);
}
