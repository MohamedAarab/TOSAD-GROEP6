package domain.businessRule;

import domain.businessRuleType.BusinessRuleType;
import domain.businessRuleType.Operator;

import javax.json.JsonObject;

/**
 * Created by lucas on 25-1-2017.
 */
public interface IBusinessRuleService {
    BusinessRule createBusinessRuleForCode(String businessRuleName);
    BusinessRule createBusinessRuleFromJSON(JsonObject businessRule);
    Operator createOperatorFromJSON(JsonObject operator);
    BusinessRuleType createBusinessRuleTypeFromJSON(JsonObject businessRuleType);
    String generateCode(String businessRule, String eventList, String scriptType);
    void executeScript(String host,String triggerCode);
}
