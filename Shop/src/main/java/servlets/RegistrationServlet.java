package servlets;

import beans.ClientBean;
import clientInfo.ClientInfo;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet", urlPatterns = {"/register.jsp"})
public class RegistrationServlet extends HttpServlet {
    @Inject
    ClientBean clientBean;
    @Inject
    ClientInfo clientInfo;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if (!clientInfo.isLoggedIn() && request.getParameter("login") != null) {
            try {
                clientBean.addClient(request.getParameter("login"), request.getParameter("address"));
                response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                response.setHeader("Location", "/auth.jsp");
                return;
            } catch (Exception e) {
                clientInfo.setErrorMessage(e.getMessage());
            }
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (clientInfo.isLoggedIn()) {
            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/");
        } else {
            request.getRequestDispatcher("register_view.jsp").forward(request, response);
        }
    }
}
