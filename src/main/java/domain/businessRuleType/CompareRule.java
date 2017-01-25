package domain.businessRuleType;

import org.antlr.stringtemplate.StringTemplate;

/**
 * Created by lucas on 25-1-2017.
 */
public class CompareRule extends BusinessRuleType {

    public CompareRule(){
        this("testcode", "Compare Rule", "");
    }

    public CompareRule(String code, String name, String description) {
        super(code, name, description);
        OracleTemplate template = new OracleTemplate(new StringTemplate("$firstAttribute$ $operator$ $comparevalue$;"));
        template.getTemplate().setAttribute("firstAttribute", "");
        template.getTemplate().setAttribute("operator", "");
        template.getTemplate().setAttribute("comparevalue", "");
        super.addTemplate(template);
    }
}
