package command.teacher;

import command.Command;
import exception.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alexeev on 23.10.2016.
 */

/**
 * TODO: AddCourse command processes Admin/Teacher request to create new course
 */
public class AddCourse extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        return null;
    }
}
