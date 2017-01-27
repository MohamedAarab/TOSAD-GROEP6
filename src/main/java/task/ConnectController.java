package task;

import domain.targetDatabase.*;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by lucas on 26-1-2017.
 */
@Path("/targetdatabase")
public class ConnectController implements IConnectController {
    TargetDatabaseServiceImp targetDatabaseService = TargetDatabaseServiceImp.getInstance() ;

    @GET
    @Path("/connect/{targetURL}/{databaseType}/{username}/{password}")
    @Produces("application/json")
    @Override
    public String connectToDatabase(@PathParam("targetURL") String host, @PathParam("databaseType") String databaseType, @PathParam("username") String username, @PathParam("password") String password) {
        targetDatabaseService.connectToDatabase(databaseType,host,username,password);
        JsonObjectBuilder job = Json.createObjectBuilder();
        if(targetDatabaseService.getTargetDatabaseByHost(host) != null)
            job.add("succes", "true");
        else
            job.add("succes", "false");
        return job.build().toString();
    }

    @GET
    @Path("/schemes/{targetURL}")
    @Produces("application/json")
    public String getAllSchemesFromDatabase(@PathParam("targetURL") String host){
        List<Scheme> schemes = targetDatabaseService.getAllSchemes(host);
        JsonObjectBuilder job = Json.createObjectBuilder();
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for(Scheme scheme : schemes){
            jab.add(scheme.getName());
        }
        job.add("schemes", jab);
        return job.build().toString();
    }

    @GET
    @Path("/tables/{targetURL}/{schemeName}")
    @Produces("application/json")
    public String getAllTablesFromDatabaseScheme(@PathParam("targetURL") String host, @PathParam("schemeName") String schemeName){
        List<Table> tables = targetDatabaseService.getTablesFromScheme(host, schemeName);
        JsonObjectBuilder job = Json.createObjectBuilder();
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for(Table table : tables){
            jab.add(table.getName());
        }
        job.add("tables", jab);
        return job.build().toString();
    }

    @GET
    @Path("/attributes/{targetURL}/{schemeName}/{tableName}")
    @Produces("application/json")
    public String getAllAttributesFromTable(@PathParam("targetURL") String host, @PathParam("schemeName") String schemeName, @PathParam("tableName") String tableName){
        List<Attribute> attributes = targetDatabaseService.getAttributesFromTable(host,schemeName,tableName);
        JsonObjectBuilder job = Json.createObjectBuilder();
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for(Attribute attribute : attributes){
            jab.add(attribute.getName());
        }
        job.add("attributes", jab);
        return job.build().toString();
    }

}
