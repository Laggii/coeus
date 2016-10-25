package command.user;

import command.Command;
import database.dao.interfaces.UserFriendsDao;
import database.dao.mysql.UserDaoImpl;
import database.dao.mysql.UserFriendsDaoImpl;
import exception.DaoException;
import model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

/**
 * Created by Alexeev on 22.10.2016.
 */

/**
 * ViewFriends command processes user request to see all user friends
 */
public class ViewFriends extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        HttpSession session = request.getSession();
        UserFriendsDao userFriendsDao = new UserFriendsDaoImpl();
        Collection<User> userFriends = userFriendsDao.getFriends((User) session.getAttribute("user"));
        request.setAttribute("userFriends", userFriends);
        return "/friends.jsp";
    }
}
