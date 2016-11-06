package action.user;

import action.Action;
import database.dao.interfaces.UserFriendsDao;
import database.dao.mysql.UserDaoImpl;
import database.dao.mysql.UserFriendsDaoImpl;
import exception.DaoException;
import model.User;
import service.InputValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Collection;

import static service.MessageProvider.*;

/**
 * Created by Alexeev on 24.10.2016.
 */

/**
 * AddFriend command processes user request to add another user in friends list
 */
public class AddFriend extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        UserFriendsDao userFriendsDao = new UserFriendsDaoImpl();
        UserDaoImpl userDao = new UserDaoImpl();

        String idParameter = request.getParameter("id");

        //validate id from request
        if (InputValidator.validateId(idParameter)) {
            long friendId = Long.parseLong(idParameter);
            User friend = userDao.read(friendId);
            Collection<User> friends = userFriendsDao.getFriends(user);

            if (friend != null && user.getUserId() != friendId) {
                if (!friends.contains(friend)) {
                    userFriendsDao.addFriend(user, friend);
                    request.setAttribute("successMsg", USER_ADD_FRIEND_SUCCESS);
                } else {
                    request.setAttribute("errorMsg", USER_IS_FRIEND_ERROR);
                }
                return "/main?action=profile&id=" + friendId;
            } else {
                request.setAttribute("errorMsg", USER_NOT_FOUND_ERROR);
                return "/main.jsp";
            }
        } else {
            request.setAttribute("errorMsg", USER_ID_ERROR);
            return "/main.jsp";
        }
    }
}
