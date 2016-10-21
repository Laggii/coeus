package command.user;

import command.Command;
import exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alexeev on 19.10.2016.
 */
public class ViewSettings extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        return "/settings.jsp";
    }
}
