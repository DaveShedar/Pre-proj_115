package Servlets;

import Service.UserService;
import User.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "/admin", value = "/admin")
public class UserListServlet extends HttpServlet {
    private final UserService userService = UserService.getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List< User > listUsers = userService.getAllUsers();
        req.setAttribute("listUsers", listUsers);
        req.getRequestDispatcher("user-list.jsp").forward(req, resp);
    }
}
