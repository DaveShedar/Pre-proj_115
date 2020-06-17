package Servlets;

import User.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin_newForm")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("вызов сервлета AddUserServlet");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("user-form.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            String role = req.getParameter("role");
            User user = new User(name, password, role);
            resp.sendRedirect(req.getContextPath() + "/admin");
            UserService.getUserService().addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
