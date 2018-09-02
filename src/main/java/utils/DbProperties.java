package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbProperties {
    private final String url;
    private final String user;
    private final String password;

    public DbProperties() {
        InputStream resource = getClass().getClassLoader().getResourceAsStream("db.properties");
        Properties p = new Properties();
        try {
            p.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.url = p.getProperty("db.url");
        this.user = p.getProperty("db.user");
        this.password = p.getProperty("db.password");
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
