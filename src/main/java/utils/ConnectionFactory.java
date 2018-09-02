package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private final String url;
    private final String user;
    private final String password;

    public ConnectionFactory() {
        DbProperties dbProperties = new DbProperties();
        this.url=dbProperties.getUrl();
        this.user = dbProperties.getUser();
        this.password=dbProperties.getPassword();
    }
    public Connection getConnection (){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
