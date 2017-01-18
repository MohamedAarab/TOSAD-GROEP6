package businessrules;

import domain.businessRuleType.Operator;

/**
 * Created by lucas on 13-1-2017.
 */
public abstract class BusinessRule {
    private Operator operator;
    private String errorMessage;
    private String name;

    public BusinessRule(){}

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void setValue(Object value);

    public void save(){
        // TODO: 13-1-2017
    }
}
