package servlets;

import beans.ProductBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Дмитрий on 12.04.2017.
 */
@WebServlet(name = "AddProductServlet", urlPatterns = {"/AddProductServlet"})
public class AddProductServlet extends HttpServlet {
    @Inject
    ProductBean productBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            if (request.getParameter("idProductNew") != null && request.getParameter("nameProductNew") != null
                    && request.getParameter("priceProductNew") != null && request.getParameter("countProductNew") != null && request.getParameter("category_id") != null) {
                productBean.addProduct(
                        Integer.parseInt(request.getParameter("idProductNew")),
                        request.getParameter("nameProductNew"),
                        Integer.parseInt(request.getParameter("priceProductNew")),
                        Integer.parseInt(request.getParameter("countProductNew")),
                        Integer.parseInt(request.getParameter("category_id")));
            }
        } catch (Exception e) {
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", e.getMessage());
        }
        request.getRequestDispatcher("admin_view.jsp").forward(request, response);
    }
}