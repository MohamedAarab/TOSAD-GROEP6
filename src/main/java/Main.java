import domain.businessRule.BusinessRule;
import domain.businessRule.Definition;
import domain.businessRule.OracleScript;
import domain.businessRule.Script;
import domain.businessRuleType.AttributeCompareRule;
import domain.businessRuleType.BusinessRuleType;
import domain.businessRuleType.Operator;
import domain.businessRuleType.RangeRule;
import domain.targetDatabase.Attribute;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        BusinessRuleType ACMPtype = new AttributeCompareRule("ACMP", "Attribute Compare Rule", "HI");
        BusinessRuleType ARNGtype = new RangeRule("ARNG", "Attribute Range Rule", "");

        BusinessRule attributeCompareRule = new BusinessRule(ACMPtype);
        attributeCompareRule.setOperator(new Operator("GreaterThan", ">"));
        attributeCompareRule.addDefinition(new Definition("comparevalue", 10));
        attributeCompareRule.setFirstAttribute(new Attribute("attribute"));
        attributeCompareRule.setErrorMessage("error1");
        Script script = new OracleScript("testScript",Arrays.asList("update","insert"), attributeCompareRule);
        script.generate();

        System.out.println("\n");
        BusinessRule rangeRule = new BusinessRule(ARNGtype);
        rangeRule.setOperator(new Operator("between", "between"));
        rangeRule.addDefinition(new Definition("minimum", 10));
        rangeRule.addDefinition(new Definition("maximum", 20));
        rangeRule.setFirstAttribute(new Attribute("attribute"));
        Script script2 = new OracleScript("testScript",Arrays.asList("update","insert"), rangeRule);
        script2.generate();
    }
}
