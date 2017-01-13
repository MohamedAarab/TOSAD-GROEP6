package businessrules.test;

/**
 * Created by lucas on 13-1-2017.
 */
public class Definition  {
    private String name;
    private Object value;

    public Definition(String name, Object value){
        this.name = name;
        if(DefinitionHandler.canSetValue(name, value))
            this.value = value;
    }

    public Definition(String name) {
        this(name, null);
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(String value) {
        if(DefinitionHandler.canSetValue(name, value))
            this.value = value;
    }
}
