package servlet;

import command.Command;
import command.CommandFactory;
import exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexeev on 09.10.2016.
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(MainServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandName = request.getParameter("action");
        String path;
        Command command;

        if (commandName != null) {
            try {
                command = CommandFactory.getInstance().getCommand(commandName);
                path = command.execute(request, response);
                redirect(request, response, path);
            } catch (CommandException e) {
                logger.error("Failed to process user command request", e);
                redirect(request, response, "/profile.jsp");
            }
        } else {
            redirect(request, response, "/profile.jsp");
        }
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }
}
