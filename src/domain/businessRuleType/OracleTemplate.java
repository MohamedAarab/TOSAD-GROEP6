package domain.businessRuleType;

import domain.businessRule.Definition;
import domain.businessRuleType.AttributeCompareRule.AttributeCompareRule;
import org.antlr.stringtemplate.StringTemplate;

import java.util.List;
import java.util.Map;

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
