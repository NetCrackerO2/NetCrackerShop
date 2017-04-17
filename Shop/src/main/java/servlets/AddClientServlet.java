package servlets;

import beans.ClientBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AddClientServlet", urlPatterns = {"/AddClientServlet"})
public class AddClientServlet extends HttpServlet {
    @Inject
    ClientBean clientBean;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if (request.getParameter("addClient") != null) {
            try {
                clientBean.addClient(request.getParameter("clientName"),
                                     request.getParameter("clientDefaultAddress")
                );
            } catch (Exception e) {
                request.setAttribute("isError", true);
                request.setAttribute("errorMessage", e.getMessage());
            }
        }

        request.getRequestDispatcher("admin_view.jsp").forward(request, response);
    }
}
