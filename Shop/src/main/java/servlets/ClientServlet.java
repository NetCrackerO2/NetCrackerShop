package servlets;

import backup.FromXml;
import beans.ClientBean;
import beans.OrderBean;
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
    OrderBean orderBean;
    @Inject
    ClientInfo clientInfo;
    @Inject
    FromXml fromxml;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            if (request.getParameter("addClient") != null) {
                request.setAttribute("clientNameValue", request.getParameter("clientName"));
                request.setAttribute("clientDefaultAddressValue", request.getParameter("clientDefaultAddress"));
                request.setAttribute("clientIsAdminValue", request.getParameter("clientIsAdmin"));

                clientBean.addClient(getStringParameter(request, "clientName"),
                                     getStringParameter(request, "clientDefaultAddress"),
                                     getConvertedParameter(request, "clientIsAdmin", Boolean::valueOf)
                );

                request.setAttribute("clientNameValue", "");
                request.setAttribute("clientDefaultAddressValue", "");
                request.setAttribute("clientIsAdminValue", "");
            } else if (request.getParameter("removeClient") != null) {
                Integer clientId = getConvertedParameter(request, "clientId", Integer::valueOf);
                clientBean.remove(clientId);
                if (clientInfo.getId() == clientId) {
                    clientInfo.logout();
                }
            } else if (request.getParameter("editClient") != null) {
                clientBean.editClient(getConvertedParameter(request, "clientId", Integer::valueOf),
                                      getStringParameter(request, "clientName"),
                                      getStringParameter(request, "clientDefaultAddress")
                );
                clientInfo.init(clientBean.get(clientInfo.getId()));
            } else if (request.getParameter("editClientInfo") != null) {
                clientBean.editClientInfo(getStringParameter(request, "clientName"),
                                          getStringParameter(request, "clientDefaultAddress")
                );
                clientInfo.init(clientBean.get(clientInfo.getId()));
                request.getRequestDispatcher("user_profile.jsp").forward(request, response);
                return;
            } else if (request.getParameter("logout") != null) {
                clientInfo.logout();
                response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                response.setHeader("Location", "/");
                return;
            }
        } catch (NullPointerException e) {
            clientInfo.setErrorMessage("Отсутствует необходимый параметр: " + e.getMessage());
        } catch (ConverterException e) {
            clientInfo.setErrorMessage("Некорректное значение параметра: " + e.getMessage());
        } catch (Exception e) {
            clientInfo.setErrorMessage(e.getMessage());
        }

        request.getRequestDispatcher("admin_clients.jsp").forward(request, response);
    }
}
