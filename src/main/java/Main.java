import domain.businessRule.BusinessRule;
import domain.businessRule.Definition;
import domain.businessRule.OracleScript;
import domain.businessRule.Script;
import domain.businessRuleType.*;
import domain.targetDatabase.Attribute;
import domain.targetDatabase.Scheme;
import domain.targetDatabase.Table;
import domain.targetDatabase.TargetDatabase;
import infrastructure.TargetDatabaseDAO;

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

        TargetDatabase targetDatabase = new TargetDatabaseDAO("MySQL", "admin12", "admin12!", "mysql4.gear.host").createTargetDatabase();
        for(Scheme s : targetDatabase.getSchemes()){
            System.out.println("Scheme: " + s.getName());
            for (Table t : s.getTables()){
                System.out.println("table: " + t.getName());
                for(Attribute a : t.getAttributes()){
                    System.out.println("Scheme : " + s.getName() + ", Table : " + t.getName() + ", Attribute : " + a.getName());
                }
            }
        }
    }
}
