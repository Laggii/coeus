package action.user;

import action.Action;
import database.dao.mysql.UserDaoImpl;
import exception.DaoException;
import model.User;
import service.MessageProvider;
import service.InputValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static service.MessageProvider.OLD_PASSWORD_INVALID_ERROR;
import static service.MessageProvider.PASSWORD_CHANGE_SUCCESSFUL;
import static service.MessageProvider.VALID;
import static utils.Encryption.checkPassword;
import static utils.Encryption.encryptPassword;

/**
 * Created by Alexeev on 23.10.2016.
 */

/**
 * ChangePassword command processes user password change request
 */
public class ChangePassword extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        HttpSession session = request.getSession();
        UserDaoImpl userDao = new UserDaoImpl();

        User user = (User) session.getAttribute("user");

        String oldPassword = request.getParameter("old_password");
        String newPassword = request.getParameter("new_password");
        String repeatPassword = request.getParameter("repeat_password");

        String oldHash = user.getHash();
        String newHash;

        //validate form input
        MessageProvider validationResult = InputValidator.validatePasswordChange(oldPassword, newPassword, repeatPassword);
        if (validationResult != VALID) {
            request.setAttribute("errorMsg", validationResult);
            return "/settings.jsp";
        }

        //validate old password is correct
        if (!checkPassword(oldPassword, oldHash)) {
            request.setAttribute("errorMsg", OLD_PASSWORD_INVALID_ERROR);
        } else {
            //change password and update user in session
            newHash = encryptPassword(newPassword);
            user.setHash(newHash);
            userDao.update(user);
            session.setAttribute("user", user);

            request.setAttribute("successMsg", PASSWORD_CHANGE_SUCCESSFUL);
        }
        return "/settings.jsp";
    }
}
