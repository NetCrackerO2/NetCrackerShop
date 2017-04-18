package servlets;

import beans.CategoryBean;
import beans.ProductBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AddProductServlet", urlPatterns = {"/AddProductServlet"})
public class AddProductServlet extends HttpServlet {
    @Inject
    ProductBean productBean;
    @Inject
    CategoryBean categoryBean;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if (request.getParameter("addProduct") != null) {
            try {
                productBean.addProduct(request.getParameter("productName"),
                                       request.getParameter("productDescription"),
                                       Integer.valueOf(request.getParameter("productCount")),
                                       Float.valueOf(request.getParameter("productPrice")),
                                       Integer.valueOf(request.getParameter("productCategoryId"))
                );
            } catch (NumberFormatException e) {
                request.setAttribute("isError", true);
                request.setAttribute("errorMessage", "Введены некорректные значения. Научись читать и писать.");
            } catch (Exception e) {
                request.setAttribute("isError", true);
                request.setAttribute("errorMessage", e.getMessage());
            }
        }
        request.getRequestDispatcher("admin_view.jsp").forward(request, response);
    }
}