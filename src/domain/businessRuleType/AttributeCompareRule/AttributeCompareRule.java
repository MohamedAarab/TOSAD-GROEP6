package domain.businessRuleType.AttributeCompareRule;

import domain.businessRule.Definition;
import domain.businessRuleType.BusinessRuleType;
import domain.businessRuleType.ITemplate;
import domain.businessRuleType.OracleTemplate;
import org.antlr.stringtemplate.StringTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 19-1-2017.
 */
public class AttributeCompareRule extends BusinessRuleType {
    public AttributeCompareRule(String code, String name, String description) {
        super(code, name, description);
        OracleTemplate template = new OracleTemplate(new StringTemplate("$firstAttribute$ $operator$ $comparevalue$;"));
        template.getTemplate().setAttribute("firstAttribute", "");
        template.getTemplate().setAttribute("operator", "");
        template.getTemplate().setAttribute("comparevalue", "");
        super.addTemplate(template);
    }

    @Override
    public void generate(List<Definition> definitions) {
    }

    @Override
    public void validate(List<Definition> definitions) {

    }
}
