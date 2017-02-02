package domain.businessRule;

import java.util.List;

/**
 * Created by lucas on 25-1-2017.
 */
public interface IBusinessRuleService {
    BusinessRule createBusinessRuleForCode(String businessRuleName);

    String generateCode(BusinessRule businessRule, List<String> eventList, String scriptType, String scriptName, String tableName);

    String getTableNameFromBusinessRule(String businessRuleName);
}
