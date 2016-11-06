package action.user;

import action.Action;
import exception.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alexeev on 19.10.2016.
 */

/**
 * ViewSettings action processes user request to see user settings page
 */
public class ViewSettings extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        return "/settings.jsp";
    }
}
