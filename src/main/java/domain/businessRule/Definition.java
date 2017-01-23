package domain.businessRule;

public class Definition {
    private String name;
    private Object value;

    public Definition(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
