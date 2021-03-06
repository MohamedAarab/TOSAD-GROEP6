package task;

import domain.targetDatabase.Attribute;
import domain.targetDatabase.Scheme;
import domain.targetDatabase.Table;
import domain.targetDatabase.TargetDatabaseServiceImp;

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
    TargetDatabaseServiceImp targetDatabaseService = TargetDatabaseServiceImp.getInstance();

    @GET
    @Path("/connect/{targetURL}/{port}/{databaseName}/{databaseType}/{username}/{password}")
    @Produces("application/json")
    @Override
    public String connectToDatabase(@PathParam("targetURL") final String host, @PathParam("port") final int port, @PathParam("databaseName") final String databaseName, @PathParam("databaseType") final String databaseType, @PathParam("username") final String username, @PathParam("password") final String password) {
        JsonObjectBuilder job = Json.createObjectBuilder();
        boolean result = false;
        try {
            result = targetDatabaseService.checkConnection(databaseType, host, port, databaseName, username, password);
            if (result) {
                Thread thread = new Thread(new Runnable() {
                    public void run() {
                        targetDatabaseService.connectToDatabase(databaseType, host, port, databaseName, username, password);
                    }
                });
                thread.start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (e.getMessage().contains("Access denied"))
                job.add("succes", "Access denied; Possible reason wrong username or password");
            else if (e.getMessage().contains("ORA-01017"))
                job.add("succes", "invalid username/password; logon denied");
            else
                job.add("succes", "Cannot connect to targetdatabase");
            return job.build().toString();
        }
        //if(targetDatabaseService.getTargetDatabaseByHost(host, databaseName) != null)
        job.add("succes", result);
        return job.build().toString();
    }

    @GET
    @Path("/schemes/{targetURL}/{databaseName}")
    @Produces("application/json")
    public String getAllSchemesFromDatabase(@PathParam("targetURL") String host, @PathParam("databaseName") String databaseName) {
        List<Scheme> schemes = null;
        JsonObjectBuilder job = Json.createObjectBuilder();
        JsonArrayBuilder jab = Json.createArrayBuilder();
        try {
            schemes = targetDatabaseService.getAllSchemes(host, databaseName);
            for (Scheme scheme : schemes) {
                jab.add(scheme.getName());
            }
            job.add("schemes", jab);
        } catch (NullPointerException e) {
            job.add("schemes", "please connect to targetdatabase first!");
        }
        return job.build().toString();
    }

    @GET
    @Path("/tables/{targetURL}/{databaseName}/{schemeName}")
    @Produces("application/json")
    public String getAllTablesFromDatabaseScheme(@PathParam("targetURL") String host, @PathParam("databaseName") String databaseName, @PathParam("schemeName") String schemeName) {
        List<Table> tables = targetDatabaseService.getTablesFromScheme(host, databaseName, schemeName);
        JsonObjectBuilder job = Json.createObjectBuilder();
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (Table table : tables) {
            jab.add(table.getName());
        }
        job.add("tables", jab);
        return job.build().toString();
    }

    @GET
    @Path("/attributes/{targetURL}/{databaseName}/{schemeName}/{tableName}")
    @Produces("application/json")
    public String getAllAttributesFromTable(@PathParam("targetURL") String host, @PathParam("databaseName") String databaseName, @PathParam("schemeName") String schemeName, @PathParam("tableName") String tableName) {
        List<Attribute> attributes = targetDatabaseService.getAttributesFromTable(host, databaseName, schemeName, tableName);
        JsonObjectBuilder job = Json.createObjectBuilder();
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (Attribute attribute : attributes) {
            jab.add(attribute.getName());
        }
        job.add("attributes", jab);
        return job.build().toString();
    }

    @GET
    @Path("/attributetype/{targetURL}/{databaseName}/{schemeName}/{tableName}/{attributeName}")
    @Produces("application/json")
    public String getDatabaseTypeFromAttribute(@PathParam("targetURL") String host, @PathParam("databaseName") String databaseName, @PathParam("schemeName") String schemeName, @PathParam("tableName") String tableName, @PathParam("attributeName") String attributeName) {
        domain.targetDatabase.DataType dataType = targetDatabaseService.getDatabaseTypeFromAttribute(host, databaseName, schemeName, tableName, attributeName);
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("datatype", dataType.toString());
        return job.build().toString();
    }

    @GET
    @Path("/remove/{targetURL}/{databaseName}")
    @Produces("application/json")
    public String removeTargetDatabase(@PathParam("targetURL") String host, @PathParam("databaseName") String databaseName) {
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("Amount of targetdatabases connected", targetDatabaseService.removeTargetDatabase(host, databaseName));
        return job.build().toString();
    }
}
