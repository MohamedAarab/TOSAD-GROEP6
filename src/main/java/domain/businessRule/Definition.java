package domain.businessRule;

public class Definition {
    private String name;
    private Object value;

    public Definition() {
    }

    public Definition(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Definition{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
