package action.teacher;

import action.Action;
import database.dao.mysql.CourseDaoImpl;
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
 * EditCourse action processes Admin/Teacher request to change course information
 */
public class EditCourse extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        CourseDaoImpl courseDao = new CourseDaoImpl();

        //check user rights
        if (!(user.getIsTeacher() || user.getIsAdmin())) {
            request.setAttribute("errorMsg", INSUFFICIENT_RIGHTS_ERROR);
            return "/main.jsp";
        }

        String idParameter = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        //validate id from request
        if (InputValidator.validateId(idParameter)) {
            long courseId = Long.parseLong(idParameter);
            Course course = courseDao.read(courseId);

            if (course != null) {
                //validate input parameters
                MessageProvider validationResult = InputValidator.validateCourse(name, description);
                if (validationResult != VALID) {
                    request.setAttribute("errorMsgModal", validationResult);
                } else {
                    course.setName(name);
                    course.setDescription(description);

                    courseDao.update(course);
                    request.setAttribute("successMsg", COURSE_UPDATED_SUCCESS);
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
