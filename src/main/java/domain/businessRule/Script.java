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
        triggerTemplate.setAttribute("trigger_event", triggerEvent.get(0));
        triggerTemplate.setAttribute("table_name", tableName);
        triggerTemplate.setAttribute("error_message", businessRule.getErrorMessage());
        StringTemplate listTemplate = new StringTemplate("($listvalues$)");
        if(constraintTemplate.getAllAttributes().contains("listValue")){
            String listValue = "(";
            for (Definition definition : businessRule.getDefinitions()) {
                listValue += definition.getValue() + ", ";
            }
            listValue = listValue.substring(0, listValue.length() -2);
            listValue += ")";
            constraintTemplate.setAttribute("listValue", listValue);
        } else {
            for (Definition definition : businessRule.getDefinitions()) {
                constraintTemplate.setAttribute(definition.getName(), definition.getValue().toString());
            }
        }
        constraintTemplate.setAttribute("operator", businessRule.getOperator().getOperator());
        constraintTemplate.setAttribute("firstAttribute", businessRule.getFirstAttribute().getName());
        triggerTemplate.setAttribute("constraint", constraintTemplate.toString());
        this.triggerCode = triggerTemplate.toString();
        return triggerTemplate.toString();
    }
}
