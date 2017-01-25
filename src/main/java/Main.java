import domain.businessRule.BusinessRule;
import domain.businessRule.Definition;
import domain.businessRule.OracleScript;
import domain.businessRule.Script;
import domain.businessRuleType.*;
import domain.targetDatabase.Attribute;

import java.util.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        /*BusinessRuleType ACMPtype = new AttributeCompareRule("ACMP", "Attribute Compare Rule", "HI");
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
        script2.generate();*/

        String range = "domain.businessRuleType.Attribute -Compare- Rule".replaceAll(" ", "").replaceAll("-", "");
        BusinessRuleType rangeObj = (BusinessRuleType) Class.forName(range).newInstance();
        for(ITemplate t : rangeObj.getTemplates()){
            System.out.println(t.getTemplate().toString());
        }
    }
}
