package domain.businessRuleType;

import org.antlr.stringtemplate.StringTemplate;

/**
 * Created by lucas on 25-1-2017.
 */
public class ListRule extends BusinessRuleType {
    public ListRule(String code, String name, String description) {
        super(code, name, description);
        OracleTemplate template = new OracleTemplate(new StringTemplate("$firstAttribute$ $operator$ $listValue$;"));
        template.getTemplate().setAttribute("firstAttribute", "");
        template.getTemplate().setAttribute("operator", "");
        template.getTemplate().setAttribute("listValue", "");
        super.addTemplate(template);
    }
}
