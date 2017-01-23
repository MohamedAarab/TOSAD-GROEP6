package resources;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Created by lucas on 22-1-2017.
 */
@Path("/test")
public class TestResource {
    @GET
    @Produces("application/json")
    public String test(){
        JsonArrayBuilder jab = Json.createArrayBuilder();
        jab.add("Test passed!");
        return jab.build().toString();
    }
}
