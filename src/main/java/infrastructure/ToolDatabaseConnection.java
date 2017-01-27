package infrastructure;

import domain.businessRule.BusinessRule;
import domain.businessRule.Definition;
import domain.businessRuleType.BusinessRuleType;
import domain.businessRuleType.Operator;
import domain.targetDatabase.Attribute;

import javax.json.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 26-1-2017.
 */
public class ToolDatabaseConnection {
    private final String host = "https://ondora02.hu.nl:8080/ords/";
    private final String workspace = "tosad_2016_2a_team6";

    public BusinessRule getBusinessRule(String businessruleName){
        String businessrulesURL = host + workspace + "/businessrules/";
        JsonObject businessRuleJSON = (JsonObject) getJsonFromURL(businessrulesURL + businessruleName).getJsonArray("items").get(0);
        List<Definition> definitions = getDefinitions(businessruleName);
        BusinessRule businessRule = new BusinessRule(getBusinessRuleTypeByCode(businessRuleJSON.getString("business_rule_type_code")));
        businessRule.setName(businessRuleJSON.getString("name"));
        businessRule.setErrorMessage(businessRuleJSON.getString("error_message"));
        businessRule.setFirstAttribute(new Attribute(businessRuleJSON.getString("attribute").substring(businessRuleJSON.getString("attribute").lastIndexOf(".") +1, businessRuleJSON.getString("attribute").length())));
        for(Definition definition : definitions) {
            businessRule.addDefinition(definition);
        }
        businessRule.setOperator(getOperatorByName(businessRuleJSON.getString("operator_name")));
        return businessRule;
    }

    private JsonObject getJsonFromURL(String urlString){
        JsonObject object = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String respone = "";
            String line = null;
            try {
                while ((line = rd.readLine()) != null) {
                    respone += line;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            JsonReader jsonReader = Json.createReader(new StringReader(respone));
            object = jsonReader.readObject();
            jsonReader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    private List<Definition> getDefinitions(String businessruleName){
        String businessruleDefinitionURL = host + workspace + "/businessrule/" + businessruleName + "/definitions";
        List<Definition> definitions = new ArrayList<Definition>();
        JsonObject definitionsJSON = getJsonFromURL(businessruleDefinitionURL);
        JsonArray jay = definitionsJSON.getJsonArray("items");
        int i = 0;
        while(i <jay.size()){
            Definition definition = new Definition();
            definition.setName(((JsonObject)jay.get(i)).getString("name"));
            definition.setValue(
                    Double.parseDouble((((JsonObject) jay.get(i)).get("numeric_value")).toString())
            );
            if(definition.getValue() == null) {
                definition.setValue(
                        (((JsonObject) jay.get(i)).get("string_value")).toString()
                );
            }
            definitions.add(definition);
            i++;
        }
        return definitions;
    }

    private Operator getOperatorByName(String operatorName){
        String getOperatorURL = host + workspace + "/operator/" + operatorName;
        JsonObject response = (JsonObject) getJsonFromURL(getOperatorURL).getJsonArray("items").get(0);
        Operator operator = new Operator(response.getString("name"), response.getString("operator"));
        return operator;
    }

    private BusinessRuleType getBusinessRuleTypeByCode(String code){
        String getBusinessRuleTypeURL = host + workspace + "/BusinessRuleType/" + code;
        JsonObject response = (JsonObject) getJsonFromURL(getBusinessRuleTypeURL).getJsonArray("items").get(0);
        BusinessRuleType businessRuleType = new BusinessRuleType(response.getString("code"), response.getString("name"), response.getString("description"));
        return businessRuleType;
    }

    public String getTableNameFromBusinessRule(String businessRuleName){
        String businessrulesURL = host + workspace + "/businessrules/" + businessRuleName;
        JsonObject businessRuleJSON = (JsonObject) getJsonFromURL(businessrulesURL ).getJsonArray("items").get(0);
        String attribute = businessRuleJSON.getString("attribute");
        return attribute.split("\\.")[1];
    }

    public List<String> getAllDatabaseTypes(){
        List<String> types = new ArrayList<String>();
        String url = host + workspace + "/scripttypes";
        JsonArray jay = getJsonFromURL(url).getJsonArray("items");
        int i =0;
        while (i <jay.size()){
            types.add(((JsonObject) jay.get(i)).getString("name"));
            i++;
        }
        return types;
    }
}