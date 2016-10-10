package servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexeev on 10.10.2016.
 */

/**
 * ErrorHandler handles all web application exceptions and errors
 * Redirects user to the valid error page explaining the error
 */
@WebServlet("/error")
public class ErrorHandlerServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ErrorHandlerServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    private void processError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Throwable throwable = (Throwable)
                request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer)
                request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String)
                request.getAttribute("javax.servlet.error.servlet_name");
        String requestUri = (String)
                request.getAttribute("javax.servlet.error.request_uri");
        String errorMsg = "";

        if (servletName == null) {
            servletName = "Unknown";
        }
        if (requestUri == null) {
            requestUri = "Unknown";
        }
        if (statusCode == null) {
            statusCode = 0;
        }

        //TODO: translation
        switch (statusCode) {
            case 404:
                errorMsg = "Page not found";
                break;
            case 403:
                errorMsg = "Access Denied";
                break;
            case 500:
                errorMsg = "Internal Server Error";
                break;
            default:
                errorMsg = "An error has occured.";
                break;
        }

        logger.error(String.format("Error/Exception information: {Servlet Name: %s, Status code: %d, Request URI: %s",
                servletName, statusCode, requestUri), throwable);

        request.setAttribute("errorMsg", errorMsg);
        request.setAttribute("statusCode", statusCode);
        request.getRequestDispatcher("/WEB-INF/error/errorpage.jsp").forward(request, response);
    }
}
