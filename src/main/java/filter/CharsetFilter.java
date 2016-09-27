package filter;

import database.connectionpool.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by Alexeev on 26.09.2016.
 */
@WebFilter(filterName = "CharsetFilter", urlPatterns = "/*")
public class CharsetFilter implements Filter {

    private static final Logger logger = Logger.getLogger(ConnectionPool.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        logger.info("Charset was set to utf-8");

        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
    }
}
