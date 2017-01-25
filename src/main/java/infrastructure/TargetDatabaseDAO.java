package infrastructure;

import domain.targetDatabase.Attribute;
import domain.targetDatabase.Scheme;
import domain.targetDatabase.Table;
import domain.targetDatabase.TargetDatabase;
import org.w3c.dom.Attr;

import javax.smartcardio.ATR;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 25-1-2017.
 */
public class TargetDatabaseDAO extends BaseDAO {
    public TargetDatabaseDAO(String type, String targetUsername, String targetPassword, String targetUrl) {
        super(type, targetUsername, targetPassword, targetUrl);
    }

    public TargetDatabase createTargetDatabase(){
        TargetDatabase targetDatabase = new TargetDatabase(getDatabaseType(), getTargetUrl(), getTargetUsername(), getTargetPassword());
        for(Scheme scheme : getSchemes()){
            targetDatabase.addScheme(scheme);
            for(Table table : getTables(scheme.getName())){
                scheme.addTable(table);
                for(Attribute attribute : getAttributes(scheme.getName(), table.getName())){
                    table.addAttribute(attribute);
                }
            }
        }
        //// TODO: 25-1-2017
        return targetDatabase;
    }

    public List<Scheme> getSchemes(){
        List<Scheme> schemes = new ArrayList<Scheme>();
        try(Connection conn = super.getConnection()){
            ResultSet result = conn.getMetaData().getCatalogs();
            while(result.next()){
                schemes.add(new Scheme(result.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schemes;
    }

    public List<Table> getTables(String schemeName){
        List<Table> tables = new ArrayList<Table>();
        try(Connection connection = super.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA='" + schemeName + "'");
            while(resultSet.next()){
                tables.add(new Table(resultSet.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;
    }

    public List<Attribute> getAttributes(String schemeName, String tableName){
        List<Attribute> attributes = new ArrayList<Attribute>();
        try(Connection connection = super.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("DESCRIBE " + schemeName + "." + tableName);
            while (resultSet.next()){
                attributes.add(new Attribute(resultSet.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attributes;
    }
}
