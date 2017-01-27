package infrastructure;

import domain.businessRule.BusinessRule;

import java.util.List;

/**
 * Created by lucas on 27-1-2017.
 */
public interface IToolDatabaseService {
    BusinessRule getBusinessRuleByName(String businessruleName);
    String getTableNameFromBusinessRule(String businessRuleName);
    List<String> getAllDatabaseTypes();
}
