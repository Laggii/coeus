package command.user;

import command.Command;
import database.dao.mysql.UserDaoImpl;
import exception.DaoException;
import model.User;
import org.apache.log4j.Logger;
import service.InputValidator;
import service.MessageProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static service.MessageProvider.PROFILE_CHANGE_SUCCESSFUL;
import static service.MessageProvider.USER_EXISTS_ERROR;
import static service.MessageProvider.VALID;

/**
 * Created by Alexeev on 23.10.2016.
 */

/**
 * EditProfile command processes user request to change profile information
 */
public class EditProfile extends Command {
    private static final Logger logger = Logger.getLogger(EditProfile.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        HttpSession session = request.getSession();
        UserDaoImpl userDao = new UserDaoImpl();

        User user = (User) session.getAttribute("user");
        String oldEmail = user.getEmail();

        String email = request.getParameter("email");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String sex = request.getParameter("sex");
        String phone = request.getParameter("phone");
        String birthDate = request.getParameter("birthdate");

        //validate form input
        MessageProvider validationResult = InputValidator.validateProfileChange(email, firstName, lastName, sex, phone, birthDate);
        if (validationResult != VALID) {
            request.setAttribute("errorMsg", validationResult);
        } else {
            user.setEmail(email);
            if (!email.equals(oldEmail) && userDao.isExists(user)) {
                user.setEmail(oldEmail);
                request.setAttribute("errorMsg", USER_EXISTS_ERROR);
            } else {
                //convert date to avoid sql error
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                java.util.Date parsedDate = new java.util.Date();
                try {
                    parsedDate = format.parse(birthDate);
                } catch (ParseException e) {
                    logger.error("Failed to parse date: ", e);
                }

                //update user in database and session
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setGender(sex.charAt(0));
                user.setPhone(phone);
                user.setBirthDate(new Date(parsedDate.getTime()));

                userDao.update(user);
                session.setAttribute("user", user);
                request.setAttribute("successMsg", PROFILE_CHANGE_SUCCESSFUL);
            }
        }
        return "/settings.jsp";
    }
}
