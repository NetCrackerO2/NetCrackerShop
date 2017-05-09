package servlets;

import backup.FromXml;
import backup.ToXml;
import beans.CategoryBean;
import clientInfo.ClientInfo;

import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static servlets.ParameterGetter.getConvertedParameter;
import static servlets.ParameterGetter.getStringParameter;


@WebServlet(name = "CategoryServlet", urlPatterns = {"/categoriesServlet.jsp"})
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
                categoryBean.addCategory(
                        getStringParameter(request, "categoryName"),
                        Objects.equals(getStringParameter(request, "parentCategoryId"), "") ?
                                null :
                                getConvertedParameter(request, "parentCategoryId", Integer::valueOf)
                );
            } else if (request.getParameter("removeCategory") != null) {
                categoryBean.remove(getConvertedParameter(request, "categoryId", Integer::valueOf));
            } else if (request.getParameter("editCategory") != null) {
                categoryBean.editCategory(getConvertedParameter(request, "categoryId", Integer::valueOf),
                                          getStringParameter(request, "categoryName")
                );
            } else if (request.getParameter("exportCategories") != null) {
                ToXml.exportCategories(categoryBean.getAll(),
                                       request.getServletContext().getRealPath("/categories.xml"));
            } else if (request.getParameter("importCategories") != null) {
                fromxml.importCategories(request.getServletContext().getRealPath("/categories.xml"));
            }
        } catch (NullPointerException e) {
            clientInfo.setErrorMessage("Отсутствует необходимый параметр: " + e.getMessage());
        } catch (ConverterException e) {
            clientInfo.setErrorMessage("Некорректное значение параметра: " + e.getMessage());
        } catch (Exception e) {
            clientInfo.setErrorMessage(e.getMessage());
        }

        request.getRequestDispatcher("admin_category.jsp").forward(request, response);
    }
}
