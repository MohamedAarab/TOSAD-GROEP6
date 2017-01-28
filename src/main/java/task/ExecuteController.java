package task;

import domain.targetDatabase.ITargetDatabaseService;
import domain.targetDatabase.TargetDatabaseServiceImp;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Created by lucas on 27-1-2017.
 */
@Path("/execute")
public class ExecuteController implements IExecuteController {
    @Path("/{host}/{triggerCode}")
    @GET
    @Produces("application/json")
    @Override
    public String executeScript(@PathParam("host") String host, @PathParam("triggerCode") String triggerCode) {
        ITargetDatabaseService targetDatabaseService = TargetDatabaseServiceImp.getInstance();
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("message", targetDatabaseService.executeScript(host, triggerCode));
        return job.build().toString();
    }
}
