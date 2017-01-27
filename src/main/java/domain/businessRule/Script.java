package domain.businessRule;

import infrastructure.SyntaxManager;

import java.util.List;

/**
 * Created by Unknown on 01/18/2017.
 */
public class Script {
    protected String name;
    protected String triggerCode;
    protected List<String> triggerEvent;
    protected BusinessRule businessRule;

    public Script(String name, List<String> triggerEvent, BusinessRule businessRule) {
        this.name = name;
        this.triggerEvent = triggerEvent;
        this.businessRule = businessRule;
    }

    public String generate(String tableName, String scriptType)
    {
        StringTemplate triggerTemplate = SyntaxManager.getInstance().getTriggerTemplate(scriptType);
        StringTemplate constraintTemplate = SyntaxManager.getInstance().getConstraintTemplate(scriptType, businessRule.getBusinessRuleType().getName().replace(" ", ""));
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
            constraintTemplate.setAttribute(definition.getName(), definition.getValue().toString());
        }
        constraintTemplate.setAttribute("operator", businessRule.getOperator().getOperator());
        constraintTemplate.setAttribute("firstAttribute", businessRule.getFirstAttribute().getName());
        triggerTemplate.setAttribute("trigger_code", "l_passed := " + constraintTemplate.toString());
        this.triggerCode = triggerTemplate.toString();
        return triggerTemplate.toString();
    }
}
