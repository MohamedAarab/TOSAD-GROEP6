package domain.businessRuleType;

import domain.businessRule.Definition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Unknown on 01/18/2017.
 */
public abstract class BusinessRuleType {
    private List<ITemplate> templates;
    private String code;
    private String name;
    private String description;
    private List<Operator> operators;
    private static List<BusinessRuleType> allRuleTypes;

    public BusinessRuleType(String code, String name, String description) {
        this.templates = new ArrayList<ITemplate>();
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return this.name;
    }

    public List<Operator> getOperators() {
        return operators;
    }

    public void setOperators(List<Operator> operators){
        this.operators = operators;
    }

    public abstract void validate(List<Definition> definitions);

    protected void addTemplate(ITemplate template){
        templates.add(template);
    }

    public List<ITemplate> getTemplates() {
        return templates;
    }

    public static List<BusinessRuleType> getAllRuleTypes(){
        return allRuleTypes;
    }
}
