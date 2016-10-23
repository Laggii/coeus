package command.user;

import command.Command;
import database.dao.mysql.UserDaoImpl;
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
        UserDaoImpl userDao = new UserDaoImpl();
        Collection<User> userFriends = userDao.getFriends((User) session.getAttribute("user"));
        request.setAttribute("userFriends", userFriends);
        return "friends.jsp";
    }
}
