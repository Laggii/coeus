package command.user;

import command.Command;
import database.dao.jdbc.CourseDaoImpl;
import exception.CommandException;
import exception.ConnectionPoolException;
import model.Course;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Alexeev on 17.10.2016.
 */
public class ViewCourses extends Command {

    CourseDaoImpl courseDao;

    Collection<Course> courses;
    Collection<Course> userCourses;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            courseDao = new CourseDaoImpl();
            courses = courseDao.getAll();
            request.setAttribute("courses", courses);

            return "/courses.jsp";
        } catch (SQLException | ConnectionPoolException e) {
            throw new CommandException(e);
        }
    }
}
