package servlets;


import beans.CartBean;
import beans.ProductBean;
import clientInfo.ClientInfo;

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
    @Inject
    ClientInfo clientInfo;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if (!clientInfo.isLoggedIn()) {
            response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            response.setHeader("Location", "/auth_view.jsp");
            return;
        }

        try {
            if (request.getParameter("buy") != null) {
                cartBean.addProductInCart(Integer.parseInt(request.getParameter("id")),
                                          Integer.parseInt(request.getParameter("count")));
            } else if (request.getParameter("createOrder") != null) {
                cartBean.createOrder(request.getParameter("address"));

                response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                response.setHeader("Location", "/orders.jsp");
                return;
            } else if (request.getParameter("remove") != null) {
                cartBean.removeProductFromCart(Integer.parseInt(request.getParameter("id")));
            }
        } catch (Exception e) {
            clientInfo.setErrorMessage(e.getMessage());
        }

        request.getRequestDispatcher("cart_view.jsp").forward(request, response);
    }
}
