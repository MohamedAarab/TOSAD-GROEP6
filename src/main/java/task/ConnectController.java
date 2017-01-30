package task;

import domain.targetDatabase.*;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by lucas on 26-1-2017.
 */
@Path("/targetdatabase")
public class ConnectController implements IConnectController {
    TargetDatabaseServiceImp targetDatabaseService = TargetDatabaseServiceImp.getInstance() ;

    @GET
    @Path("/connect/{targetURL}/{port}/{databaseName}/{databaseType}/{username}/{password}")
    @Produces("application/json")
    @Override
    public String connectToDatabase(@PathParam("targetURL") String host, @PathParam("port") int port, @PathParam("databaseName") String databaseName, @PathParam("databaseType") String databaseType, @PathParam("username") String username, @PathParam("password") String password) {
        JsonObjectBuilder job = Json.createObjectBuilder();
        try{
            targetDatabaseService.connectToDatabase(databaseType,host,port,databaseName,username,password);
        } catch (Exception e){
           job.add("succes", "Cannot connect to targetdatabase");
        }
        if(targetDatabaseService.getTargetDatabaseByHost(host) != null)
            job.add("succes", "true");
        return job.build().toString();
    }

    @GET
    @Path("/schemes/{targetURL}")
    @Produces("application/json")
    public String getAllSchemesFromDatabase(@PathParam("targetURL") String host){
        List<Scheme> schemes = null;
        JsonObjectBuilder job = Json.createObjectBuilder();
        JsonArrayBuilder jab = Json.createArrayBuilder();
        try {
            schemes = targetDatabaseService.getAllSchemes(host);
            for(Scheme scheme : schemes){
                jab.add(scheme.getName());
            }
            job.add("schemes", jab);
        } catch (NullPointerException e){
            job.add("schemes", "please connect to targetdatabase first!");
        }
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
