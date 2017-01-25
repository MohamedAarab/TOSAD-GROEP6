package infrastructure;

import domain.targetDatabase.TargetDatabase;

/**
 * Created by lucas on 25-1-2017.
 */
public class TargetDatabaseDAO extends BaseDAO {
    public TargetDatabaseDAO(String targetUsername, String targetPassword, String targetUrl) {
        super(targetUsername, targetPassword, targetUrl);
    }

    public TargetDatabase createTargetDatabase(){
        TargetDatabase targetDatabase = null;
        //// TODO: 25-1-2017
        return targetDatabase;
    }
}
