package domain.targetDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 13-1-2017.
 */
public class TargetDatabase {
    private int port;
    private String name;
    private String type;
    private String host;
    private String username;
    private String password;
    private List<Scheme> schemes;

    public TargetDatabase(String type, String host, int port, String name, String username, String password) {
        this.type = type;
        this.host = host;
        this.port = port;
        this.name = name;
        this.username = username;
        this.password = password;
        this.schemes = new ArrayList<Scheme>();
    }

    public List<Scheme> getSchemes() {
        return schemes;
    }

    public String getHost() {
        return host;
    }

    public Scheme getSchemeByName(String schemeName) {
        Scheme scheme = null;
        for (Scheme s : schemes) {
            if (s.getName().equals(schemeName)) {
                scheme = s;
                break;
            }
        }
        return scheme;
    }

    public void addScheme(Scheme scheme) {
        schemes.add(scheme);
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getPort() {
        return port;
    }
}
