package domain.businessRuleType;

/**
 * Created by lucas on 13-1-2017.
 */
public class Operator {
    public String name;
    private String operator;

    public Operator(String name, String operator){
        this.name = name;
        this.operator = operator;
    }

    public String getName(){
        return name;
    }

    public String getOperator() {
        return operator;
    }
}
