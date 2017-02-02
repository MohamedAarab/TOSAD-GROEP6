package domain.targetDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 13-1-2017.
 */
public class Scheme {
    private String name;
    private List<Table> tables;

    public Scheme(String name) {
        this.name = name;
        this.tables = new ArrayList<Table>();
    }

    public String getName() {
        return name;
    }

    public List<Table> getTables() {
        return tables;
    }

    public Table getTableByName(String tableName) {
        Table table = null;
        for (Table t : tables) {
            if (t.getName().equals(tableName)) {
                table = t;
                break;
            }
        }
        return table;
    }

    public void addTable(Table table) {
        tables.add(table);
    }

    @Override
    public String toString() {
        String returnString = "Scheme : " + name + ", Tables : [";
        for (int i = 0; i < tables.size(); i++) {
            returnString += "\n" + tables.get(i).toString();
            if (i != tables.size() - 1)
                returnString += " ,";
        }
        if (tables.size() > 0)
            returnString = returnString.substring(0, returnString.length());
        returnString += "\n]";
        return returnString;
    }
}
