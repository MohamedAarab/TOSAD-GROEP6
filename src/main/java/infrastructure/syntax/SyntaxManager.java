package infrastructure.syntax;

import domain.businessRule.StringTemplate;
import domain.targetDatabase.DataType;
import infrastructure.tooldatabase.ToolDatabaseServiceImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lucas on 25-1-2017.
 */
public class SyntaxManager {
    private Map<String, IDatabaseSyntax> syntaxMap = new HashMap<String, IDatabaseSyntax>();
    //        databasetype, syntax
    private static SyntaxManager syntaxManager;

    public SyntaxManager() {
        try {
            List<String> databaseTypes = new ToolDatabaseServiceImp().getAllDatabaseTypes();
            for (String scriptType : databaseTypes) {
                syntaxMap.put(scriptType, (IDatabaseSyntax) Class.forName("infrastructure.syntax." + scriptType + "Syntax").newInstance());
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static SyntaxManager getInstance() {
        if (syntaxManager == null)
            syntaxManager = new SyntaxManager();
        return syntaxManager;
    }

    public StringTemplate getTriggerTemplate(String type) {
        return syntaxMap.get(type).getTriggerTemplate();
    }

    public StringTemplate getTablesTemplate(String type) {
        return syntaxMap.get(type).getTables();
    }

    public String getSchemesTemplate(String type) {
        return syntaxMap.get(type).getSchemes();
    }

    public StringTemplate getAttributesTemplate(String type) {
        return syntaxMap.get(type).getAttributes();
    }

    public StringTemplate getConstraintTemplate(String syntaxType, String ruleType) {
        System.out.println(ruleType);
        return syntaxMap.get(syntaxType).getConstraintTemplate(ruleType);
    }

    public DataType getDataType(String syntaxType, String datatype) {
        return syntaxMap.get(syntaxType).getDataType(datatype);
    }

    public String getJDBCUrl(String syntaxType) {
        return syntaxMap.get(syntaxType).getJDBCUrl();
    }

    public String getDriverPath(String syntaxType) {
        return syntaxMap.get(syntaxType).getDriverPath();
    }

    public StringTemplate getToDateTemplate(String syntaxType) {
        return syntaxMap.get(syntaxType).getToDateTemplate();
    }
}
