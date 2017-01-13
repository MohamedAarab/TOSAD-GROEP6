package businessrules.test;

import domain.Operator;

import java.util.List;

/**
 * Created by lucas on 13-1-2017.
 */
public class TestBusinessRule {
    private Operator operator;
    private String errorMessage;
    private String name;
    private List<Definition> definitions;

    public TestBusinessRule(List<Definition> definitions){
        this.definitions = definitions;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinition(String name, String value){
        for(Definition definition : definitions){
            if(definition.getName().equals(name)){
                definition.setValue(value);
                break;
            }
        }
    }

    public void save(){
        // TODO: 13-1-2017
    }
}
