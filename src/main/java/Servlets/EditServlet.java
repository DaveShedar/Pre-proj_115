package Servlets;

import Service.UserService;
import User.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin_edit")
public class EditServlet extends HttpServlet {
    private final UserService userService = UserService.getUserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User existingUser = userService.getUserById(id);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("user-form.jsp");
        req.setAttribute("user", existingUser);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            String role = req.getParameter("role");
            User updateUser = new User(id, name, password, role);
            userService.updateUser(updateUser);
            resp.sendRedirect("/admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
