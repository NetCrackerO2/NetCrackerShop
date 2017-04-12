package servlets;

import beans.ClientBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Дмитрий on 11.04.2017.
 */
@WebServlet(name = "AddClientServlet", urlPatterns = {"/AddClientServlet"})
public class AddClientServlet extends HttpServlet {
    @Inject
    ClientBean clientBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            if (request.getParameter("idNew") != null && request.getParameter("nameNew") != null
                    && request.getParameter("addressNew") != null) {
                clientBean.addClient(
                        Integer.parseInt(request.getParameter("idNew")),
                        request.getParameter("nameNew"),
                        request.getParameter("addressNew"));
            }
        } catch (Exception e) {
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", e.getMessage());
        }
        request.getRequestDispatcher("admin_view.jsp").forward(request, response);
    }
}
