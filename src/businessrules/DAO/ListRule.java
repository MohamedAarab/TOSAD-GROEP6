package businessrules.DAO;

import java.util.List;

/**
 * Created by lucas on 13-1-2017.
 */
public class ListRule extends BusinessRule {
    private List<String> list;

    public ListRule(List<String> list) {
        this.list = list;
    }

    @Override
    public void setValue(Object value) {
        if(list.getClass().isInstance(value)){
            this.list = (List<String>) value;
        }
    }
}
