package infrastructure;

import domain.businessRule.StringTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lucas on 31-1-2017.
 */
public class OracleSyntax implements IDatabaseSyntax {
    private Map<String, StringTemplate> constraintTemplates ;

    public OracleSyntax(){
        constraintTemplates = new HashMap<String, StringTemplate>();
        constraintTemplates.put("CompareRule", new StringTemplate("$firstAttribute$ $operator$ $comparevalue$;"));
        constraintTemplates.put("ListRule", new StringTemplate("$firstAttribute$ $operator$ $listValue$;"));
        constraintTemplates.put("AttributeRangeRule", new StringTemplate("$firstAttribute$ $operator$ $minimum$ and $maximum$;"));
    }

    @Override
    public StringTemplate getTriggerTemplate() {
        return new StringTemplate("CREATE OR REPLACE TRIGGER $trigger_name$\n"+
                "before $trigger_event$ \n"+
                "   ON $table_name$ \n"+
                "    FOR EACH ROW \n"+
                "DECLARE \n"+
                "   l_passed boolean := false; \n"+
                "BEGIN \n"+
                "   $trigger_code$ \n"+
                "   if l_passed = false then \n "+
                "      raise_application_error(-20000, \'$error_message$\'); \n"+
                "   end if; \n"+
                "EXCEPTION \n"+
                "   WHEN others \n"+
                "   -- exception handling \n"+
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
        return null;
    }

    @Override
    public SyntaxManager.DataType getDataType(String dataTypeIN) {
        if(dataTypeIN.toLowerCase().contains("number")){
            return SyntaxManager.DataType.numeric;
        } else if(dataTypeIN.toLowerCase().contains("varchar")){
            return SyntaxManager.DataType.text;
        } else if(dataTypeIN.toLowerCase().contains("date")){
            return SyntaxManager.DataType.date;
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
}
