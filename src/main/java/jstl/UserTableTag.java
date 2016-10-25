package jstl;

import model.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Alexeev on 17.10.2016.
 */

/**
 * UserTableTag prints a single user information as a part of the table on page
 */
public class UserTableTag extends TagSupport {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            String userRole;

            switch (user.getRoleId()) {
                case 1:
                    userRole = "Student";
                    break;
                case 2:
                    userRole = "Teacher";
                    break;
                case 3:
                    userRole = "Admin";
                    break;
                default:
                    userRole = "User";
            }

            pageContext.getOut().write(String.format("                    <tr>\n" +
                            "                        <td>%s</td>\n" +
                            "                        <td>%s</td>\n" +
                            "                        <td>%s</td>\n" +
                            "                        <td>%s</td>\n" +
                            "                        <td><a href=\"./main?action=profile&id=%d\">View Profile</a></td>\n" +
                            "                    </tr>",
                    user.getFirstName(), user.getLastName(), user.getEmail(), userRole, user.getUserId()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
