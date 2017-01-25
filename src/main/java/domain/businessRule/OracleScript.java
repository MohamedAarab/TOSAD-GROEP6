package domain.businessRule;

import domain.businessRuleType.ITemplate;
import domain.businessRuleType.OracleTemplate;
import org.antlr.stringtemplate.StringTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by lucas on 19-1-2017.
 */
public class OracleScript extends Script {
    private StringTemplate triggerTemplate = new StringTemplate("CREATE OR REPLACE TRIGGER $trigger_name$\n"+
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

    public OracleScript(String name, List<String> triggerEvent, BusinessRule businessRule) {
        super(name, triggerEvent, businessRule);
    }

    @Override
    public String generate(String tableName) {
        StringTemplate constraintTemplate = null;
        for(ITemplate t : businessRule.getBusinessRuleType().getTemplates()){
            if(t instanceof OracleTemplate){
                constraintTemplate = t.getTemplate();
                break;
            }
        }
        triggerTemplate.setAttribute("trigger_name", name);
        String event = "";
        for(String s : triggerEvent){
            event += s + " or ";
        }
        event = event.substring(0, event.length() - 3);
        triggerTemplate.setAttribute("trigger_event", event);
        triggerTemplate.setAttribute("table_name", tableName);
        triggerTemplate.setAttribute("error_message", businessRule.getErrorMessage());
        for(Definition definition : businessRule.getDefinitions()){
            constraintTemplate.setAttribute(definition.getName(), definition.getValue());
        }
        constraintTemplate.setAttribute("operator", businessRule.getOperator().getOperator());
        constraintTemplate.setAttribute("firstAttribute", businessRule.getFirstAttribute().getName());
        triggerTemplate.setAttribute("trigger_code", "l_passed := " + constraintTemplate.toString());
        return triggerTemplate.toString();
    }
}