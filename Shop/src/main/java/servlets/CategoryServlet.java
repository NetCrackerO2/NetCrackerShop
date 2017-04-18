package servlets;

import beans.CategoryBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "CategoryServlet", urlPatterns = {"/categoriesServlet.jsp"})
public class CategoryServlet extends HttpServlet {
    @Inject
    CategoryBean categoryBean;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            if (request.getParameter("addCategory") != null) {
                String parentId = request.getParameter("parentCategoryId");
                categoryBean.addCategory(
                        request.getParameter("categoryName"),
                        parentId.equals("") ? null : Integer.parseInt(parentId)
                );
            } else if (request.getParameter("removeCategory") != null) {
                categoryBean.remove(Integer.parseInt(request.getParameter("categoryId")));
            }
        } catch (NumberFormatException e) {
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", "Введены некорректные значения.");
        } catch (Exception e) {
            request.setAttribute("isError", true);
            request.setAttribute("errorMessage", e.getMessage());
        }

        request.getRequestDispatcher("admin_view.jsp").forward(request, response);
    }
}
