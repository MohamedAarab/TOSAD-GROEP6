package task;

import domain.businessRule.StringTemplate;
import domain.targetDatabase.*;

import java.util.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        /*links voor rest:
        * generate/<brname>/<eventlist(comma seperated)/<scripttype>
        * targetdatabase/connect/<host>/<databasename>/<databasetype>/<username>/<password>
        * targetdatabase/schemes/<host>
        * targetdatabase/tables/<host>/<scheme>
        * targetdatabase/attributes/<host>/<scheme>/<table>        *
        * */
        long start = System.currentTimeMillis();

        ITargetDatabaseService targetDatabaseService = TargetDatabaseServiceImp.getInstance();
        /*String host = "128.140.222.220";
        targetDatabaseService.connectToDatabase("MySQL", host, "advertmedi_tos","advertmedi_tos", "07yomnM4");*/
        String host = "mysql4.gear.host";
        targetDatabaseService.connectToDatabase("MySQL", host, "test67", "admin12", "admin12!");
        for(Scheme s : targetDatabaseService.getAllSchemes(host)){
            System.out.println(s.toString());
        }
        System.out.println("Duration: " + (System.currentTimeMillis() - start));
        GenerateController generateController = new GenerateController();
        String code = generateController.generateCode("med_id_br","update", "MySQL");
        code = code.substring(9,code.length()-2);
        ExecuteController executeController = new ExecuteController();
        System.out.println(executeController.executeScript(host, code));
    }
}
