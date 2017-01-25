package domain.businessRuleType;

import org.antlr.stringtemplate.StringTemplate;

/**
 * Created by lucas on 19-1-2017.
 */
public class RangeRule extends BusinessRuleType {

    public RangeRule(String code, String name, String description) {
        super(code, name, description);
        OracleTemplate template = new OracleTemplate(new StringTemplate("$firstAttribute$ $minimum$ $operator$ $maximum$"));
        template.getTemplate().setAttribute("firstAttribute", "");
        template.getTemplate().setAttribute("operator", "");
        template.getTemplate().setAttribute("minimum", "");
        template.getTemplate().setAttribute("maximum", "");
        super.addTemplate(template);
    }

}
