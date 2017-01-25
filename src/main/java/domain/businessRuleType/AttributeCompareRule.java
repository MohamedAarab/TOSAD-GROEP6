package domain.businessRuleType;

import org.antlr.stringtemplate.StringTemplate;

/**
 * Created by lucas on 19-1-2017.
 */
public class AttributeCompareRule extends BusinessRuleType {
    public AttributeCompareRule(){
        this("testcode", "Attribute Compare Rule", "");
    }
    public AttributeCompareRule(String code, String name, String description) {
        super(code, name, description);
        OracleTemplate template = new OracleTemplate(new StringTemplate("$firstAttribute$ $operator$ $comparevalue$;"));
        template.getTemplate().setAttribute("firstAttribute", "");
        template.getTemplate().setAttribute("operator", "");
        template.getTemplate().setAttribute("comparevalue", "");
        super.addTemplate(template);
    }
}
