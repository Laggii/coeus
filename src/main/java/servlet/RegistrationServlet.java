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
import java.util.Enumeration;

import static service.MessageProvider.*;
import static utils.Encryption.encryptPassword;

/**
 * Created by Alexeev on 05.10.2016.
 */

/**
 * RegistrationServlet handles user registration requests and checks request parameters
 * If registration is unsuccessful provides reason to user
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final static Logger logger = Logger.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Secure registration page from logged users
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect("./main?action=profile");
            return;
        }

        response.sendRedirect("./login");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        //Secure registration page from logged users
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect("./main?action=profile");
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
        MessageProvider validationResult = InputValidator.validateRegistration(email, firstName, lastName, password, repeatPassword);
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
            UserDaoImpl userDao = new UserDaoImpl();
            if (userDao.isExists(user)) {
                printError(USER_EXISTS_ERROR, request, response);
            } else {
                userDao.create(user);
                user.setUserId(userDao.getId(email));

                session.setAttribute("user", user);
                logger.info("Successfully registered new user: {" + user.getEmail() + "," + user.getFirstName() + "," + user.getLastName() + "}");
                response.sendRedirect("./main?action=profile");
            }
        } catch (DaoException e) {
            logger.error("Database error: " + e);
            printError(DATABASE_ERROR, request, response);
        }
    }

    private void printError(MessageProvider errorMsg, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errorMsg", errorMsg);
        //marker to open registration tab
        request.setAttribute("isRegister", "true");

        //Save form parameters
        Enumeration params = request.getParameterNames();
        String param;
        while (params.hasMoreElements()) {
            param = (String) params.nextElement();
            //do not save token
            if (!"token".equals(param)) {
                request.setAttribute(param, request.getParameter(param));
            }
        }

        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
