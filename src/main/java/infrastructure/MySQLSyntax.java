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
        constraintTemplates.put("CompareRule", new StringTemplate("new.$firstAttribute$ $operator$ $comparevalue$;"));
        constraintTemplates.put("ListRule", new StringTemplate("new.$firstAttribute$ $operator$ $listValue$;"));
        constraintTemplates.put("AttributeRangeRule", new StringTemplate("new.$firstAttribute$ $operator$ $minimum$ and $maximum$;"));
    }

    @Override
    public StringTemplate getTriggerTemplate() {
        /* //oracle
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
                "END;");*/
        return new StringTemplate(//"DELIMITER ** "+
                //"DROP TRIGGER IF EXISTS $trigger_name$; ** " +
                "CREATE TRIGGER $trigger_name$ " +
                "before $trigger_event$ ON $table_name$ " +
                "FOR EACH ROW " +
                "BEGIN " +
                "   DECLARE l_passed BOOLEAN DEFAULT FALSE; " +
                "   set l_passed = $constraint$ " +
                "   if l_passed = false then " +
                "        signal sqlstate '45000' set message_text = '$error_message$'; " +
                "   end if; " +
                "END;");
    }

    @Override
    public StringTemplate getTables() {
        return new StringTemplate("SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA='$schemeName$'");
    }

    @Override
    public SyntaxManager.DataType getDataType(String dataTypeIN){
        if(dataTypeIN.contains("int")){
            return SyntaxManager.DataType.numeric;
        } else if(dataTypeIN.contains("varchar")){
            return SyntaxManager.DataType.text;
        } else if(dataTypeIN.contains("datetime")){
            return SyntaxManager.DataType.date;
        }
        return null;
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
