package infrastructure;

import domain.businessRuleType.Category;


import java.util.List;

/**
 * Created by Gebruiker on 18-1-2017.
 */
public class IDAO {

    private TargetConnection targetConnection = new TargetConnection();
    private ToolConnection toolConnection = new ToolConnection();

    private CategoryDAO categoryDAO = new CategoryDAO(toolConnection.getConnection());
//    private BusinessRuleDAO businessRuleDAO = new BusinessRuleDAO(toolConnection.getConnection());

    public void setTargetUsername(String username) {
        targetConnection.setTargetUsername(username);
    }

    public void setTargetUrl(String url) {
        targetConnection.setTargetUrl(url);
    }

    public void setTargetPassword(String password) {
        targetConnection.setTargetPassword(password);
    }

    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

//    public BusinessRuleDAO getBusinessRuleDAO() {
////        return new BusinessRuleDAO();
//    }
}
