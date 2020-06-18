package Filter;

import Service.UserService;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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

        if (nonNull(session) && nonNull(session.getAttribute("name")) && nonNull(session.getAttribute("password"))) {
            String role = (String) session.getAttribute("role");
            moveToMenu(req, res, role);
        } else if (UserService.getUserService().isUserExist(name, password)) {
            String role = UserService.getUserService().getRoleByNamePassword(name, password);
            session.setAttribute("password", password);
            session.setAttribute("name", name);
            session.setAttribute("role", role);
            moveToMenu(req, res, role);
        } else {
            moveToMenu(req, res, "unknown");
        }
    }

    private void moveToMenu(HttpServletRequest req, HttpServletResponse res, String role) throws ServletException, IOException {
        if (role.equals("admin")) {
            res.sendRedirect("/admin");
        } else if (role.equals("user")) {
            res.sendRedirect("/user");
        } else {
            req.getRequestDispatcher("login.jsp").forward(req, res);
        }
    }

    @Override
    public void destroy() {
    }
}
