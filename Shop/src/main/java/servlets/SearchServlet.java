package servlets;

import beans.ProductBean;
import clientInfo.ClientInfo;
import models.ProductEntity;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@WebServlet(name = "SearchServlet", urlPatterns = {"/searchServlet.jsp"})
public class SearchServlet extends HttpServlet {
    @Inject
    ProductBean productBean;
    @Inject
    ClientInfo clientInfo;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        List<ProductEntity> list = null;

        if (request.getParameter("findProductWide") != null) {
            try {
                List<String[]> parameters = new ArrayList<>();
                List<String[]> minMaxParameters = new ArrayList<>();
                addItem(parameters, "name", request.getParameter("nameFilter"));
                addItem(parameters, "category.name", request.getParameter("categoryFilter"));
                addMinMaxItem(minMaxParameters,
                              "price",
                              request.getParameter("minPriceFilter"),
                              request.getParameter("maxPriceFilter"));
                addMinMaxItem(minMaxParameters,
                              "count",
                              "0",
                              request.getParameter("countFilter"));

                list = productBean.filterBy(parameters, minMaxParameters);
            } catch (IllegalArgumentException | EJBException e) {
                clientInfo.setErrorMessage("Введены некорректные параметры фильтрации.");
            } catch (Exception e) {
                clientInfo.setErrorMessage(e.getMessage());
            }
        }

        request.setAttribute("products", list);
        request.getRequestDispatcher("search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void addItem(List<String[]> parameters, String parameterName, String parameterValue) {
        if ((parameterName == null || parameterValue == null)
                || (Objects.equals(parameterName, "") || Objects.equals(parameterValue, ""))) {
            return;
        }

        parameters.add(new String[] {parameterName, parameterValue});
    }

    private void addMinMaxItem(List<String[]> minMaxParameters,
                               String parameterName,
                               String parameterMinValue,
                               String parameterMaxValue) {
        if (parameterName == null
                || parameterMinValue == null
                || parameterMaxValue == null
                || Objects.equals(parameterName, "")
                || Objects.equals(parameterMinValue, "")
                || Objects.equals(parameterMaxValue, "")) {
            return;
        }

        minMaxParameters.add(new String[] {parameterName, parameterMinValue, parameterMaxValue});
    }
}
