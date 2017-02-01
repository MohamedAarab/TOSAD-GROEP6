package domain.businessRule;

import infrastructure.SyntaxManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Unknown on 01/18/2017.
 */
public class Script {
    protected String name;
    protected String triggerCode;
    protected List<String> triggerEvent;
    protected BusinessRule businessRule;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

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
        if(constraintTemplate.getAllAttributes().contains("listValue")){
            String listValue = "(";
            for (Definition definition : businessRule.getDefinitions()) {
                listValue += "'";
                if(definition.getValue() instanceof Date) {
                    StringTemplate toDate = SyntaxManager.getInstance().getToDateTemplate(scriptType);
                    toDate.setAttribute("date", definition.getValue().toString());
                    listValue += toDate.toString();
                } else
                    listValue += definition.getValue();
                listValue += "', ";
            }
            listValue = listValue.substring(0, listValue.length() -2);
            listValue += ")";
            constraintTemplate.setAttribute("listValue", listValue);
        } else {
            for (Definition definition : businessRule.getDefinitions()) {
                if(definition.getValue() instanceof Date){
                    StringTemplate toDate = SyntaxManager.getInstance().getToDateTemplate(scriptType);
                    toDate.setAttribute("date", formatter.format(definition.getValue()));
                    constraintTemplate.setAttribute(definition.getName(), toDate.toString());
                } else

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
