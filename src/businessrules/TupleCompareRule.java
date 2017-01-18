package businessrules;

import domain.targetDatabase.Attribute;

/**
 * Created by lucas on 13-1-2017.
 */
public class TupleCompareRule extends BusinessRule {
    private Attribute secondAttribute;

    @Override
    public void setValue(Object value) {
        if(value instanceof Attribute){
            this.secondAttribute = (Attribute) value;
        }
    }
}
