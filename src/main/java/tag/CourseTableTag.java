package tag;

import model.Course;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Alexeev on 17.10.2016.
 */

/**
 * CourseTableTag prints a single course information as a part of the table on page
 */
public class CourseTableTag extends TagSupport {

    private Course course;

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write(String.format("                    <tr>\n" +
                            "                        <td>%s</td>\n" +
                            "                        <td>%s</td>\n" +
                            "                        <td><a href=\"./main?action=profile&id=%d\">%s %s</a></td>\n" +
                            "                        <td><a href=\"./main?action=course&id=%d\">View Course</a></td>\n" +
                            "                    </tr>",
                    course.getName(), course.getDescription(), course.getOwner().getUserId(), course.getOwner().getFirstName(),
                    course.getOwner().getLastName(), course.getCourseId()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
