package dao;

import model.Product;
import utils.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements DAOextend<Product, Integer, String> {
    Connection connection = new ConnectionFactory().getConnection();

    public ProductDAO() {
       init();
    }

    @Override
    public void init() {
        try {
            Statement st = connection.createStatement();
            st.execute(SQLProduct.INIT.QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Product product) {
        try (PreparedStatement ps = connection.prepareStatement(SQLProduct.ADD.QUERY)){
            ps.setString(1,product.getName());
            ps.setInt(2, product.getPrice());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
       List<Product> products = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(SQLProduct.GET_ALL.QUERY);
            while (resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setPrice(resultSet.getInt(3));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement ps = connection.prepareStatement(SQLProduct.DELETE.QUERY)) {
            ps.setInt(1,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isPresent(String name) {
        try (PreparedStatement ps = connection.prepareStatement(SQLProduct.GET_PRODUCT_BY_NAME.QUERY)) {
            ps.setString(1, name);
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
    public Product get(String name) {
        try (PreparedStatement ps = connection.prepareStatement(SQLProduct.GET_PRODUCT_BY_NAME.QUERY)){
            ps.setString(1,name);
            ResultSet resultSet = ps.executeQuery();
            Product product = createProduct(resultSet);
            if (product != null)
                return product;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product getById(Integer id) {
        try (PreparedStatement ps = connection.prepareStatement(SQLProduct.GET_PRODUCT_BY_ID.QUERY)){
            ps.setInt(1,id.intValue());
            ResultSet resultSet = ps.executeQuery();
            Product product = createProduct(resultSet);
            if (product != null)
                return product;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Product createProduct(ResultSet resultSet) throws SQLException {
        if (resultSet.next()){
            Product product = new Product();
            product.setId(resultSet.getInt(1));
            product.setName(resultSet.getString(2));
            product.setPrice(resultSet.getInt(3));
            return product;
        }
        return null;
    }

    private enum SQLProduct {
        INIT("CREATE TABLE IF NOT EXISTS Products (" +
                "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(128)," +
                "price INT)"),
        ADD("INSERT INTO Products (name, price) VALUES (?,?)"),
        GET_ALL("SELECT * FROM Products"),
        DELETE ("DELETE FROM Products WHERE id = ?"),
        GET_PRODUCT_BY_ID("SELECT * FROM Products WHERE id = ?"),
        GET_PRODUCT_BY_NAME("SELECT * FROM Products WHERE name = ?");

        String QUERY;

        SQLProduct(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
