package command;

import exception.CommandException;
import exception.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alexeev on 17.10.2016.
 */
public abstract class Command {
    public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException;
}
