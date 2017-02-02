package infrastructure.tooldatabase;

import domain.businessRule.BusinessRule;
import domain.businessRule.Definition;
import domain.businessRuleType.BusinessRuleType;
import domain.businessRuleType.Operator;
import domain.targetDatabase.Attribute;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by lucas on 26-1-2017.
 */
public class ToolDatabaseConnection {
    private final String host = "https://ondora02.hu.nl:8080/ords/";
    private final String workspace = "tosad_2016_2a_team6";

    public BusinessRule getBusinessRule(String businessruleName) {
        String businessrulesURL = host + workspace + "/businessrules/";
        JsonObject businessRuleJSON = (JsonObject) getJsonFromURL(businessrulesURL + businessruleName).getJsonArray("items").get(0);
        //List<Definition> definitions = getDefinitions(businessruleName);
        BusinessRule businessRule = new BusinessRule(getBusinessRuleTypeByCode(businessRuleJSON.getString("business_rule_type_code")));
        businessRule.setName(businessRuleJSON.getString("name"));
        businessRule.setErrorMessage(businessRuleJSON.getString("error_message"));
        businessRule.setFirstAttribute(new Attribute(businessRuleJSON.getString("attribute").substring(businessRuleJSON.getString("attribute").lastIndexOf(".") + 1, businessRuleJSON.getString("attribute").length())));
        /*for(Definition definition : definitions) {
            businessRule.addDefinition(definition);
        }*/
        businessRule.setOperator(getOperatorByName(businessRuleJSON.getString("operator_name")));
        return businessRule;
    }

    private JsonObject getJsonFromURL(String urlString) {
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

    public List<Definition> getDefinitions(String businessruleName) {
        String businessruleDefinitionURL = host + workspace + "/businessrule/" + businessruleName + "/definitions";
        List<Definition> definitions = new ArrayList<Definition>();
        JsonObject definitionsJSON = getJsonFromURL(businessruleDefinitionURL);
        JsonArray jay = definitionsJSON.getJsonArray("items");
        int i = 0;
        while (i < jay.size()) {
            Definition definition = new Definition();
            definition.setName(((JsonObject) jay.get(i)).getString("name"));
            System.out.println("def name: " + definition.getName());
            Object numObject = (((JsonObject) jay.get(i)).get("numeric_value"));
            Object stringObject = (((JsonObject) jay.get(i)).get("string_value"));
            Object dateObject = (((JsonObject) jay.get(i)).get("date_value"));
            if (numObject != null)
                definition.setValue(Integer.parseInt(numObject.toString()));
            else if (stringObject != null) {
                String st = stringObject.toString().substring(1, stringObject.toString().length() - 1);
                definition.setValue(st.toString());
            } else if (dateObject != null) {
                DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                try {
                    String st = dateObject.toString().substring(1, dateObject.toString().length() - 1);
                    definition.setValue(format.parse(st));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            definitions.add(definition);
            i++;
        }
        return definitions;
    }

    public Operator getOperatorByName(String operatorName) {
        String getOperatorURL = host + workspace + "/operator/" + operatorName;
        JsonObject response = (JsonObject) getJsonFromURL(getOperatorURL).getJsonArray("items").get(0);
        Operator operator = new Operator(response.getString("name"), response.getString("operator"));
        return operator;
    }

    public BusinessRuleType getBusinessRuleTypeByCode(String code) {
        String getBusinessRuleTypeURL = host + workspace + "/BusinessRuleType/" + code;
        JsonObject response = (JsonObject) getJsonFromURL(getBusinessRuleTypeURL).getJsonArray("items").get(0);
        BusinessRuleType businessRuleType = new BusinessRuleType(response.getString("code"), response.getString("name"), response.getString("description"));
        return businessRuleType;
    }

    public String getTableNameFromBusinessRule(String businessRuleName) {
        String businessrulesURL = host + workspace + "/businessrules/" + businessRuleName;
        JsonObject businessRuleJSON = (JsonObject) getJsonFromURL(businessrulesURL).getJsonArray("items").get(0);
        String attribute = businessRuleJSON.getString("attribute");
        return attribute.split("\\.")[1];
    }

    public List<String> getAllDatabaseTypes() {
        List<String> types = new ArrayList<String>();
        String url = host + workspace + "/scripttypes";
        JsonArray jay = getJsonFromURL(url).getJsonArray("items");
        int i = 0;
        while (i < jay.size()) {
            types.add(((JsonObject) jay.get(i)).getString("name"));
            i++;
        }
        return types;
    }
}
