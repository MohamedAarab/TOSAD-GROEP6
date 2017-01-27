package infrastructure;


import domain.businessRule.StringTemplate;

/**
 * Created by lucas on 25-1-2017.
 */
public interface IDatabaseSyntax {
    StringTemplate getTriggerTemplate();
    StringTemplate getTables();
    StringTemplate getSchemes();
    StringTemplate getAttributes();
    StringTemplate getConstraintTemplate(String ruleType);
}
