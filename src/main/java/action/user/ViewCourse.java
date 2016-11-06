package action.user;

import action.Action;
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

import static service.MessageProvider.COURSE_ID_ERROR;
import static service.MessageProvider.COURSE_NOT_FOUND_ERROR;

/**
 * Created by Alexeev on 25.10.2016.
 */

/**
 * ViewCourse action processes user reqeust to see information about the course
 */
public class ViewCourse extends Action {

    private UserCoursesDao userCoursesDao;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        CourseDaoImpl courseDao = new CourseDaoImpl();
        userCoursesDao = new UserCoursesDaoImpl();

        String idParameter = request.getParameter("id");

        if (InputValidator.validateId(idParameter)) {
            Course course = courseDao.read(Long.parseLong(idParameter));

            if (course != null) {
                return setInfo(request, course);
            } else {
                request.setAttribute("errorMsg", COURSE_NOT_FOUND_ERROR);
            }
        } else {
            request.setAttribute("errorMsg", COURSE_ID_ERROR);
        }
        return "/main.jsp";
    }

    /**
     * Set information about the course that will be printed in JSP
     *
     * @param request
     * @param course
     * @return course page
     * @throws DaoException
     */
    private String setInfo(HttpServletRequest request, Course course) throws DaoException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        request.setAttribute("courseInfo", course);
        Collection<User> courseMembers = userCoursesDao.getUsers(course);
        request.setAttribute("courseMembers", courseMembers);

        //Check that user is a course member and show "left course" button
        if (courseMembers.contains(user)) {
            request.setAttribute("isMember", "true");
        }

        return "/course.jsp";
    }
}
