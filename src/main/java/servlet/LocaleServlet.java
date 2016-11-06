package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Alexeev on 13.10.2016.
 */

/**
 * LocaleServlet allows changing localization using user request
 */
@WebServlet("/locale")
public class LocaleServlet extends HttpServlet {

    private static final String RU_CODE = "ru";
    private static final String EN_CODE = "en";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setLocale(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setLocale(request, response);
    }

    /**
     * Set locale in Session
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void setLocale(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        String lang = request.getParameter("lang");

        if (session != null && lang != null) {
            if (RU_CODE.equals(lang)) {
                session.setAttribute("language", RU_CODE);
            } else {
                //English by default
                session.setAttribute("language", EN_CODE);
            }
        }

        //return back to the page from where request came
        response.sendRedirect(request.getHeader("Referer"));
    }
}
