package servlets;


import backup.ToXml;
import beans.CartBean;
import beans.ProductBean;
import clientInfo.ClientInfo;
import models.ProductInOrder;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "CartServlet", urlPatterns = {"/cart.jsp"})
public class CartServlet extends HttpServlet {
    @Inject
    CartBean cartBean;
    @Inject
    ProductBean productBean;
    @Inject
    ClientInfo clientInfo;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            if (request.getParameter("buy") != null) {
                cartBean.addProductInCart(Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("count")));
            }
            if (request.getParameter("createOrder") != null) {
                cartBean.createOrder(request.getParameter("address"));
                request.getRequestDispatcher("orders.jsp").forward(request, response);
                return;
            }
            if (request.getParameter("remove") != null) {
                cartBean.removeProductFromCart(Integer.parseInt(request.getParameter("id")));
            }
            if (request.getParameter("orderInfoButton") != null) {
                request.setAttribute("orderId",request.getParameter("orderId"));
                request.getRequestDispatcher("order.jsp").forward(request, response);
                return;
            }
            if (request.getParameter("exportCarts") != null) {
                ToXml.exportCarts(cartBean.getAll(),request.getServletContext().getRealPath("/carts.xml"));
                request.getRequestDispatcher("admin_clients.jsp").forward(request, response);
                return;
            }

        } catch (Exception e) {
            clientInfo.setErrorMessage(e.getMessage());
        }

        request.getRequestDispatcher("cart_view.jsp").forward(request, response);
    }
}
