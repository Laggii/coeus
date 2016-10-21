package servlet;

import database.dao.jdbc.UserDaoImpl;
import exception.ConnectionPoolException;
import model.User;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import service.ErrorCode;
import service.InputValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static service.ErrorCode.*;
/**
 * Created by Alexeev on 29.09.2016.
 */

/**
 * LoginServlet handles user login requests and checks request parameters
 * If login is unsuccessful provides reason to user
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDaoImpl userDao;

    private final static Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Secure login page from logged users
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect("./main");
            return;
        }

        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        //Secure login page from logged users
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect("./main");
            return;
        }

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //Validate form parameters
        ErrorCode validationResult = InputValidator.validateLogin(email, password);
        if (validationResult != VALID) {
            printError(validationResult, request, response);
            return;
        }

        //Check if user exists and his password is correct
        try {
            userDao = new UserDaoImpl();
            User user = userDao.read(email);
            if (user != null && checkPassword(password, user.getHash())) {
                session.setAttribute("user", user);

                logger.info("User logged in: {" + user.getEmail() + "," + user.getFirstName() + "," + user.getLastName() + "}");

                response.sendRedirect("./main");
            } else {
                printError(USER_INCORRECT_ERROR, request, response);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Database error: " + e);
            printError(DATABASE_ERROR, request, response);
        }
    }

    /**
     * Print error message to the user
     *
     * @param errorCode
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void printError(ErrorCode errorCode, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errorMsg", errorCode);
        request.setAttribute("email", request.getParameter("email"));

        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    /**
     * Check that plaintext password matches provided hash
     *
     * @param password
     * @param hash
     * @return true if matches
     */
    private boolean checkPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
