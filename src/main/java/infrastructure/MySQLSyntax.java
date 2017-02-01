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
        constraintTemplates.put("AttributeListRule", new StringTemplate("new.$firstAttribute$ $operator$ $listValue$;"));
        constraintTemplates.put("AttributeRangeRule", new StringTemplate("new.$firstAttribute$ $operator$ $minimum$ and $maximum$;"));
    }

    @Override
    public StringTemplate getTriggerTemplate() {
        return new StringTemplate(//"DELIMITER ** "+
                //"DROP TRIGGER IF EXISTS $trigger_name$; ** " +
                "CREATE TRIGGER $trigger_name$ \n" +
                "before $trigger_event$ ON $table_name$ \n" +
                "FOR EACH ROW \n" +
                "BEGIN \n" +
                "   DECLARE l_passed BOOLEAN DEFAULT FALSE; \n" +
                "   set l_passed = $constraint$ \n" +
                "   if l_passed = false then \n" +
                "        signal sqlstate '45000' set message_text = '$error_message$'; \n" +
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
        } else if(dataTypeIN.toLowerCase().contains("date")){
            return SyntaxManager.DataType.date;
        }
        return null;
    }

    @Override
    public String getJDBCUrl() {
        return "jdbc:mysql://";
    }

    @Override
    public String getDriverPath() {
        return "com.mysql.jdbc.Driver";
    }

    @Override
    public StringTemplate getToDateTemplate() {
        return new StringTemplate("STR_TO_DATE('$date$', '%d-%m-%Y')");
    }

    @Override
    public String getSchemes() {
        return "show schemas";
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
