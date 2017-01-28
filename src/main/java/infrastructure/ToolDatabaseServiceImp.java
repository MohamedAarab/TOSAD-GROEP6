package infrastructure;

import domain.businessRule.BusinessRule;
import domain.businessRule.Definition;
import domain.businessRuleType.BusinessRuleType;
import domain.businessRuleType.Operator;

import java.util.List;

/**
 * Created by lucas on 27-1-2017.
 */
public class ToolDatabaseServiceImp implements IToolDatabaseService {
    ToolDatabaseConnection toolDatabaseConnection = new ToolDatabaseConnection();
    @Override
    public BusinessRule getBusinessRuleByName(String businessruleName) {
        return toolDatabaseConnection.getBusinessRule(businessruleName);
    }

    @Override
    public String getTableNameFromBusinessRule(String businessRuleName) {
        return toolDatabaseConnection.getTableNameFromBusinessRule(businessRuleName);
    }

    @Override
    public List<String> getAllDatabaseTypes() {
        return toolDatabaseConnection.getAllDatabaseTypes();
    }

    @Override
    public List<Definition> getDefinitions(String businessruleName) {
        return toolDatabaseConnection.getDefinitions(businessruleName);
    }

    @Override
    public Operator getOperatorByName(String operatorName) {
        return toolDatabaseConnection.getOperatorByName(operatorName);
    }

    @Override
    public BusinessRuleType getBusinessRuleTypeByCode(String code) {
        return toolDatabaseConnection.getBusinessRuleTypeByCode(code);
    }


}
