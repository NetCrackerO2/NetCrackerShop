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

@WebServlet(name = "ClientInfoServlet", urlPatterns = {"/clientInfoServlet.jsp"})
public class ClientInfoServlet extends HttpServlet {
    @Inject
    ClientBean clientBean;
    @Inject
    ClientInfo clientInfo;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            if (request.getParameter("saveChangeInfo") != null) {
                //TODO:ClientInfo null???
                if (!clientBean.get(Integer.parseInt(request.getParameter("idUser")))
                        .equals(request.getParameter("nameUser")))
                    clientBean.setName(
                            Integer.parseInt(request.getParameter("idUser")),
                            request.getParameter("nameUser"));
//                if (!clientInfo.getName().equals(request.getParameter("addressUser")))
//                    clientBean.setAddress(request.getParameter("addressUser"));
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

        request.getRequestDispatcher("user_profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
