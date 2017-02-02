package task;

import domain.businessRule.BusinessRule;
import domain.businessRule.BusinessRuleServiceImp;
import domain.businessRule.IBusinessRuleService;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 27-1-2017.
 */
@Path("/generate")
public class GenerateController implements IGenerateController {
    @Path("/{businessRuleName}/{eventList}/{scriptType}/{scriptName}")
    @GET
    @Override
    public String generateCode(@PathParam("businessRuleName") String businessRuleName, @PathParam("eventList") String eventList, @PathParam("scriptType") String scriptType, @PathParam("scriptName") String scriptName) {
        IBusinessRuleService businessRuleService = new BusinessRuleServiceImp();
        BusinessRule businessRule = businessRuleService.createBusinessRuleForCode(businessRuleName);
        List<String> events = new ArrayList<String>();
        for (String s : eventList.split(",")) {
            events.add(s);
        }
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("code", businessRuleService.generateCode(businessRule, events, scriptType, scriptName, businessRuleService.getTableNameFromBusinessRule(businessRuleName)));
        return job.build().toString();
    }
}
