package infrastructure;

import domain.businessRule.BusinessRule;

/**
 * Created by lucas on 27-1-2017.
 */
public interface IToolDatabaseService {
    BusinessRule getBusinessRuleByName(String businessruleName);
    String getTableNameFromBusinessRule(String businessRuleName);
}
