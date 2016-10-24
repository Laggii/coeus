package command.teacher;

import command.Command;
import exception.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alexeev on 23.10.2016.
 */

/**
 * TODO: DeleteCourse command processes Admin/Teacher request to delete course
 */
public class DeleteCourse extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        return null;
    }
}
