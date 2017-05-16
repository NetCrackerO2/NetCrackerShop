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


@WebServlet(name = "AuthServlet", urlPatterns = {"/auth.jsp"})
public class AuthServlet extends HttpServlet {
    @Inject
    ClientBean clientBean;
    @Inject
    ClientInfo clientInfo;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if (clientInfo.isLoggedIn()) {
            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/");
        }

        if (request.getParameter("login") != null) {
            login(request);
        } else if (request.getParameter("register") != null) {
            try {
                clientBean.registerClient(request.getParameter("clientName"),
                                          request.getParameter("clientDefaultAddress"));
                login(request);
            } catch (Exception e) {
                clientInfo.setErrorMessage(e.getMessage());
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }
        }

        if (clientInfo.isLoggedIn()) {
            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/");
        } else {
            request.getRequestDispatcher("auth_view.jsp").forward(request, response);
        }
    }

    private void login(HttpServletRequest request) {
        clientBean.login(request.getParameter("clientName"));
        if (!clientInfo.isLoggedIn()) {
            clientInfo.setErrorMessage("Неверное имя: " + request.getParameter("clientName"));
        }
    }
}
