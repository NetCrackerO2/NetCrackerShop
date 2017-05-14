package servlets;

import backup.FromXml;
import backup.ToXml;
import beans.CategoryBean;
import beans.ProductBean;
import clientInfo.ClientInfo;
import models.CategoryEntity;

import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
    @Inject
    FromXml fromXml;
    @Inject
    ToXml toXml;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            if (request.getParameter("addProduct") != null) {
                request.setAttribute("productNameValue", request.getParameter("productName"));
                request.setAttribute("productDescriptionValue", request.getParameter("productDescription"));
                request.setAttribute("productPriceValue", request.getParameter("productCount"));
                request.setAttribute("productCountValue", request.getParameter("productPrice"));
                request.setAttribute("categorySelectValue", request.getParameter("categorySelect"));

                productBean.addProduct(getStringParameter(request, "productName"),
                                       getStringParameter(request, "productDescription"),
                                       getConvertedParameter(request, "productCount", Integer::valueOf),
                                       getConvertedParameter(request, "productPrice", Float::valueOf),
                                       getCategoryIdByName(request.getParameter("categorySelect"))
                );

                request.setAttribute("productNameValue", "");
                request.setAttribute("productDescriptionValue", "");
                request.setAttribute("productPriceValue", "");
                request.setAttribute("productCountValue", "");
                request.setAttribute("categorySelectValue", "");
            } else if (request.getParameter("removeProduct") != null) {
                productBean.remove(Integer.parseInt(request.getParameter("productId")));
            } else if (request.getParameter("editProduct") != null) {
                productBean.editProduct(getConvertedParameter(request, "productId", Integer::valueOf),
                                        getStringParameter(request, "productName"),
                                        getConvertedParameter(request, "productCount", Integer::valueOf),
                                        getConvertedParameter(request, "productPrice", Float::valueOf)
                );
            } else if (request.getParameter("export") != null) {
                toXml.export(request.getServletContext().getRealPath("/exported.xml"));
            } else if (request.getParameter("importProducts") != null) {
                HashMap<Integer, Integer> mapping = new HashMap<>();
                for (CategoryEntity client : categoryBean.getAll())
                    mapping.put(client.getId(), client.getId());
                fromXml.importProducts(request.getServletContext().getRealPath("/products.xml"), mapping);
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

    private int getCategoryIdByName(String name) {
        List<CategoryEntity> tmp = categoryBean.getAll();
        for (CategoryEntity i : tmp) {
            if (i.getName().equals(name)) {
                return i.getId();
            }
        }
        return -1;
    }
}