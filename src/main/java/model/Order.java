package model;

public class Order {
    private int id;
    private int product_id;
    private int count;
    private int user_id;
    private User user;
    private Product product;

    public Order(int count, User user, Product product) {
        this.count = count;
        this.user = user;
        this.product = product;
    }

    public Order() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getCount() {
        return count;
    }
    public int getUser_id() {
        return user_id;
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
