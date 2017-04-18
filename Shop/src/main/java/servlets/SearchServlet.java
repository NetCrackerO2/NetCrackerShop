package servlets;

import beans.ProductBean;
import models.ProductEntity;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Дмитрий on 18.04.2017.
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {
    @Inject
    ProductBean productBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductEntity> list = productBean.getAll();
        if (request.getParameter("findProduct") != null) {
            try {
                list = productBean.filterProductByName(request.getParameter("findInput"), list);
                request.setAttribute("nameFind", request.getParameter("findInput"));
            } catch (NumberFormatException e) {
                request.setAttribute("isError", true);
                request.setAttribute("errorMessage", "Введены некорректные значения. Научись читать и писать.");
            } catch (Exception e) {
                request.setAttribute("isError", true);
                request.setAttribute("errorMessage", e.getMessage());
            }
        }
        if (request.getParameter("findProductWide") != null) {
            try {
                if (!request.getParameter("nameFilter").equals(""))
                    list = productBean.filterProductByName(request.getParameter("nameFilter"), list);
                if (!request.getParameter("categoryFilter").equals(""))
                    list = productBean.filterProductByCategory(
                            request.getParameter("categoryFilter"), list);
                if (!request.getParameter("minPriceFilter").equals("") && !request.getParameter("maxPriceFilter").equals(""))
                    list = productBean.filterProductByPrice(
                            Integer.parseInt(request.getParameter("minPriceFilter")),
                            Integer.parseInt(request.getParameter("maxPriceFilter")), list);
                if (!request.getParameter("countFilter").equals(""))
                    list = productBean.filterByCount(Integer.parseInt(request.getParameter("countFilter")), list);
            } catch (NumberFormatException e) {
                request.setAttribute("isError", true);
                request.setAttribute("errorMessage", "Введены некорректные значения. Научись читать и писать.");
            } catch (Exception e) {
                request.setAttribute("isError", true);
                request.setAttribute("errorMessage", e.getMessage());
            }
        }
        request.setAttribute("products", list);
        request.getRequestDispatcher("search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
