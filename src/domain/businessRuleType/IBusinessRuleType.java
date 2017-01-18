package domain.businessRuleType;

import infrastructure.IDAO;

import java.util.List;

/**
 * Created by Unknown on 01/18/2017.
 */
public class IBusinessRuleType {

    private IDAO InterfaceDAO = new IDAO();

    public List<Category> getAllCategories() {
        return InterfaceDAO.getAllCategories();
    }
}
