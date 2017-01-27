package infrastructure;

import domain.businessRule.BusinessRule;

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
}