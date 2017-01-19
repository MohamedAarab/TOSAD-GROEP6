package domain.businessRuleType;

import org.antlr.stringtemplate.StringTemplate;

/**
 * Created by lucas on 19-1-2017.
 */
public class OracleTemplate implements ITemplate {
    private StringTemplate stringTemplate;

    public OracleTemplate(StringTemplate template) {
        this.stringTemplate = template;
    }

    @Override
    public StringTemplate getTemplate() {
        return stringTemplate;
    }
}
