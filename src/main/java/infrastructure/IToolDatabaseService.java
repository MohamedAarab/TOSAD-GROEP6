package infrastructure;

import domain.businessRule.BusinessRule;
import domain.businessRule.Definition;
import domain.businessRuleType.BusinessRuleType;
import domain.businessRuleType.Operator;

import java.util.List;

/**
 * Created by lucas on 27-1-2017.
 */
public interface IToolDatabaseService {
    BusinessRule getBusinessRuleByName(String businessruleName);
    String getTableNameFromBusinessRule(String businessRuleName);
    List<String> getAllDatabaseTypes();
    public List<Definition> getDefinitions(String businessruleName);
    Operator getOperatorByName(String operatorName);
    BusinessRuleType getBusinessRuleTypeByCode(String code);
}
