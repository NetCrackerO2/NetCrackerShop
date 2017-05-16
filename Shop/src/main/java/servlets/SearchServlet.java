package servlets;

import beans.CategoryBean;
import beans.ProductBean;
import clientInfo.ClientInfo;
import models.CategoryEntity;
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
    @Inject
    CategoryBean categoryBean;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        List<ProductEntity> list = null;
        List<CategoryEntity> categoryList = categoryBean.getAll();

        if (request.getParameter("findProductWide") != null) {
            try {
                List<String[]> parameters = new ArrayList<>();
                List<String[]> minMaxParameters = new ArrayList<>();
                addItem(parameters, "name", request.getParameter("nameFilter"));
                if (!Objects.equals(request.getParameter("categorySelect"), "<Все>")) {
                    addItem(parameters, "category.name", request.getParameter("categorySelect"));
                }
                addMinMaxItem(minMaxParameters,
                              "price",
                              request.getParameter("minPriceFilter"),
                              request.getParameter("maxPriceFilter"));
                addMinMaxItem(minMaxParameters,
                              "count",
                              request.getParameter("countFilter"),
                              productBean.getMaxCount().toString());

                list = productBean.filterBy(parameters, minMaxParameters);
            } catch (IllegalArgumentException | EJBException e) {
                clientInfo.setErrorMessage("Введены некорректные параметры фильтрации.");
            } catch (Exception e) {
                clientInfo.setErrorMessage(e.getMessage());
            }
        }
        CategoryEntity tmp = null;
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getName().equals(request.getParameter("categorySelect"))) {
                tmp = categoryList.get(0);
                categoryList.set(0, categoryList.get(i));
                categoryList.set(i, tmp);
            }

        }
        request.setAttribute("nameValue", request.getParameter("nameFilter"));
        request.setAttribute("minPriceValue", request.getParameter("minPriceFilter"));
        request.setAttribute("maxPriceValue", request.getParameter("maxPriceFilter"));
        request.setAttribute("countValue", request.getParameter("countFilter"));
        request.setAttribute("categorySelectValue", request.getParameter("categorySelect"));
        request.setAttribute("categories", categoryList);
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
