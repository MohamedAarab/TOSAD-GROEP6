package businessrules.DAO;

import java.util.Map;

/**
 * Created by lucas on 13-1-2017.
 */
public class RangeRule extends BusinessRule {
    private int minimum;
    private int maximum;

    @Override
    public void setValue(Object value) {//value has to be a Map<string, Integer> with maximum and minimum key
        if(Map.class.isInstance(value)){
            Map val;
            val = (Map) value;
            if(val.keySet().size() == 2 && val.keySet().toArray()[0] instanceof String && val.values().toArray()[0] instanceof Integer){
                Map<String, Integer> valueMap = (Map<String, Integer>) value;
                this.minimum = valueMap.get("minimum");
                this.maximum = valueMap.get("maximum");
            }
        }
        System.out.println("min: " + minimum + ", max: " + maximum);
    }
}
