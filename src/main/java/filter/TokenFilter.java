package filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Alexeev on 10.10.2016.
 */

/**
 * TokenFilter provides protection from CSRF attack by setting token in session and validating it later with token in forms
 * Spring CSRF protection: http://docs.spring.io/spring-security/site/docs/current/reference/html/csrf.html
 */
@WebFilter(urlPatterns = {"/login", "/registration", "/logout", "/main"})
public class TokenFilter implements Filter {
    private final static Logger logger = Logger.getLogger(TokenFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(true);

        String newToken = UUID.randomUUID().toString();
        String oldToken = (String) session.getAttribute("token");
        String requestToken = request.getParameter("token");

        //set token for newly created session
        if (oldToken == null) {
            session.setAttribute("token", newToken);
            chain.doFilter(req, resp);
            return;
        }

        //All post requests without token in form will be blocked
        if (req.getMethod().equalsIgnoreCase("POST")) {
            if (requestToken != null && oldToken.equals(requestToken)) {
                chain.doFilter(req, resp);
            } else {
                logger.info("Blocking request with incorrect token from " + req.getRequestURI());
                request.getRequestDispatcher("/").forward(request, response);
            }
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
