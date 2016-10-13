package servlet;

import database.dao.jdbc.UserDaoImpl;
import exception.ConnectionPoolException;
import model.User;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import service.ErrorCode;
import service.FormValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import static service.ErrorCode.*;

/**
 * Created by Alexeev on 05.10.2016.
 */

/**
 * RegistrationServlet handles user registration requests and checks request parameters
 * If registration is unsuccessful provides reason to user
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private UserDaoImpl userDao;

    private final static Logger logger = Logger.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Secure registration page from logged users
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect("./main");
            return;
        }

        response.sendRedirect("./login");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        //Secure registration page from logged users
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect("./main");
            return;
        }

        String email = request.getParameter("email");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeat_password");
        String isTeacher = request.getParameter("teacher");
        int roleId;

        //Validate form parameters
        ErrorCode validationResult = FormValidator.validateRegistration(email, firstName, lastName, password, repeatPassword);
        if (validationResult != VALID) {
            printError(validationResult, request, response);
            return;
        }

        roleId = "on".equals(isTeacher) ? 2 : 1;

        User user = new User.Builder()
                .setEmail(email)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setHash(encryptPassword(password))
                .setRoleId(roleId)
                .build();

        //Register user if he doesn't exist in database
        try {
            userDao = new UserDaoImpl();
            if (userDao.isExists(user)) {
                printError(USER_EXISTS_ERROR, request, response);
            } else {
                userDao.create(user);
                session.setAttribute("user", user);
                logger.info("Successfully registered new user: {" + user.getEmail() + "," + user.getFirstName() + "," + user.getLastName() + "}");
                request.getRequestDispatcher("/main").forward(request, response);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Database error: " + e);
            printError(DATABASE_ERROR, request, response);
        }
    }

    private void printError(ErrorCode errorCode, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errorMsg", errorCode);
        //marker for js to open register tab
        request.setAttribute("isRegister", "true");

        //Save form parameters
        Enumeration params = request.getParameterNames();
        String param;
        while (params.hasMoreElements()) {
            param = (String) params.nextElement();
            request.setAttribute(param, request.getParameter(param));
        }

        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    /**
     * Encrypt password with salt
     *
     * @param password
     * @return hash
     */
    private String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
