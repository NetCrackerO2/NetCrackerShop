package servlets;


import beans.CartBean;
import beans.ProductBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "CartServlet", urlPatterns = {"/cart.jsp"})
public class CartServlet extends HttpServlet {
    @Inject
    CartBean cartBean;
    @Inject
    ProductBean productBean;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("buy") != null) {
            cartBean.addProductInCart(Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("count")));
        }
        if (request.getParameter("createOrder") != null) {
            cartBean.createOrder();
        }
        if (request.getParameter("remove") != null) {
            cartBean.removeProductFromCart(Integer.parseInt(request.getParameter("id")));
        }
        request.getRequestDispatcher("cart_view.jsp").forward(request, response);
    }
}
