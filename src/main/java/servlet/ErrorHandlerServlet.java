package servlet;

import org.apache.log4j.Logger;
import service.ErrorCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static service.ErrorCode.*;

/**
 * Created by Alexeev on 10.10.2016.
 */

/**
 * ErrorHandler handles all web application exceptions and errors
 * Redirects user to the valid error page explaining the error
 * TODO: possible abuse
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
        ErrorCode errorCode;

        if (servletName == null) {
            servletName = "Unknown";
        }
        if (requestUri == null) {
            requestUri = "Unknown";
        }
        if (statusCode == null) {
            statusCode = 0;
        }

        switch (statusCode) {
            case 403:
                errorCode = STATUS_403_ERROR;
                break;
            case 404:
                errorCode = STATUS_404_ERROR;
                break;
            case 500:
                errorCode = STATUS_500_ERROR;
                break;
            default:
                errorCode = STATUS_UNKNOWN_ERROR;
                break;
        }

        logger.error(String.format("ErrorCode/Exception information: {Servlet Name: %s, Status code: %d, Request URI: %s",
                servletName, statusCode, requestUri), throwable);

        request.setAttribute("errorMsg", errorCode);
        request.setAttribute("statusCode", statusCode);
        request.getRequestDispatcher("/WEB-INF/error/errorpage.jsp").forward(request, response);
    }
}
