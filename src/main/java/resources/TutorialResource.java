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
@Path("/hello")
public class TutorialResource {
    @GET
    @Path("/world")
    @Produces("application/json")
    public String hello(){
        return hello("World");
    }

    @GET
    @Path("/{name}")
    @Produces("application/json")
    public String hello(@PathParam("name") String name){
        JsonArrayBuilder jab = Json.createArrayBuilder();
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("Hello", name);
        jab.add(job);
        return jab.build().toString();
    }
}
