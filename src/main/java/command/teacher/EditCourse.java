package command.teacher;

import command.Command;
import exception.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alexeev on 23.10.2016.
 */

/**
 * TODO: EditCourse command processes Admin/Teacher request to change course information
 */
public class EditCourse  extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        return null;
    }
}
