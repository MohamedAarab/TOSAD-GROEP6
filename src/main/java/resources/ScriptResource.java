package resources;

import domain.businessRule.BusinessRule;
import domain.businessRule.Definition;
import domain.businessRule.OracleScript;
import domain.businessRule.Script;
import domain.businessRuleType.AttributeCompareRule;
import domain.businessRuleType.BusinessRuleType;
import domain.businessRuleType.Operator;
import domain.businessRuleType.RangeRule;
import domain.targetDatabase.Attribute;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Arrays;

/**
 * Created by lucas on 23-1-2017.
 */
@Path("/script")
public class ScriptResource {

    @GET
    @Produces("application/json")
    public String generateScript(){
        JsonObjectBuilder job = Json.createObjectBuilder();
        BusinessRuleType ACMPtype = new AttributeCompareRule("ACMP", "Attribute Compare Rule", "HI");
        BusinessRuleType ARNGtype = new RangeRule("ARNG", "Attribute Range Rule", "");

        BusinessRule attributeCompareRule = new BusinessRule(ACMPtype);
        attributeCompareRule.setName("attribute_compare_rule");
        attributeCompareRule.setOperator(new Operator("GreaterThan", ">"));
        attributeCompareRule.addDefinition(new Definition("comparevalue", 10));
        attributeCompareRule.setFirstAttribute(new Attribute("attribute"));
        attributeCompareRule.setErrorMessage("error1");
        Script script = new OracleScript("testScript", Arrays.asList("update","insert"), attributeCompareRule);
        JsonObjectBuilder job1 = Json.createObjectBuilder();
        job.add(attributeCompareRule.getName(), script.generate("table1"));

        BusinessRule rangeRule = new BusinessRule(ARNGtype);
        rangeRule.setName("range_rule");
        rangeRule.setOperator(new Operator("between", "between"));
        rangeRule.addDefinition(new Definition("minimum", 10));
        rangeRule.addDefinition(new Definition("maximum", 20));
        rangeRule.setFirstAttribute(new Attribute("attribute"));
        Script script2 = new OracleScript("testScript",Arrays.asList("update","insert"), rangeRule);
        JsonObjectBuilder job2 = Json.createObjectBuilder();
        job.add(rangeRule.getName(), script2.generate("table2"));
        return job.build().toString();
    }
}
