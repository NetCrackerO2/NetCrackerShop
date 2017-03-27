package rest;


import beans.OrderBean;
import models.OrderEntity;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/order")
public class OrderREST {
    @Inject
    OrderBean bean;

    @GET
    @Produces("application/json;charset=utf-8")
    public List<OrderEntity> getAll() {
        return bean.getAll();
    }

    @GET
    @Path("/get/{id}")
    @Produces("application/json;charset=utf-8")
    public OrderEntity getOrder(@PathParam("id") int id) {
        return bean.get(id);
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
