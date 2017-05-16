package servlets;

import backup.FromXml;
import backup.MergePolicy;
import backup.ToXml;
import beans.CategoryBean;
import beans.ProductBean;
import clientInfo.AdminInterceptor;
import clientInfo.AuthorizationInterceptor;
import clientInfo.ClientInfo;
import clientInfo.NeedAdmin;
import models.CategoryEntity;

import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static servlets.ParameterGetter.getConvertedParameter;
import static servlets.ParameterGetter.getStringParameter;

@WebServlet(name = "ProductServlet", urlPatterns = {"/productsServlet.jsp"})
@Interceptors({AuthorizationInterceptor.class, AdminInterceptor.class})
@NeedAdmin
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
                                       getCategoryIdByName(request.getParameter("categorySelect")));

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
                                        getConvertedParameter(request, "productPrice", Float::valueOf),
                                        getCategoryIdByName(getStringParameter(request, "productCategory")));
            } else if (request.getParameter("export") != null) {
                toXml.export(request.getServletContext().getRealPath("/exported.xml"));
                request.getRequestDispatcher("admin_exp_imp.jsp").forward(request, response);
                return;
            } else if (request.getParameter("import") != null) {
                String value = request.getParameter("mergePolicyRadio");
                MergePolicy policy = Objects.equals(value, "1") ? MergePolicy.IGNORE_COPIES :
                        (Objects.equals(value, "2") ? MergePolicy.RENAME_COPIES : null);
                fromXml.importBackup(request.getServletContext().getRealPath("/exported.xml"), policy);
                request.getRequestDispatcher("admin_exp_imp.jsp").forward(request, response);
                return;
            }
        } catch (NullPointerException e) {
            clientInfo.setErrorMessage("Отсутствует необходимый параметр: " + e.getMessage());
        } catch (ConverterException e) {
            clientInfo.setErrorMessage("Некорректное значение параметра: " + e.getMessage());
        } catch (Exception e) {
            clientInfo.setErrorMessage(e.getMessage());
        }
        if (request.getParameter("editProduct") != null) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            if(clientInfo.getErrorMessage()!=null)
                response.getWriter().print(clientInfo.getErrorMessage());
            clientInfo.setErrorMessage("");
            return;
        }
        request.getRequestDispatcher("admin_products.jsp").forward(request, response);
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