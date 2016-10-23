package command.user;

import command.Command;
import exception.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alexeev on 19.10.2016.
 */

/**
 * ViewSettings command processes user request to see user settings page
 */
public class ViewSettings extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        return "/settings.jsp";
    }
}
