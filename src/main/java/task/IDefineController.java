package task;

import domain.targetDatabase.Attribute;
import domain.targetDatabase.Scheme;
import domain.targetDatabase.Table;

import java.util.List;

/**
 * Created by lucas on 25-1-2017.
 */
public interface IDefineController {
    List<Scheme> getAllSchemes(String host);

    List<Table> getTablesFromScheme(String host, String schemeName);

    List<Attribute> getAttributesFromTable(String host, String schemeName, String tableName);

}
