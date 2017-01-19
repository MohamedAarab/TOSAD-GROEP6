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
                                                                 "$trigger_event$\n"+
                                                                 "   ON $table_name$\n"+
                                                                 "    FOR EACH ROW\n"+
                                                                 "\n"+
                                                                 "DECLARE\n"+
                                                                 "   l_passed boolean := false;\n"+
                                                                 "BEGIN\n"+
                                                                 "   $trigger_code$\n"+
                                                                 "EXCEPTION\n"+
                                                                 "   WHEN others\n"+
                                                                 "   -- exception handling\n"+
                                                                 "END;");

    public OracleScript(String name, List<String> triggerEvent, BusinessRule businessRule) {
        super(name, triggerEvent, businessRule);
    }

    @Override
    public void generate() {
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
        triggerTemplate.setAttribute("table_name", "table_name");
        //triggerTemplate.setAttribute("trigger_code", constraintTemplate);
        for(Definition definition : businessRule.getDefinitions()){
            constraintTemplate.setAttribute(definition.getName(), definition.getValue());
        }
        constraintTemplate.setAttribute("operator", businessRule.getOperator().getName());
        constraintTemplate.setAttribute("firstAttribute", businessRule.getFirstAttribute().getName());
        triggerTemplate.setAttribute("trigger_code", "l_passed := " + constraintTemplate.toString());
        System.out.println(triggerTemplate);
    }
}