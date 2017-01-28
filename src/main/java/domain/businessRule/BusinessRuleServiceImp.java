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
    private IToolDatabaseService toolDatabaseService;

    public BusinessRuleServiceImp() {
        toolDatabaseService = new ToolDatabaseServiceImp();
    }

    @Override
    public BusinessRule createBusinessRuleForCode(String businessRuleName) {
        BusinessRule businessRule = toolDatabaseService.getBusinessRuleByName(businessRuleName);
        businessRule.setDefinitions(toolDatabaseService.getDefinitions(businessRuleName));
        //businessRule.setBusinessRuleType(toolDatabaseService.get);
        return businessRule;
    }

    @Override
    public String generateCode(BusinessRule businessRule, List<String> eventList, String scriptType, String tableName) {
        businessRule.getName();
        // TODO: 28-1-2017  generate name
        String code = new Script("scriptName", eventList,businessRule).generate(tableName, scriptType);
        return code;
    }

    @Override
    public String getTableNameFromBusinessRule(String businessRuleName) {
        return toolDatabaseService.getTableNameFromBusinessRule(businessRuleName);
    }
}
