package rest;


import beans.OrderBean;
import models.OrderEntity;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;


@Path("/order")
public class OrderREST {
    @Inject
    OrderBean bean;

    @GET
    @Produces("application/json;charset=utf-8")
    public List<OrderEntity> function() {
        return bean.getAll();
    }

    @GET
    @Path("/get/{id}")
    @Produces("application/json;charset=utf-8")
    public OrderEntity getProduct(@PathParam("id") int id) {
        return bean.get(id);
    }

    @GET
    @Path("/addproductinorder/{orderId}")
    @Produces("application/json;charset=utf-8")
    public String setProductDescription(@PathParam("orderId") int orderId,
                                        @QueryParam("productId") int productId,
                                        @QueryParam("count") int count,
                                        @QueryParam("price") float price) {
        bean.addProductInOrder(orderId, productId, count, price);
        return null; //TODO: заменить на что-нибудь
    }
}
