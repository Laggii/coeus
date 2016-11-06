package action.teacher;

import action.Action;
import database.dao.mysql.CourseDaoImpl;
import exception.DaoException;
import model.Course;
import model.User;
import service.InputValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static service.MessageProvider.*;

/**
 * Created by Alexeev on 23.10.2016.
 */

/**
 * DeleteCourse action processes Admin/Teacher request to delete course
 */
public class DeleteCourse extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        CourseDaoImpl courseDao = new CourseDaoImpl();

        String idParameter = request.getParameter("id");

        if (InputValidator.validateId(idParameter)) {
            long courseId = Long.parseLong(idParameter);
            Course course = courseDao.read(courseId);

            if (course != null) {
                //check that user is Admin or Course Owner
                if ((user.getIsTeacher() && course.isOwner(user)) || user.getIsAdmin()) {
                    courseDao.delete(course);
                    request.setAttribute("successMsg", COURSE_DELETED_SUCCESS);
                } else {
                    request.setAttribute("errorMsg", INSUFFICIENT_RIGHTS_ERROR);
                    return "/main?action=course&id=" + courseId;
                }
            } else {
                request.setAttribute("errorMsg", COURSE_NOT_FOUND_ERROR);
            }
        } else {
            request.setAttribute("errorMsg", COURSE_ID_ERROR);
        }
        return "/main.jsp";
    }
}
