package infrastructure.syntax;

import domain.businessRule.StringTemplate;
import domain.targetDatabase.DataType;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by lucas on 31-1-2017.
 */
public class OracleSyntax implements IDatabaseSyntax {
    private Map<String, StringTemplate> constraintTemplates;

    public OracleSyntax() {
        constraintTemplates = new HashMap<String, StringTemplate>();
        constraintTemplates.put("AttributeCompareRule", new StringTemplate(":new.$firstAttribute$ $operator$ $comparevalue$"));
        constraintTemplates.put("TupleCompareRule", new StringTemplate(":new.$firstAttribute$ $operator$ :new.$secondAttribute$"));
        constraintTemplates.put("Inter-EntityCompareRule", new StringTemplate(":new.$firstAttribute$ $operator$ (select $secondAttribute$ from $secondTable$ where $primaryKey$ = :new.$foreignKey$)"));
        constraintTemplates.put("AttributeListRule", new StringTemplate(":new.$firstAttribute$ $operator$ $listValue$"));
        constraintTemplates.put("AttributeRangeRule", new StringTemplate(":new.$firstAttribute$ $operator$ $minimum$ and $maximum$"));
    }

    @Override
    public StringTemplate getTriggerTemplate() {
        return new StringTemplate("CREATE OR REPLACE TRIGGER $trigger_name$ \n" +
                "before $trigger_event$ \n" +
                "   ON $table_name$ \n" +
                "    FOR EACH ROW \n" +
                "DECLARE \n" +
                "   l_passed varchar2(5); \n" +
                "BEGIN \n" +
                "   select (CASE WHEN ($constraint$) THEN 'true' ELSE 'false' END) as result into l_passed from dual; \n" +
                "   if l_passed = 'false' then \n " +
                "      raise_application_error(-20000, \'$error_message$\'); \n" +
                "   end if; \n" +
                "END;");
    }

    @Override
    public StringTemplate getTables() {
        return new StringTemplate("SELECT table_name FROM all_tables where owner = '$schemeName$'");
    }

    @Override
    public String getSchemes() {
        return "SELECT distinct owner FROM all_tables";
    }

    @Override
    public StringTemplate getAttributes() {
        return new StringTemplate("SELECT column_name, data_type FROM USER_TAB_COLUMNS where table_name = '$tableName$'");
    }

    @Override
    public StringTemplate getConstraintTemplate(String ruleType) {
        return constraintTemplates.get(ruleType);
    }

    @Override
    public domain.targetDatabase.DataType getDataType(String dataTypeIN) {
        if (dataTypeIN.toLowerCase().contains("number")) {
            return DataType.numeric;
        } else if (dataTypeIN.toLowerCase().contains("varchar")) {
            return DataType.text;
        } else if (dataTypeIN.toLowerCase().contains("date")) {
            return DataType.date;
        }
        return null;
    }

    @Override
    public String getJDBCUrl() {
        return "jdbc:oracle:thin:@//";
    }

    @Override
    public String getDriverPath() {
        return "oracle.jdbc.driver.OracleDriver";
    }

    @Override
    public StringTemplate getToDateTemplate() {
        return new StringTemplate("TO_DATE('$date$', 'DD-MM-YYYY')");
    }
}
