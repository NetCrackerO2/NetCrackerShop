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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("login") != null) {
            clientBean.login(request.getParameter("login"));
        }
        if (clientInfo.isLoggedIn()) {
            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/");
        } else
            request.getRequestDispatcher("auth_view.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (clientInfo.isLoggedIn()) {
            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/");
        } else
            request.getRequestDispatcher("auth_view.jsp").forward(request, response);
    }
}
