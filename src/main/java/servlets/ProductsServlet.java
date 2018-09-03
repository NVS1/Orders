package servlets;

import dao.DbService;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductsServlet", urlPatterns = "/products")
public class ProductsServlet extends HttpServlet {
    private final DbService dbService = DbService.getInstance();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        if (name.isEmpty() || price.isEmpty()){
            response.getWriter().print("Fields not filled");
        }else if (dbService.isPresentProduct(name)){
            response.getWriter().print("this product exist");
        } else {
            Product product = new Product(name,Integer.parseInt(price));
            dbService.addProduct(product);
            response.getWriter().print("product has been added");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products", dbService.getAllProducts());
        request.getRequestDispatcher("products.jsp").forward(request,response);
    }
}
