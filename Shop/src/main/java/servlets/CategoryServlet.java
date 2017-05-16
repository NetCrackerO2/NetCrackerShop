package servlets;

import backup.FromXml;
import beans.CategoryBean;
import clientInfo.AdminInterceptor;
import clientInfo.AuthorizationInterceptor;
import clientInfo.ClientInfo;
import clientInfo.NeedAdmin;

import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static servlets.ParameterGetter.getConvertedParameter;
import static servlets.ParameterGetter.getStringParameter;


@WebServlet(name = "CategoryServlet", urlPatterns = {"/categoriesServlet.jsp"})
@Interceptors({AuthorizationInterceptor.class, AdminInterceptor.class})
@NeedAdmin
public class CategoryServlet extends HttpServlet {
    @Inject
    CategoryBean categoryBean;
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
            if (request.getParameter("addCategory") != null) {
                request.setAttribute("categoryNameValue", request.getParameter("categoryName"));

                categoryBean.addCategory(
                        getStringParameter(request, "categoryName"),
                        null
                );

                request.setAttribute("categoryNameValue", "");
            } else if (request.getParameter("removeCategory") != null) {
                categoryBean.remove(getConvertedParameter(request, "categoryId", Integer::valueOf));
            } else if (request.getParameter("editCategory") != null) {
                categoryBean.editCategory(getConvertedParameter(request, "categoryId", Integer::valueOf),
                                          getStringParameter(request, "categoryName")
                );
            }
        } catch (NullPointerException e) {
            clientInfo.setErrorMessage("Отсутствует необходимый параметр: " + e.getMessage());
        } catch (ConverterException e) {
            clientInfo.setErrorMessage("Некорректное значение параметра: " + e.getMessage());
        } catch (Exception e) {
            clientInfo.setErrorMessage(e.getMessage());
        }

        if (request.getParameter("editCategory") != null) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            if(clientInfo.getErrorMessage()!=null)
                response.getWriter().print(clientInfo.getErrorMessage());
            clientInfo.setErrorMessage("");
            return;
        }

        request.getRequestDispatcher("admin_categories.jsp").forward(request, response);
    }
}
