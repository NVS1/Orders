package servlets;

import dao.DbService;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UsersServlet", urlPatterns = "/users")
public class UsersServlet extends HttpServlet {
    private final DbService dbService = DbService.getInstance();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()){
            response.getWriter().print("Fields not filled");
        }else if (dbService.isPresentUser(phone)){
            response.getWriter().print("This phone number exist");
        } else {
            User newUser = new User(name,email,phone);
            dbService.addUser(newUser);
            response.getWriter().write("User has been added");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = dbService.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("users.jsp").forward(request,response);
    }
}
