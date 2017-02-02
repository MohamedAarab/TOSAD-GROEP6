package task;

import domain.targetDatabase.ITargetDatabaseService;
import domain.targetDatabase.TargetDatabaseServiceImp;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by lucas on 27-1-2017.
 */
@Path("/execute")
public class ExecuteController implements IExecuteController {
    @POST
    @Produces("application/json")
    @Override
    public String executeScript(@FormParam("host") String host, @FormParam("databaseName") String databaseName, @FormParam("triggerCode") String triggerCode) {
        ITargetDatabaseService targetDatabaseService = TargetDatabaseServiceImp.getInstance();
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("message", targetDatabaseService.executeScript(host, databaseName, triggerCode));
        return job.build().toString();
    }
}
