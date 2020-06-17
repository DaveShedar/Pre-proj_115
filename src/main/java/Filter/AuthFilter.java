package Filter;

import Servlets.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.nonNull;

@WebFilter("/")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        final HttpSession session = req.getSession();

        //Logged user.
        if (nonNull(session) && nonNull(session.getAttribute("name")) && nonNull(session.getAttribute("password"))) {
            String role = (String) session.getAttribute("role");
            moveToMenu(req, res, role);
        } else if (UserService.getUserService().isUserExist(name, password)) {
            String role = UserService.getUserService().getRoleByNamePassword(name, password);
            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("name", name);
            req.getSession().setAttribute("role", role);
            moveToMenu(req, res, role);

        } else {
            moveToMenu(req, res, "unknown");
        }
    }

    private void moveToMenu(HttpServletRequest req, HttpServletResponse res, String role) throws ServletException, IOException {
        if (role.equals("admin")) {
//            req.getRequestDispatcher("admin_menu.jsp").forward(req, res);
            res.sendRedirect(req.getContextPath() + "/admin_welcome");
        } else if (role.equals("user")) {
            res.sendRedirect(req.getContextPath() + "/user_welcome");
//            req.getRequestDispatcher("user_menu.jsp").forward(req, res);

        } else {
            req.getRequestDispatcher("login.jsp").forward(req, res);
        }
    }

    @Override
    public void destroy() {
    }
}