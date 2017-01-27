package infrastructure;


import domain.businessRule.StringTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lucas on 25-1-2017.
 */
public class MySQLSyntax implements IDatabaseSyntax {
    private Map<String, StringTemplate> constraintTemplates ;

    public MySQLSyntax(){
        constraintTemplates = new HashMap<String, StringTemplate>();
        constraintTemplates.put("CompareRule", new StringTemplate("$firstAttribute$ $operator$ $comparevalue$;"));
        constraintTemplates.put("ListRule", new StringTemplate("$firstAttribute$ $operator$ $listValue$;"));
        constraintTemplates.put("AttributeRangeRule", new StringTemplate("$firstAttribute$ $minimum$ $operator$ $maximum$"));
    }

    @Override
    public StringTemplate getTriggerTemplate() {
        return new StringTemplate("CREATE OR REPLACE TRIGGER $trigger_name$\n"+
                "before $trigger_event$\n"+
                "   ON $table_name$\n"+
                "    FOR EACH ROW\n"+
                "DECLARE\n"+
                "   l_passed boolean := false;\n"+
                "BEGIN\n"+
                "   $trigger_code$\n"+
                "   if l_passed = false then\n   "+
                "      raise_application_error(-20000, \"$error_message$\");\n"+
                "   end if;\n"+
                "EXCEPTION\n"+
                "   WHEN others\n"+
                "   -- exception handling\n"+
                "END;");
    }

    @Override
    public StringTemplate getTables() {
        return new StringTemplate("SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA='$schemeName$'");
    }

    @Override
    public StringTemplate getSchemes() {
        return null;
    }

    @Override
    public StringTemplate getAttributes() {
        return new StringTemplate("DESCRIBE $schemeName$.$tableName$");
    }

    @Override
    public StringTemplate getConstraintTemplate(String ruleType){
        return constraintTemplates.get(ruleType);
    }
}
