package infrastructure.dao;

import domain.businessRule.StringTemplate;
import domain.targetDatabase.Attribute;
import domain.targetDatabase.Scheme;
import domain.targetDatabase.Table;
import domain.targetDatabase.TargetDatabase;
import infrastructure.syntax.SyntaxManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 25-1-2017.
 */
public class TargetDatabaseDAO extends BaseDAO {
    private SyntaxManager syntaxManager = SyntaxManager.getInstance();

    public TargetDatabaseDAO(String databaseType, String targetUsername, String targetPassword, String targetUrl, int port, String databaseName) {
        super(databaseType, targetUsername, targetPassword, targetUrl, databaseName, port);
    }

    public TargetDatabase createTargetDatabase() {
        TargetDatabase targetDatabase = new TargetDatabase(getDatabaseType(), getTargetUrl(), getPort(), getDatabaseName(), getTargetUsername(), getTargetPassword());
        for (Scheme scheme : getSchemes()) {
            targetDatabase.addScheme(scheme);
            for (Table table : getTables(scheme.getName())) {
                scheme.addTable(table);
                for (Attribute attribute : getAttributes(scheme.getName(), table.getName())) {
                    table.addAttribute(attribute);
                }
            }
        }
        return targetDatabase;
    }

    public List<Scheme> getSchemes() {
        List<Scheme> schemes = new ArrayList<Scheme>();
        try (Connection conn = super.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(syntaxManager.getSchemesTemplate(getDatabaseType()));
            while (result.next()) {
                schemes.add(new Scheme(result.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schemes;
    }

    public List<Table> getTables(String schemeName) {
        List<Table> tables = new ArrayList<Table>();
        try (Connection connection = super.getConnection()) {
            Statement stmt = connection.createStatement();
            StringTemplate query = syntaxManager.getTablesTemplate(getDatabaseType());
            query.setAttribute("schemeName", schemeName);
            ResultSet resultSet = stmt.executeQuery(query.toString());
            while (resultSet.next()) {
                tables.add(new Table(resultSet.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;
    }

    public List<Attribute> getAttributes(String schemeName, String tableName) {
        List<Attribute> attributes = new ArrayList<Attribute>();
        try (Connection connection = super.getConnection()) {
            Statement stmt = connection.createStatement();
            StringTemplate query = syntaxManager.getAttributesTemplate(getDatabaseType());
            query.setAttribute("schemeName", schemeName);
            query.setAttribute("tableName", tableName);
            ResultSet resultSet = stmt.executeQuery(query.toString());
            while (resultSet.next()) {
                attributes.add(new Attribute(resultSet.getString(1), syntaxManager.getDataType(getDatabaseType(), resultSet.getString(2))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attributes;
    }

    public String executeScript(String script) {
        String result = null;
        System.out.println(script);
        try (Connection connection = super.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.execute(script);
            result = "succes";
        } catch (SQLException e) {
            result = e.getMessage();
        }
        return result;
    }

    public boolean checkConnection() {
        boolean result = false;
        try (Connection connection = super.getConnection()) {
            result = true;
        } catch (SQLException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }
}
