package domain.targetDatabase;

import java.util.List;

/**
 * Created by lucas on 25-1-2017.
 */
public interface ITargetDatabaseService {
    List<Scheme> getAllSchemes(String host);
    List<Table> getTablesFromScheme(String host, String schemeName);
    List<Attribute> getAttributesFromTable(String host, String schemeName, String tableName);
    void connectToDatabase(String type, String host, String username, String password);
    void addTargetDatabase(TargetDatabase targetDatabase);
    TargetDatabase getTargetDatabaseByHost(String host);
    List<TargetDatabase> getTargetDatabases();
}
