package domain.targetDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 13-1-2017.
 */
public class Scheme {
    private String name;
    private List<Table> tables;

    public Scheme(String name){
        this.name = name;
        this.tables = new ArrayList<Table>();
    }

    public String getName(){
        return name;
    }

    public List<Table> getTables(){
        return tables;
    }
}
