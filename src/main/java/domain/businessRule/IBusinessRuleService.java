package domain.businessRule;

import domain.businessRuleType.BusinessRuleType;
import domain.businessRuleType.Operator;

import javax.json.JsonObject;
import java.util.List;

/**
 * Created by lucas on 25-1-2017.
 */
public interface IBusinessRuleService {
    BusinessRule createBusinessRuleForCode(String businessRuleName);
    BusinessRule createBusinessRuleFromJSON(JsonObject businessRule);
    Operator createOperatorFromJSON(JsonObject operator);
    BusinessRuleType createBusinessRuleTypeFromJSON(JsonObject businessRuleType);
    String generateCode(BusinessRule businessRule, List<String> eventList, String scriptType, String tableName);
    String getTableNameFromBusinessRule(String businessRuleName);
    void executeScript(String host,String triggerCode);
}
