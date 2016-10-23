package command.user;

import command.Command;
import database.dao.mysql.CourseDaoImpl;
import database.dao.mysql.UserDaoImpl;
import exception.DaoException;
import model.Course;
import model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

/**
 * Created by Alexeev on 17.10.2016.
 */

/**
 * ViewCourses command processes user request to see all courses information
 */
public class ViewCourses extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        HttpSession session = request.getSession();

        CourseDaoImpl courseDao = new CourseDaoImpl();
        UserDaoImpl userDao = new UserDaoImpl();

        Collection<Course> courses = courseDao.getAll();
        request.setAttribute("courses", courses);

        Collection<Course> userCourses = userDao.getCourses((User) session.getAttribute("user"));
        request.setAttribute("userCourses", userCourses);
        return "/courses.jsp";
    }
}
