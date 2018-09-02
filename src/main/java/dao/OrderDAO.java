package dao;

import model.Order;
import utils.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements DAO<Order, Integer>{
    Connection connection = new ConnectionFactory().getConnection();

    public OrderDAO() {
        init();
    }

    @Override
    public void init() {
        try {
            Statement st = connection.createStatement();
            st.execute(SQLOrder.INIT.QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Order order) {
        try (PreparedStatement ps = connection.prepareStatement(SQLOrder.ADD.QUERY)){
            ps.setInt(1,order.getProduct_id());
            ps.setInt(2,order.getCount());
            ps.setInt(3,order.getUser_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQLOrder.GET_ALL.QUERY);
            while (resultSet.next()){
                Order order = new Order();
                order.setId(resultSet.getInt(1));
                order.setProduct_id(resultSet.getInt(2));
                order.setCount(resultSet.getInt(3));
                order.setUser_id(resultSet.getInt(4));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement ps = connection.prepareStatement(SQLOrder.DELETE.QUERY)) {
            ps.setInt(1,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private enum SQLOrder {
        INIT("CREATE TABLE IF NOT EXISTS Orders (" +
                "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "product_id INT," +
                "count INT," +
                "user_id INT," +
                "FOREIGN KEY (product_id) REFERENCES Products(id)," +
                "FOREIGN KEY (user_id) REFERENCES Users (id))"),
        ADD("INSERT INTO Orders (product_id, count, user_id) VALUES (?,?,?)"),
        GET_ALL("SELECT * FROM Orders"),
        DELETE ("DELETE FROM Orders WHERE id = ?");

        String QUERY;

        SQLOrder(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
