package action.user;

import action.Action;
import database.dao.mysql.UserDaoImpl;
import exception.DaoException;
import model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * Created by Alexeev on 17.10.2016.
 */

/**
 * ViewUsers action processes user request to see all users on site
 */
public class ViewUsers extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        UserDaoImpl userDao = new UserDaoImpl();
        Collection<User> users = userDao.getAll();
        request.setAttribute("users", users);
        return "/users.jsp";
    }
}
