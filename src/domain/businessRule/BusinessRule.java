package domain.businessRule;

import domain.businessRuleType.BusinessRuleType;
import domain.businessRuleType.Operator;
import domain.targetDatabase.Attribute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Unknown on 01/18/2017.
 */
public class BusinessRule {
    private Operator operator;
    private String errorMessage;
    private String name;
    private Attribute firstAttribute;
    private List<Definition> definitions;
    private BusinessRuleType businessRuleType;

    public BusinessRule(BusinessRuleType businessRuleType){
        definitions = new ArrayList<Definition>();
        this.businessRuleType = businessRuleType;
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

    public void setFirstAttribute(Attribute firstAttribute) {
        this.firstAttribute = firstAttribute;
    }

    public void addDefinition(Definition definition){
        this.definitions.add(definition);
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public Operator getOperator() {
        return operator;
    }

    public BusinessRuleType getBusinessRuleType() {
        return businessRuleType;
    }

    public Attribute getFirstAttribute() {
        return firstAttribute;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void save(){
        // TODO: 13-1-2017
    }
}
