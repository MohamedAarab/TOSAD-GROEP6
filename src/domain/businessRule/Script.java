package domain.businessRule;

import java.util.List;

/**
 * Created by Unknown on 01/18/2017.
 */
public abstract class Script {
    protected String name;
    protected String triggerCode;
    protected List<String> triggerEvent;
    protected BusinessRule businessRule;

    public Script(String name, List<String> triggerEvent, BusinessRule businessRule) {
        this.name = name;
        this.triggerEvent = triggerEvent;
        this.businessRule = businessRule;
    }

    public abstract void generate(); /*{
        businessRule.getBusinessRuleType().generate(businessRule.getDefinitions(), businessRule.getOperator());
    }
*/
}
