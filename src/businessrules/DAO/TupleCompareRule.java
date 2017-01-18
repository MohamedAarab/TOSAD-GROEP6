package businessrules.DAO;

import domain.targetDatabase.Attribute;

/**
 * Created by lucas on 13-1-2017.
 */
public class TupleCompareRule extends BusinessRule {
    private Attribute secondAttribute;
    private int test = 1;

    @Override
    public void setValue(Object value) {
        if(value instanceof Attribute){
            this.secondAttribute = (Attribute) value;
        }
    }
}
