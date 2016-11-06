package action.user;

import action.Action;
import database.dao.interfaces.UserCoursesDao;
import database.dao.mysql.CourseDaoImpl;
import database.dao.mysql.UserCoursesDaoImpl;
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
 * ViewCourses action processes user request to see all courses information
 */
public class ViewCourses extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        HttpSession session = request.getSession();

        CourseDaoImpl courseDao = new CourseDaoImpl();
        UserCoursesDao userCoursesDao = new UserCoursesDaoImpl();

        Collection<Course> courses = courseDao.getAll();
        request.setAttribute("courses", courses);

        Collection<Course> userCourses = userCoursesDao.getCourses((User) session.getAttribute("user"));
        request.setAttribute("userCourses", userCourses);
        return "/courses.jsp";
    }
}
