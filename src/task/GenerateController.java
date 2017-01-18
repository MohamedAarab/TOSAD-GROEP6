package task;

import domain.businessRuleType.Category;
import domain.businessRuleType.IBusinessRuleType;

import java.util.List;

/**
 * Created by Unknown on 01/18/2017.
 */
public class GenerateController {

    private static IBusinessRuleType BusinessRuleTypeService = new IBusinessRuleType();

    public static List<Category> getAllCategories() {
        return BusinessRuleTypeService.getAllCategories();
    }
}
