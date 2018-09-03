package servlets;

import dao.DbService;
import model.Order;
import model.Product;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrdersServlet", urlPatterns = "/orders")
public class OrdersServlet extends HttpServlet {
    private final DbService dbService = DbService.getInstance();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        String name = request.getParameter("name");
        String count = request.getParameter("count");

        if (dbService.isPresentUser(phone) && dbService.isPresentProduct(name)){
            Product product = dbService.getProduct(name);
            User user = dbService.getUser(phone);
            Order order = new Order();
            order.setProduct(product);
            order.setUser(user);
            order.setCount(Integer.parseInt(count));
            order.setProduct_id(product.getId());
            order.setUser_id(user.getId());
            dbService.addOrder(order);
            response.getWriter().print("Order has been added");
        }else {
            response.getWriter().print("This product or user not exist");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = dbService.getAllOrders();
        for (Order order : orders) {
            order.setProduct(dbService.getProduct(order.getProduct_id()));
            order.setUser(dbService.getUser(order.getUser_id()));
        }
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("orders.jsp").forward(request, response);
    }
}
