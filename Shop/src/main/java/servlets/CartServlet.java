package servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.CartBean;
import beans.ClientBean;
import beans.ProductBean;
import clientInfo.ClientInfo;

@WebServlet(name = "CartServlet", urlPatterns = {"/cart.jsp"})
public class CartServlet extends HttpServlet {
    @Inject 
    CartBean cartBean;
    @Inject
    ProductBean productBean;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("buy") != null) {
            cartBean.addProductInCart(Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("count")));
        }
        request.getRequestDispatcher("cart_view.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("cart_view.jsp").forward(request, response);
    }
}
