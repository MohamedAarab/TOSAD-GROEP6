package domain.targetDatabase;

/**
 * Created by lucas on 13-1-2017.
 */
public class Attribute {
    private String name;
    private DataType type;

    public Attribute(String name) {
        this.name = name;
    }

    public Attribute(String name, DataType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Attribute : " + name;
    }

    public DataType getType() {
        return type;
    }
}
