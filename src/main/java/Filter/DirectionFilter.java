package Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter({"/admin*", "/admin/*", "/admin_welcome"})
public class DirectionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Вызов DirectionFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

//        String name = req.getParameter("name");
//        String password = req.getParameter("password");
//
//        HttpSession session = req.getSession();

        //Logged user.
        if (req.getSession().getAttribute("role").equals("admin")) {
            filterChain.doFilter(req, res);
        } else if (req.getSession().getAttribute("role").equals("user")) {
            RequestDispatcher rd = req.getRequestDispatcher("user_menu.jsp");
            rd.forward(req, res);
        } else {
            req.getRequestDispatcher("login.jsp").forward(req, res);
        }

    }

    @Override
    public void destroy() {
    }
}
