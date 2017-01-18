package infrastructure;

import domain.businessRuleType.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gebruiker on 18-1-2017.
 */
public class CategoryDAO extends BaseDAO {

    public CategoryDAO(Connection connection) {
        super.setConnection(connection);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<Category>();
        try(Connection conn = super.getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM CATEGORIES");
            while(result.next()){
                categoryList.add(new Category(result.getString("name")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return categoryList;
    }
}
