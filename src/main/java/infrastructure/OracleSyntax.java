package infrastructure;

import org.antlr.stringtemplate.StringTemplate;

/**
 * Created by lucas on 25-1-2017.
 */
public class OracleSyntax implements IDatabaseSyntax {
    @Override
    public StringTemplate getTriggerTemplate() {
        return  new StringTemplate("CREATE OR REPLACE TRIGGER $trigger_name$\n"+
                "before $trigger_event$\n"+
                "   ON $table_name$\n"+
                "    FOR EACH ROW\n"+
                "DECLARE\n"+
                "   l_passed boolean := false;\n"+
                "BEGIN\n"+
                "   $trigger_code$\n"+
                "   if l_passed = false then\n   "+
                "      raise_application_error(-20000, \"$error_message$\");\n"+
                "   end if;\n"+
                "EXCEPTION\n"+
                "   WHEN others\n"+
                "   -- exception handling\n"+
                "END;");
    }

    @Override
    public String getTableSelect() {
        return null;
    }

    @Override
    public String getSchemeSelect() {
        return null;
    }

    @Override
    public String getAttributeSelect() {
        return null;
    }
}
