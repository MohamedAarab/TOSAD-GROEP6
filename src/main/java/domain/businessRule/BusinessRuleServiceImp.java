package domain.businessRule;

import infrastructure.tooldatabase.IToolDatabaseService;
import infrastructure.tooldatabase.ToolDatabaseServiceImp;

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
    public String generateCode(BusinessRule businessRule, List<String> eventList, String scriptType, String scriptName, String tableName) {
        businessRule.getName();
        String code = new Script(scriptName, eventList, businessRule).generate(tableName, scriptType);
        return code;
    }

    @Override
    public String getTableNameFromBusinessRule(String businessRuleName) {
        return toolDatabaseService.getTableNameFromBusinessRule(businessRuleName);
    }
}
