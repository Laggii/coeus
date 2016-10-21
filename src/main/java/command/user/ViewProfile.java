package command.user;

import command.Command;
import database.dao.jdbc.UserDaoImpl;
import exception.CommandException;
import exception.ConnectionPoolException;
import model.User;
import service.InputValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import static service.ErrorCode.*;

/**
 * Created by Alexeev on 19.10.2016.
 */
public class ViewProfile extends Command {

    UserDaoImpl userDao;

    User user;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            userDao = new UserDaoImpl();
            String idParameter = request.getParameter("id");
            long id;

            if (InputValidator.validateId(idParameter)) {
                id = Long.parseLong(idParameter);
                user = userDao.read(id);

                if (user != null) {
                    request.setAttribute("userProfile", user);
                    return "/profile.jsp";
                } else {
                    request.setAttribute("errorMsg", USER_NOT_FOUND_ERROR);
                    return "/main.jsp";
                }
            } else {
                request.setAttribute("errorMsg", USER_ID_ERROR);
                return "/main.jsp";
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new CommandException(e);
        }
    }
}
