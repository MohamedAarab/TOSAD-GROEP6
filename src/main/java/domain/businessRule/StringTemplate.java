package domain.businessRule;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by lucas on 27-1-2017.
 */
public class StringTemplate {
    private Map<String, String> attributeMap = null;
    // identifier(between dollar signs at start, value)
    private String template;

    public StringTemplate(String template){
        this.template = template;
        getAllAttributes();
    }

    public Set<String> getAllAttributes(){
        if(attributeMap == null) {
            attributeMap = new HashMap<String, String>();
            Pattern pattern = Pattern.compile("\\$(.*?)\\$");
            Matcher matcher = pattern.matcher(template);
            while (matcher.find()) {
                attributeMap.put(matcher.group(1),matcher.group(1));
            }
        }
        return attributeMap.keySet();
    }

    public void setAttribute(String name, String value){
        attributeMap.put(name, value);
    }

    @Override
    public String toString() {
        String string = "" + template;
        string = string.replaceAll("\\$", "");
        for(String attribute : attributeMap.keySet()){
            string = string.replace(attribute, attributeMap.get(attribute));
        }
        return string;
    }

    public static void main(String[] args) {
        StringTemplate stringTemplate = new StringTemplate("$firstAttribute$ $operator$ $comparevalue$;");
        stringTemplate.setAttribute("firstAttribute", "hoi");
        stringTemplate.setAttribute("firstAttribute", "hallo!");
        stringTemplate.setAttribute("noob", "hi");
        System.out.println(stringTemplate);
        //stringTemplate.getAllAttributes();
    }

}
