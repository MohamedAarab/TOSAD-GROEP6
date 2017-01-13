package businessrules.test;

import domain.Attribute;

/**
 * Created by lucas on 13-1-2017.
 */
public class DefinitionHandler {

    public static boolean canSetValue(String name, Object value){
        switch (name.toLowerCase()){
            case "comparevalue":
            case "maximum":
            case "minimum":
                return value instanceof Number;
            case "listvalue":
                return value instanceof String;
            case "attribute":
                return value instanceof Attribute;
        }
        return false;
    }
}

/*
Attribute selectedAttribute = businessRule.getAttribute();
List<String> tableNames = new ArrayList<>();
for(Sceme s : targetdatabase.getSchemes()){//get tables which have an attribute with same name as choosenAttribute
    for(Table t : s.getTables()){
        for(Attribute a : t.getAttributes()){
            if(a.equals(selectedAttribute)){
                tables.add(t.getname());
            }
        }
    }
}
 */

/*
l_passed := p_xx_row.new_status in ('geregistreeerd', 'goedgekeurd')
list.contains(iets);
 */
