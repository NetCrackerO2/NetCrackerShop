package rest;


import beans.ProductBean;
import models.OrderEntity;
import models.ProductEntity;

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
    public List<OrderEntity> getProductOrders(@PathParam("id") int id) {
        return pb.getOrders(id);
    }

    @GET
    @Path("/setdesc/{id}")
    @Produces("application/json;charset=utf-8")
    public String setProductDescription(@PathParam("id") int id,
                                        @QueryParam("description") String description) {
        pb.setDescription(id, description);
        return null; //TODO replace this stub to something useful
    }

    @GET
    @Path("/getbycategory/{categoryId}")
    @Produces("application/json;charset=utf-8")
    public List<ProductEntity> getProducts(@PathParam("categoryId") int categoryId) {
        return pb.getProductsByCategory(categoryId);
    }
}
