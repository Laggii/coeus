package command.user;

import command.Command;
import database.dao.interfaces.UserCoursesDao;
import database.dao.mysql.CourseDaoImpl;
import database.dao.mysql.UserCoursesDaoImpl;
import exception.DaoException;
import model.Course;
import model.User;
import service.InputValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

import static service.MessageProvider.*;

/**
 * Created by Alexeev on 25.10.2016.
 */
public class LeaveCourse extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        CourseDaoImpl courseDao = new CourseDaoImpl();
        UserCoursesDao userCoursesDao = new UserCoursesDaoImpl();

        String idParameter = request.getParameter("id");

        if (InputValidator.validateId(idParameter)) {
            long courseId = Long.parseLong(idParameter);
            Course course = courseDao.read(courseId);

            if (course != null) {
                Collection<Course> userCourses = userCoursesDao.getCourses(user);
                if (userCourses.contains(course)) {
                    userCoursesDao.leftCourse(user, course);
                    request.setAttribute("successMsg", COURSE_LEFT_SUCCESS);
                } else {
                    request.setAttribute("errorMsg", COURSE_NOT_JOINED_ERROR);
                }
                return "/main?action=course&id=" + courseId;
            } else {
                request.setAttribute("errorMsg", COURSE_NOT_FOUND_ERROR);
            }
        } else {
            request.setAttribute("errorMsg", COURSE_ID_ERROR);
        }
        return "/main.jsp";
    }
}
