package command.admin;

import command.Command;
import exception.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alexeev on 23.10.2016.
 */

/**
 * DeleteUser command processes admin delete user request
 */
public class DeleteUser extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        return null;
    }
}
