package servlets;

import beans.ClientBean;
import clientInfo.AdminInterceptor;
import clientInfo.AuthorizationInterceptor;
import clientInfo.ClientInfo;
import clientInfo.NeedAuthorization;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlets.ParameterGetter.getStringParameter;

@WebServlet(name = "ClientInfoServlet", urlPatterns = {"/clientInfo.jsp"})
@Interceptors({AuthorizationInterceptor.class, AdminInterceptor.class})
@NeedAuthorization
public class ClientInfoServlet extends HttpServlet {
    @Inject
    ClientBean clientBean;
    @Inject
    ClientInfo clientInfo;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if (request.getParameter("logout") != null) {
            clientInfo.logout();
        } else if (request.getParameter("editClientInfo") != null) {
            clientBean.editClientInfo(getStringParameter(request, "clientName"),
                                      getStringParameter(request, "clientDefaultAddress")
            );
            clientInfo.init(clientBean.get(clientInfo.getId()));
            request.getRequestDispatcher("user_profile.jsp").forward(request, response);
            return;
        }

        response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
        response.setHeader("Location", "/");
    }
}
