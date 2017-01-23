package domain.targetDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 13-1-2017.
 */
public class TargetDatabase {
    private String type;
    private String host;
    private String username;
    private String password;
    private List<Scheme> schemes;

    public TargetDatabase(String type, String host, String username, String password) {
        this.type = type;
        this.host = host;
        this.username = username;
        this.password = password;
        this.schemes = new ArrayList<Scheme>();
    }

    public List<Scheme> getSchemes(){
        return schemes;
    }
}
