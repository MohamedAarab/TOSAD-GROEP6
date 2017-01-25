package infrastructure;

import org.antlr.stringtemplate.StringTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lucas on 25-1-2017.
 */
public class SyntaxManager {
    private Map<String, IDatabaseSyntax> syntaxMap = new HashMap<String, IDatabaseSyntax>();
    private static SyntaxManager syntaxManager;

    public SyntaxManager(){
        //getAllScriptTypes from apex;
        //voorbeeld van apex krijgen we : Oracle
        //instantiate Class met naam Oracle+Syntax
        //voeg nieuwe Class aan syntaxMap toe met als key Oracle

        //wanneer methodes worden aangeroepen, Altijd SyntaxManager.getInstance() en dan de gewenste methode.
    }

    public static SyntaxManager getInstance(){
        if(syntaxManager == null)
            syntaxManager = new SyntaxManager();
        return syntaxManager;
    }

    public StringTemplate getTriggerTemplate(String type) {
        return syntaxMap.get(type).getTriggerTemplate();
    }

    public String getTableSelect(String type) {
        return syntaxMap.get(type).getTableSelect();
    }

    public String getSchemeSelect(String type) {
        return syntaxMap.get(type).getSchemeSelect();
    }

    public String getAttributeSelect(String type) {
        return syntaxMap.get(type).getAttributeSelect();
    }
}
