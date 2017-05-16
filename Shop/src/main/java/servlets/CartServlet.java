package servlets;


import beans.CartBean;
import beans.ProductBean;
import clientInfo.AdminInterceptor;
import clientInfo.AuthorizationInterceptor;
import clientInfo.ClientInfo;
import clientInfo.NeedAuthorization;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "CartServlet", urlPatterns = {"/cart.jsp"})
@Interceptors({AuthorizationInterceptor.class, AdminInterceptor.class})
@NeedAuthorization
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

        try {
            if (request.getParameter("buy") != null) {
                cartBean.addProductInCart(Integer.parseInt(request.getParameter("id")),
                                          Integer.parseInt(request.getParameter("count")));
            }
            if (request.getParameter("createOrder") != null) {
                cartBean.createOrder(request.getParameter("address"));
                request.getRequestDispatcher("orders.jsp").forward(request, response);
                return;
            }
            if (request.getParameter("remove") != null) {
                cartBean.removeProductFromCart(Integer.parseInt(request.getParameter("id")));
            }
        } catch (Exception e) {
            clientInfo.setErrorMessage(e.getMessage());
        }

        request.getRequestDispatcher("cart_view.jsp").forward(request, response);
    }
}
