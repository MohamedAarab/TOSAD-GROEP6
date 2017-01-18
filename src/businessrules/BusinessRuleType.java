package businessrules;

import domain.businessRuleType.Operator;

import java.util.List;

/**
 * Created by lucas on 13-1-2017.
 */
public class BusinessRuleType {
    private String code;
    private String name;
    private String description;
    private List<Operator> operators;

    public BusinessRuleType(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public void setOperators(List<Operator> operators){
        this.operators = operators;
    }

    public String getName(){
        return name;
    }

    public List<Operator> getOperators(){
        return operators;
    }

}
