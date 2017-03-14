package servlets;

//import models.ProductEntity;

import models.ProductEntity;
import sql.AbstractDAO;
import sql.DAOPostgres;
import sql.Fields;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "MainServlet", urlPatterns = { "/" })
public class MainServlet extends HttpServlet {

    @Inject
    AbstractDAO dao;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        DAOPostgres dp = new DAOPostgres();
        dp.setURL(DAOPostgres.DEFAULT_HOST, DAOPostgres.DEFAULT_DATABASE, DAOPostgres.DEFAULT_PORT);
        dp.Connect(DAOPostgres.DEFAULT_LOGIN, DAOPostgres.DEFAULT_PASSWORD);

        ResultSet rs = dp.selectTable(Fields.PRODUCT_TABLE);

        request.setAttribute("title", "Test title");
        request.setAttribute("menu",
                new MenuItem[] { new MenuItem("test_menu1", "#"), new MenuItem("test_menu2", "#") });
        List<Product> products = new ArrayList<>();
        try {
            while (rs.next()){
                products.add(new Product(rs.getString(Fields.PRODUCT_NAME),
                                         rs.getString(Fields.PRODUCT_DESCRIPTION),
                                         rs.getInt(Fields.PRODUCT_PRICE)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("data", products.toArray());
        request.getRequestDispatcher("products.jsp").forward(request, response);

        ProductEntity pe = dao.get(1);
        pe.getId();
        System.out.println(pe.getProdName());
    }
}
