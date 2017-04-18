package servlets;

import beans.CategoryBean;
import beans.ProductBean;
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


@WebServlet(name = "ProductServlet", urlPatterns = {"/productsServlet.jsp"})
public class ProductServlet extends HttpServlet {
    @Inject
    ProductBean productBean;
    @Inject
    CategoryBean categoryBean;
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
            if (request.getParameter("addProduct") != null) {
                productBean.addProduct(getStringParameter(request, "productName"),
                                       getStringParameter(request, "productDescription"),
                                       getConvertedParameter(request, "productCount", Integer::valueOf),
                                       getConvertedParameter(request, "productPrice", Float::valueOf),
                                       getConvertedParameter(request, "productCategoryId", Integer::valueOf)
                );
            } else if (request.getParameter("removeProduct") != null) {
                productBean.remove(Integer.parseInt(request.getParameter("productId")));
            }
        } catch (NullPointerException e) {
            clientInfo.setErrorMessage("Отсутствует необходимый параметр: " + e.getMessage());
        } catch (ConverterException e) {
            clientInfo.setErrorMessage("Некорректное значение параметра: " + e.getMessage());
        } catch (Exception e) {
            clientInfo.setErrorMessage(e.getMessage());
        }
        request.getRequestDispatcher("admin_view.jsp").forward(request, response);
    }
}