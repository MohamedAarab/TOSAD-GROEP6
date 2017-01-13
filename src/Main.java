import businessrules.AttributeCompareRule;
import businessrules.ListRule;
import businessrules.RangeRule;
import businessrules.test.Definition;
import businessrules.test.TestBusinessRule;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("hi");
        list.add("hoi");
        ListRule rule = new ListRule(list);
        rule.setValue(new LinkedList<String>());
        RangeRule rangeRule = new RangeRule();
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("minimum", 1);
        map.put("maximum", 2);
        rangeRule.setValue(map);
        AttributeCompareRule attributeCompareRule = new AttributeCompareRule();
        attributeCompareRule.setValue(1.01);
        List<Definition> rangeRuleDefinitions = new ArrayList<Definition>();
        rangeRuleDefinitions.add(new Definition("minimum"));
        rangeRuleDefinitions.add(new Definition("maximum"));
        TestBusinessRule testBusinessRule = new TestBusinessRule(rangeRuleDefinitions);
    }
}
