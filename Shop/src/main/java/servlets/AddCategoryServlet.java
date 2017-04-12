package servlets;

import beans.CategoryBean;


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
@WebServlet(name = "AddCategoryServlet", urlPatterns = {"/AddCategoryServlet"})
public class AddCategoryServlet extends HttpServlet {
    @Inject
    CategoryBean categoryBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            if (request.getParameter("idCategoryNew") != null && request.getParameter("nameCategoryNew") != null) {
                categoryBean.addCategory(
                        Integer.parseInt(request.getParameter("idCategoryNew")),
                        request.getParameter("nameCategoryNew"));
            }
        } catch (Exception e) {
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", e.getMessage());
        }
        request.getRequestDispatcher("admin_view.jsp").forward(request, response);
    }
}
