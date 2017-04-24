package servlets;

import beans.ClientBean;
import clientInfo.ClientInfo;

import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlets.ParameterGetter.getConvertedParameter;
import static servlets.ParameterGetter.getStringParameter;


@WebServlet(name = "ClientServlet", urlPatterns = {"/clientsServlet.jsp"})
public class ClientServlet extends HttpServlet {
    @Inject
    ClientBean clientBean;
    @Inject
    ClientInfo clientInfo;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            if (request.getParameter("addClient") != null) {
                clientBean.addClient(getStringParameter(request, "clientName"),
                        getStringParameter(request, "clientDefaultAddress")
                );
            } else if (request.getParameter("removeClient") != null) {
                Integer clientId = getConvertedParameter(request, "clientId", Integer::valueOf);
                clientBean.remove(clientId);
                if (clientInfo.getId() == clientId) {
                    clientInfo.logout();
                }
            } else if (request.getParameter("logout") != null) {
                clientInfo.logout();
            }
        } catch (NullPointerException e) {
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", "Отсутствует необходимый параметр: " + e.getMessage());
        } catch (ConverterException e) {
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", "Некорректное значение параметра: " + e.getMessage());
        } catch (Exception e) {
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", e.getMessage());
        }

        request.getRequestDispatcher("admin_clients.jsp").forward(request, response);
    }
}
