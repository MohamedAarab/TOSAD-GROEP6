package task;

/**
 * Created by lucas on 25-1-2017.
 */
public interface IConnectController {
    String connectToDatabase(String host, int port, String databaseName, String databaseType, String username, String password);
}
