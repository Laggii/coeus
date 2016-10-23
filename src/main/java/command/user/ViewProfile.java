package command.user;

import command.Command;
import database.dao.mysql.UserDaoImpl;
import exception.DaoException;
import model.Course;
import model.User;
import service.InputValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Collection;

import static service.MessageProvider.*;

/**
 * Created by Alexeev on 19.10.2016.
 */

/**
 * ViewProfile command processes user request to see user profile
 */
public class ViewProfile extends Command {

    private UserDaoImpl userDao;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        userDao = new UserDaoImpl();
        User user;

        String idParameter = request.getParameter("id");

        //print current user profile if no id specified
        if (idParameter == null) {
            HttpSession session = request.getSession();
            user = (User) session.getAttribute("user");
            return setInfo(request, user);
        }

        if (InputValidator.validateId(idParameter)) {
            user = userDao.read(Long.parseLong(idParameter));

            if (user != null) {
                return setInfo(request, user);
            } else {
                request.setAttribute("errorMsg", USER_NOT_FOUND_ERROR);
                return "/main.jsp";
            }
        } else {
            request.setAttribute("errorMsg", USER_ID_ERROR);
            return "/main.jsp";
        }
    }

    /**
     * Set information to request and return profile page where information should be printed
     *
     * @param request
     * @param user
     * @return profile page
     * @throws DaoException
     */
    private String setInfo(HttpServletRequest request, User user) throws DaoException {
        request.setAttribute("userProfile", user);
        Collection<Course> userCourses = userDao.getCourses(user);
        request.setAttribute("userCourses", userCourses);
        Collection<User> userFriends = userDao.getFriends(user);
        request.setAttribute("userFriends", userFriends);
        return "/profile.jsp";
    }
}
