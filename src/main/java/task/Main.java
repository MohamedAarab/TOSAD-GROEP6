package task;

import domain.businessRule.BusinessRule;
import domain.businessRule.Definition;
import domain.businessRule.Script;
import domain.businessRuleType.*;
import domain.targetDatabase.*;
import infrastructure.IToolDatabaseService;
import infrastructure.ToolDatabaseServiceImp;

import java.util.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        /*links voor rest:
        * generate/<brname>/<eventlist(comma seperated)/<scripttype>
        * targetdatabase/connect/<host>/<databasetype>/<username>/<password>
        * targetdatabase/schemes/<host>
        * targetdatabase/tables/<host>/<scheme>
        * targetdatabase/attributes/<host>/<scheme>/<table>        *
        * */
        long start = System.currentTimeMillis();
        BusinessRuleType ACMPtype = new BusinessRuleType("ACMP", "Attribute Compare Rule", "HI");
        BusinessRuleType ARNGtype = new BusinessRuleType("ARNG", "Attribute Range Rule", "");
        /*
        BusinessRule attributeCompareRule = new BusinessRule(ACMPtype);
        attributeCompareRule.setOperator(new Operator("GreaterThan", ">"));
        attributeCompareRule.addDefinition(new Definition("comparevalue", 10));
        attributeCompareRule.setFirstAttribute(new Attribute("attribute"));
        attributeCompareRule.setErrorMessage("error1");
        Script script = new Script("testScript",Arrays.asList("update","insert"), attributeCompareRule);
        script.generate();*/

        System.out.println("\n");
        BusinessRule rangeRule = new BusinessRule(ARNGtype);
        rangeRule.setOperator(new Operator("between", "between"));
        rangeRule.addDefinition(new Definition("minimum", 10));
        rangeRule.addDefinition(new Definition("maximum", 20));
        rangeRule.setFirstAttribute(new Attribute("attribute"));
        Script script2 = new Script("testScript",Arrays.asList("update","insert"), rangeRule);
        System.out.println(script2.generate("table", "MySQL"));

        /*String range = "domain.businessRuleType.Attribute -Compare- Rule".replaceAll(" ", "").replaceAll("-", "");
        BusinessRuleType rangeObj = (BusinessRuleType) Class.forName(range).newInstance();
        for(ITemplate t : rangeObj.getTemplates()){
            System.out.println(t.getTemplate().toString());
        }*/

        ITargetDatabaseService targetDatabaseService = new TargetDatabaseServiceImp();
        String host = "128.140.222.220";
        targetDatabaseService.connectToDatabase("MySQL", host, "advertmedi_tos", "07yomnM4");
        for(Scheme s : targetDatabaseService.getAllSchemes(host)){
            System.out.println(s.toString());
        }
        System.out.println("Duration: " + (System.currentTimeMillis() - start));

        IToolDatabaseService toolDatabaseService = new ToolDatabaseServiceImp();
        System.out.println(toolDatabaseService.getBusinessRuleByName("mijnbr"));
        System.out.println(toolDatabaseService.getTableNameFromBusinessRule("mijnbr"));
    }
}
