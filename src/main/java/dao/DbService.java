package dao;

import model.Order;
import model.Product;
import model.User;

import java.util.List;

public class DbService {
    private final DAOextend<User,Integer, String> userDAO = new UserDAO();
    private final DAOextend<Product,Integer,String> productDAO = new ProductDAO();
    private final DAO<Order,Integer> orderDAO = new OrderDAO();
    private static final DbService dbservice = new DbService();

    private DbService() {
    }

    public static DbService getInstance (){
        return dbservice;
    }
    public synchronized void addUser (User user){
        userDAO.add(user);
    }
    public synchronized List<User> getAllUsers(){
        return userDAO.getAll();
    }
    public synchronized void deleteUser (int id){
        userDAO.delete(Integer.valueOf(id));
    }
    public synchronized boolean isPresentUser (String phone){
        return userDAO.isPresent(phone);
    }
    public synchronized User getUser (String phone){
        return userDAO.get(phone);
    }
    public synchronized User getUser (int id){
        return userDAO.getById(Integer.valueOf(id));
    }
    public synchronized void addProduct (Product product){
        productDAO.add(product);
    }
    public synchronized List<Product> getAllProducts(){
        return productDAO.getAll();
    }
    public synchronized void deleteProduct (int id){
        productDAO.delete(Integer.valueOf(id));
    }
    public synchronized boolean isPresentProduct (String name){
        return productDAO.isPresent(name);
    }
    public synchronized Product getProduct (String name){
        return productDAO.get(name);
    }
    public synchronized Product getProduct (int id){
        return productDAO.getById(Integer.valueOf(id));
    }
    public synchronized void addOrder (Order order){
        orderDAO.add(order);
    }
    public synchronized List<Order> getAllOrders(){
        return orderDAO.getAll();
    }
    public synchronized void deleteOrder (int id){
        orderDAO.delete(Integer.valueOf(id));
    }
}
