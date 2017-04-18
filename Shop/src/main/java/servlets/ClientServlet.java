package servlets;

import beans.ClientBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "ClientServlet", urlPatterns = {"/clientsServlet.jsp"})
public class ClientServlet extends HttpServlet {
    @Inject
    ClientBean clientBean;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            if (request.getParameter("addClient") != null) {
                clientBean.addClient(request.getParameter("clientName"),
                                     request.getParameter("clientDefaultAddress")
                );
            } else if (request.getParameter("removeClient") != null) {
                clientBean.remove(Integer.parseInt(request.getParameter("clientId")));
            }
        } catch (NumberFormatException e) {
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", "Введены некорректные значения.");
        } catch (Exception e) {
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", e.getMessage());
        }

        request.getRequestDispatcher("admin_view.jsp").forward(request, response);
    }
}
