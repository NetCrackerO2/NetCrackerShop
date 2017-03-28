package rest;


import beans.GenericBean;
import beans.OrderBean;
import models.OrderEntity;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/order")
public class OrderREST extends GenericREST<OrderEntity> {
    @Inject
    OrderBean bean;

    @Override
    protected GenericBean<OrderEntity> getBean() {
        return bean;
    }

    @GET
    @Path("/addproductinorder/{orderId}")
    @Produces("application/json;charset=utf-8")
    public Response addProductInOrder(@PathParam("orderId") int orderId,
                                      @QueryParam("productId") int productId,
                                      @QueryParam("count") int count,
                                      @QueryParam("price") float price) {
        bean.addProductInOrder(orderId, productId, count, price);
        return Response.ok().build();
    }

    @GET
    @Path("/getbyclientid/{clientId}")
    @Produces("application/json;charset=utf-8")
    public List<OrderEntity> getByClientId(@PathParam("clientId") int clientId) {
        return bean.getByClientId(clientId);
    }

    @GET
    @Path("/getlastorderidbyclientid/{clientId}")
    @Produces("application/json;charset=utf-8")
    public Response getLastOrderIdByClientId(@PathParam("clientId") int clientId) {
        Integer lastIndex = bean.getLastOrderIdByClientId(clientId);
        if (lastIndex != null)
            return Response.ok().entity(lastIndex).build();
        else
            return Response.serverError().entity("ERROR: orders does not exist").build();
    }
}
