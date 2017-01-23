package domain.businessRuleType;

import domain.businessRule.Definition;
import org.antlr.stringtemplate.StringTemplate;

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
    public void validate(List<Definition> definitions) {

    }
}
