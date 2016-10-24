package command.user;

import command.Command;
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

import static service.MessageProvider.USER_DEL_FRIEND_SUCCESS;
import static service.MessageProvider.USER_ID_ERROR;
import static service.MessageProvider.USER_NOT_FRIEND_ERROR;

/**
 * Created by Alexeev on 24.10.2016.
 */

/**
 * DeleteFriend command processes user request to delete user's friend
 */
public class DeleteFriend extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        UserFriendsDao userFriendsDao = new UserFriendsDaoImpl();
        UserDaoImpl userDao = new UserDaoImpl();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String idParameter = request.getParameter("id");

        if (InputValidator.validateId(idParameter)) {
            long friendId = Long.parseLong(idParameter);
            User friend = userDao.read(friendId);
            Collection<User> friends = userFriendsDao.getFriends(user);

            if (friends.contains(friend)) {
                userFriendsDao.delFriend(user, friend);
                request.setAttribute("successMsg", USER_DEL_FRIEND_SUCCESS);
            } else {
                request.setAttribute("errorMsg", USER_NOT_FRIEND_ERROR);
            }
            return "./main?action=profile&id=" + friendId;
        } else {
            request.setAttribute("errorMsg", USER_ID_ERROR);
            return "/main.jsp";
        }
    }
}
