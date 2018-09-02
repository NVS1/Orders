package dao;

import model.User;
import utils.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAOextend<User, Integer, String> {

    Connection connection = new ConnectionFactory().getConnection();

    public UserDAO() {
        init();
    }

    @Override
    public boolean isPresent(String phone) {
        try (PreparedStatement ps = connection.prepareStatement(SQLUser.GET_USER_BY_PHONE.QUERY)) {
            ps.setString(1, phone);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void init() {
        try {
            Statement st = connection.createStatement();
            st.execute(SQLUser.INIT.QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(User user) {
        try (PreparedStatement ps = connection.prepareStatement(SQLUser.ADD.QUERY)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQLUser.GET_ALL.QUERY);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                user.setPhone(resultSet.getString(4));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement ps = connection.prepareStatement(SQLUser.DELETE.QUERY)) {
            ps.setInt(1, id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User get(String phone) {
        try (PreparedStatement ps = connection.prepareStatement(SQLUser.GET_USER_BY_PHONE.QUERY)) {
            ps.setString(1, phone);
            ResultSet resultSet = ps.executeQuery();
            User user = createUser(resultSet);
            if (user != null)
                return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getById(Integer id) {
        try (PreparedStatement ps = connection.prepareStatement(SQLUser.GET_USER_BY_ID.QUERY)) {
            ps.setInt(1, id.intValue());
            ResultSet resultSet = ps.executeQuery();
            User user = createUser(resultSet);
            if (user != null)
                return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setEmail(resultSet.getString(3));
            user.setPhone(resultSet.getString(4));
            return user;
        }
        return null;
    }

    private enum SQLUser {

        INIT("CREATE TABLE IF NOT EXISTS Users (" +
                "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(128)," +
                "email VARCHAR(128)," +
                "phone VARCHAR(128) NOT NULL)"),
        ADD("INSERT INTO Users (name, email, phone) VALUES (?,?,?)"),
        GET_ALL("SELECT * FROM Users"),
        DELETE("DELETE FROM Users WHERE id = ?"),
        GET_USER_BY_PHONE("SELECT * FROM Users WHERE phone = ?"),
        GET_USER_BY_ID("SELECT * FROM Users WHERE id = ?");

        String QUERY;

        SQLUser(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
