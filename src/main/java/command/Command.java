package command;

import exception.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alexeev on 17.10.2016.
 */

/**
 * Abstract Command with execute method which processes user request
 * Implementation of the Strategy pattern
 */
public abstract class Command {

    /**
     * Execute command and return page where user should be forwarded
     *
     * @param request
     * @param response
     * @return page
     * @throws DaoException
     */
    public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException;
}
