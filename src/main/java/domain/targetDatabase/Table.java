package domain.targetDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 13-1-2017.
 */
public class Table {
    private String name;
    private List<Attribute> attributes;

    public Table(String name){
        this.name = name;
        this.attributes = new ArrayList<Attribute>();
    }

    public List<Attribute> getAttributes(){
        return attributes;
    }

    public Attribute getAttributeByName(String name){
        Attribute attribute = null;
        for(Attribute a : attributes){
            if(a.getName().equals(name)){
                attribute = a;
                break;
            }
        }
        return attribute;
    }

    public String getName(){
        return name;
    }

    public void addAttribute(Attribute attribute){
        attributes.add(attribute);
    }

}
