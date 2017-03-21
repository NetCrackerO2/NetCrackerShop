package rest;


import beans.ProductBean;
import models.OrderProductEntity;
import models.ProductEntity;
import models.ProductInOrder;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;
import java.util.Objects;


@Path("/product")
public class ProductREST {
    @Inject
    ProductBean pb;

    @GET
    @Produces("application/json;charset=utf-8")
    public List<ProductEntity> function() {
        return pb.getAll();
    }

    @GET
    @Path("/add/")
    public String function(@QueryParam("name") String name,
                           @QueryParam("description") String description) {
        if (name == null || Objects.equals(name, ""))
            return null;

        pb.create(name, description);
        return null;
    }

    @GET
    @Path("/get/{id}")
    @Produces("application/json;charset=utf-8")
    public ProductEntity getProduct(@PathParam("id") int id) {
        return pb.get(id);
    }

    @GET
    @Path("/getorders/{id}")
    @Produces("application/json;charset=utf-8")
    public List<OrderProductEntity> getProductOrders(@PathParam("id") int id) {
        return pb.getOrders(id);
    }

    @GET
    @Path("/setdesc/{id}")
    @Produces("application/json;charset=utf-8")
    public String setProductDescription(@PathParam("id") int id,
                                        @QueryParam("description") String description) {
        pb.setDescription(id, description);
        return null; //TODO: заменить на что-нибудь
    }

    @GET
    @Path("/getbycategory/{categoryId}")
    @Produces("application/json;charset=utf-8")
    public List<ProductEntity> getProducts(@PathParam("categoryId") int categoryId) {
        return pb.getProductsByCategory(categoryId);
    }

    @GET
    @Path("/getinorder/{orderId}")
    @Produces("application/json;charset=utf-8")
    public List<ProductInOrder> getProductsInOrder(@PathParam("orderId") int orderId) {
        return pb.getProductsInOrder(orderId);
    }
}
