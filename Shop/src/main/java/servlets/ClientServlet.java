package servlets;

import backup.FromXml;
import backup.ToXml;
import beans.ClientBean;
import beans.OrderBean;
import clientInfo.ClientInfo;
import models.ClientEntity;

import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

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
                clientBean.addClient(getStringParameter(request, "clientName"),
                                     getStringParameter(request, "clientDefaultAddress")
                );
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
            } else if (request.getParameter("logout") != null) {
                clientInfo.logout();
                response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                response.setHeader("Location", "/");
                return;
            }
            else if (request.getParameter("exportClients") != null) {
                ToXml.exportClients(clientBean.getAll(),request.getServletContext().getRealPath("/clients.xml"));
            }
            else if (request.getParameter("exportOrders") != null) {
                ToXml.exportOrders(orderBean.getAll(),request.getServletContext().getRealPath("/orders.xml"));
            }
            else if (request.getParameter("importClients") != null) {
                fromxml.importClients(request.getServletContext().getRealPath("/clients.xml"));
            }
            else if (request.getParameter("importOrders") != null) {
                HashMap<Integer,Integer> mapping = new HashMap<>();
                for(ClientEntity client:clientBean.getAll())
                    mapping.put(client.getId(), client.getId());
                fromxml.importOrders(request.getServletContext().getRealPath("/orders.xml"),mapping);
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
