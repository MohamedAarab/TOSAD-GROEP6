package domain.businessRuleType;

import java.util.List;

/**
 * Created by Unknown on 01/18/2017.
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

    public String getName() {
        return this.name;
    }

    public List<Operator> getOperators() {
        return operators;
    }

    public void setOperators(List<Operator> operators) {
        this.operators = operators;
    }

    @Override
    public String toString() {
        return "BusinessRuleType{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
