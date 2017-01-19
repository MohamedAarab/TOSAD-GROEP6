package main;

//import businessrules.AttributeCompareRule;
//import businessrules.ListRule;
//import businessrules.RangeRule;
//import businessrules.test.Definition;
//import businessrules.test.TestBusinessRule;
import domain.businessRule.BusinessRule;
import domain.businessRule.Definition;
import domain.businessRule.OracleScript;
import domain.businessRule.Script;
import domain.businessRuleType.AttributeCompareRule.AttributeCompareRule;
import domain.businessRuleType.BusinessRuleType;
import domain.businessRuleType.Operator;
import domain.targetDatabase.Attribute;
import org.antlr.stringtemplate.StringTemplate;
import task.GenerateController;

import java.util.*;

public class Main {

    public static void main(String[] args) {
//        List<String> list = new ArrayList<String>();
//        list.add("hi");
//        list.add("hoi");
//        ListRule rule = new ListRule(list);
//        rule.setValue(new LinkedList<String>());
//        RangeRule rangeRule = new RangeRule();
//        Map<String, Integer> map = new LinkedHashMap<>();
//        map.put("minimum", 1);
//        map.put("maximum", 2);
//        rangeRule.setValue(map);
//        AttributeCompareRule attributeCompareRule = new AttributeCompareRule();
//        attributeCompareRule.setValue(1.01);
//        List<domain.businessRule.Definition> rangeRuleDefinitions = new ArrayList<domain.businessRule.Definition>();
////        rangeRuleDefinitions.add(new domain.businessRule.Definition("minimum"));
////        rangeRuleDefinitions.add(new domain.businessRule.Definition("maximum"));
////        TestBusinessRule testBusinessRule = new TestBusinessRule(rangeRuleDefinitions);
        //System.out.println(GenerateController.getAllCategories());
        BusinessRuleType businessRuleType = new AttributeCompareRule("ACMP", "Attribute Compare Rule", "HI");
        BusinessRule businessRule = new BusinessRule(businessRuleType);
        businessRule.setOperator(new Operator("GreaterThan"));
        businessRule.addDefinition(new Definition("comparevalue", 10));
        businessRule.setFirstAttribute(new Attribute("attribute"));
        Script script = new OracleScript("testScript",Arrays.asList("update","insert"), businessRule);
        script.generate();
    }
}
