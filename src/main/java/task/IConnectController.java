package task;

/**
 * Created by lucas on 25-1-2017.
 */
public interface IConnectController {
    String connectToDatabase(String databaseType, String host, String username, String password);
}
