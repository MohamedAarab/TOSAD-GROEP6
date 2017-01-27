package domain.businessRule;

import domain.businessRuleType.BusinessRuleType;
import domain.businessRuleType.Operator;
import infrastructure.IToolDatabaseService;
import infrastructure.ToolDatabaseServiceImp;

import javax.json.JsonObject;
import java.util.List;

/**
 * Created by lucas on 26-1-2017.
 */
public class BusinessRuleServiceImp implements IBusinessRuleService {
    IToolDatabaseService toolDatabaseService;

    public BusinessRuleServiceImp() {
        toolDatabaseService = new ToolDatabaseServiceImp();
    }

    @Override
    public BusinessRule createBusinessRuleForCode(String businessRuleName) {
        return toolDatabaseService.getBusinessRuleByName(businessRuleName);
    }

    @Override
    public BusinessRule createBusinessRuleFromJSON(JsonObject businessRule) {
        return null;
    }

    @Override
    public Operator createOperatorFromJSON(JsonObject operator) {
        return null;
    }

    @Override
    public BusinessRuleType createBusinessRuleTypeFromJSON(JsonObject businessRuleType) {
        return null;
    }

    @Override
    public String generateCode(BusinessRule businessRule, List<String> eventList, String scriptType, String tableName) {
        businessRule.getName();
        String code = new Script("scriptName", eventList,businessRule).generate(tableName, scriptType);
        return code;
    }

    @Override
    public String getTableNameFromBusinessRule(String businessRuleName) {
        return toolDatabaseService.getTableNameFromBusinessRule(businessRuleName);
    }

    @Override
    public void executeScript(String host, String triggerCode) {

    }
}
