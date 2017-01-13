package businessrules;

/**
 * Created by lucas on 13-1-2017.
 */
public class AttributeCompareRule extends BusinessRule {
    private double value;

    @Override
    public void setValue(Object value) {
        if(value instanceof Number){
            this.value = Double.parseDouble(value.toString());
        }
    }
}
