package main;

//import businessrules.AttributeCompareRule;
//import businessrules.ListRule;
//import businessrules.RangeRule;
//import businessrules.test.Definition;
//import businessrules.test.TestBusinessRule;
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
        System.out.println(GenerateController.getAllCategories());
    }
}
