package filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Alexeev on 05.10.2016.
 */

/**
 * Login filter provides functionality for blocking unauthorized requests to server pages and resources.
 * Grants access if user is logged in or requested allowed paths
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    private static final String[] ALLOWED_PATHS = {"/login", "/registration", "/error", "/locale",
            "/css", "/fonts", "/js"};

    private static final Logger logger = Logger.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String context = req.getContextPath();
        String uri = req.getRequestURI();

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        boolean isAllowedRequest = checkPath(uri.substring(context.length()));

        if (isLoggedIn || isAllowedRequest) {
            chain.doFilter(req, resp);
        } else {
            logger.info("Blocking unauthorized access to " + uri);
            resp.sendRedirect(context + "/login");
        }
    }

    /**
     * Check if requested URI is in allowed paths
     *
     * @param uri
     * @return
     */
    private boolean checkPath(String uri) {
        for (String path : ALLOWED_PATHS) {
            if ("/".equals(uri) || path.equals(uri) || uri.startsWith(path)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {
    }
}
