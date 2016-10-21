package command.user;

import command.Command;
import database.dao.jdbc.UserDaoImpl;
import exception.CommandException;
import exception.ConnectionPoolException;
import model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Alexeev on 17.10.2016.
 */
public class ViewUsers extends Command {

    UserDaoImpl userDao;

    Collection<User> users;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            userDao = new UserDaoImpl();
            users = userDao.getAll();
            request.setAttribute("users", users);

            return "/users.jsp";
        } catch (ConnectionPoolException | SQLException e) {
            throw new CommandException(e);
        }
    }
}
