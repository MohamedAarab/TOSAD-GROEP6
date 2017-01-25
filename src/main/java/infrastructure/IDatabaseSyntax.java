package infrastructure;

import org.antlr.stringtemplate.StringTemplate;

/**
 * Created by lucas on 25-1-2017.
 */
public interface IDatabaseSyntax {
    StringTemplate getTriggerTemplate();
    String getTableSelect();
    String getSchemeSelect();
    String getAttributeSelect();
}
