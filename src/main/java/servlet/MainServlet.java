package servlet;

import action.Action;
import action.ActionFactory;
import exception.ActionException;
import exception.DaoException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static service.MessageProvider.DATABASE_ERROR;
import static service.MessageProvider.UNKNOWN_ACTION;

/**
 * Created by Alexeev on 09.10.2016.
 */

/**
 * Main servlet processes all user "action" commands and returns page user will load
 * implemented as a FrontController
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(MainServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandName = request.getParameter("action");
        String path = "/profile.jsp";

        if (commandName != null) {
            try {
                Action action = ActionFactory.getInstance().getAction(commandName);
                path = action.execute(request, response);
            } catch (ActionException e) {
                logger.error("Failed to process user action request:", e);
                request.setAttribute("errorMsg", UNKNOWN_ACTION);
                path = "/main.jsp";
            } catch (DaoException e) {
                logger.error("Database error:", e);
                request.setAttribute("errorMsg", DATABASE_ERROR);
                path = "/main.jsp";
            }
        }
        redirect(request, response, path);
    }

    /**
     * Redirect user to page
     *
     * @param request
     * @param response
     * @param path     page
     * @throws ServletException
     * @throws IOException
     */
    private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }
}
