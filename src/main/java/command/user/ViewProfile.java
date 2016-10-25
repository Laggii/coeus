package command.user;

import command.Command;
import database.dao.interfaces.UserCoursesDao;
import database.dao.interfaces.UserFriendsDao;
import database.dao.mysql.UserCoursesDaoImpl;
import database.dao.mysql.UserDaoImpl;
import database.dao.mysql.UserFriendsDaoImpl;
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

    private User loggedUser;

    private UserDaoImpl userDao;

    private UserCoursesDao userCoursesDao;

    private UserFriendsDao userFriendsDao;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        HttpSession session = request.getSession();
        loggedUser = (User) session.getAttribute("user");

        userDao = new UserDaoImpl();
        userCoursesDao = new UserCoursesDaoImpl();
        userFriendsDao = new UserFriendsDaoImpl();

        String idParameter = request.getParameter("id");

        //print current user profile if no id specified
        if (idParameter == null) {
            return setInfo(request, loggedUser);
        }

        if (InputValidator.validateId(idParameter)) {
            User user = userDao.read(Long.parseLong(idParameter));

            if (user != null) {
                return setInfo(request, user);
            } else {
                request.setAttribute("errorMsg", USER_NOT_FOUND_ERROR);
            }
        } else {
            request.setAttribute("errorMsg", USER_ID_ERROR);
        }
        return "/main.jsp";
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
        Collection<Course> userCourses = userCoursesDao.getCourses(user);
        request.setAttribute("userCourses", userCourses);
        Collection<User> userFriends = userFriendsDao.getFriends(user);
        request.setAttribute("userFriends", userFriends);

        //check that current user is our friend and show delete button
        if (!user.equals(loggedUser)) {
            Collection<User> loggedUserFriends = userFriendsDao.getFriends(loggedUser);
            if (loggedUserFriends.contains(user)) {
                request.setAttribute("isFriend", "true");
            }
        }
        return "/profile.jsp";
    }
}
