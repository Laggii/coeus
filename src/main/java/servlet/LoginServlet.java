package servlet;

import database.dao.mysql.UserDaoImpl;
import exception.DaoException;
import model.User;
import org.apache.log4j.Logger;
import service.MessageProvider;
import service.InputValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static service.MessageProvider.*;
import static utils.Encryption.checkPassword;
/**
 * Created by Alexeev on 29.09.2016.
 */

/**
 * LoginServlet handles user login requests and checks request parameters
 * If login is unsuccessful provides reason to user
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Secure login page from logged users
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect("./main?action=profile");
            return;
        }

        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        //Secure login page from logged users
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect("./main?action=profile");
            return;
        }

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //Validate form parameters
        MessageProvider validationResult = InputValidator.validateLogin(email, password);
        if (validationResult != VALID) {
            printError(validationResult, request, response);
            return;
        }

        //Check if user exists and his password is correct
        try {
            UserDaoImpl userDao = new UserDaoImpl();
            User user = userDao.read(email);
            if (user != null && checkPassword(password, user.getHash())) {
                session.setAttribute("user", user);

                logger.info("User logged in: {" + user.getEmail() + "," + user.getFirstName() + "," + user.getLastName() + "}");

                response.sendRedirect("./main?action=profile");
            } else {
                printError(USER_INCORRECT_ERROR, request, response);
            }
        } catch (DaoException e) {
            logger.error("Database error: " + e);
            printError(DATABASE_ERROR, request, response);
        }
    }

    /**
     * Print error message to the user
     *
     * @param errorMsg
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void printError(MessageProvider errorMsg, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errorMsg", errorMsg);
        request.setAttribute("email", request.getParameter("email"));

        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
