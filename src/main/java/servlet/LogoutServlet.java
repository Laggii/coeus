package servlet;

import model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Alexeev on 09.10.2016.
 */

/**
 * LogoutServlet handles simple logout request
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LogoutServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logoutUser(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logoutUser(request, response);
    }

    /**
     * Invalidate user session and log his logout event
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = "unknown";
        String userFirstName = "unknown";
        String userLastName = "unknown";

        HttpSession session = request.getSession();
        if (session != null) {
            User user = (User) session.getAttribute("user");

            if (user != null) {
                userEmail = user.getEmail();
                userFirstName = user.getLastName();
                userLastName = user.getLastName();
            }

            session.invalidate();
        }

        logger.info("User logged out: {" + userEmail + "," + userFirstName + "," + userLastName + "}");
        response.sendRedirect("./login");
    }
}
