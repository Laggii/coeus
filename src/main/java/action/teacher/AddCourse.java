package action.teacher;

import action.Action;
import database.dao.interfaces.UserCoursesDao;
import database.dao.mysql.CourseDaoImpl;
import database.dao.mysql.UserCoursesDaoImpl;
import exception.DaoException;
import model.Course;
import model.User;
import service.InputValidator;
import service.MessageProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static service.MessageProvider.*;

/**
 * Created by Alexeev on 23.10.2016.
 */

/**
 * AddCourse action processes Admin/Teacher request to create new course
 */
public class AddCourse extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        CourseDaoImpl courseDao = new CourseDaoImpl();
        UserCoursesDao userCoursesDao = new UserCoursesDaoImpl();

        //check user rights
        if (!(user.getIsTeacher() || user.getIsAdmin())) {
            request.setAttribute("errorMsg", INSUFFICIENT_RIGHTS_ERROR);
            return "/main.jsp";
        }

        String name = request.getParameter("name");
        String description = request.getParameter("description");

        //validate input parameters
        MessageProvider validationResult = InputValidator.validateCourse(name, description);
        if (validationResult != VALID) {
            request.setAttribute("name", name);
            request.setAttribute("description", description);
            request.setAttribute("errorMsg", validationResult);
        } else {
            Course course = new Course.Builder()
                    .setName(name)
                    .setDescription(description)
                    .setOwner(user)
                    .build();

            courseDao.create(course);
            long courseId = courseDao.getId(course.getName());
            course.setCourseId(courseId);
            userCoursesDao.joinCourse(user, course);

            request.setAttribute("successMsg", COURSE_CREATED_SUCCESS);
        }
        return "/main?action=courses";
    }
}
