package rest;


import beans.GenericBean;
import beans.ProductBean;
import models.ProductEntity;
import models.ProductInOrder;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;


@Path("/product")
public class ProductREST extends GenericREST<ProductEntity> {
    @Inject
    ProductBean bean;


    @Override
    protected GenericBean<ProductEntity> getBean() {
        return bean;
    }

    /*@GET
    @Path("/add/")
    public String function(@QueryParam("name") String name,
                           @QueryParam("description") String description) {
        if (name == null || Objects.equals(name, ""))
            return null;

        bean.create(name, description);
        return null;
    }*/

    /*@GET
    @Path("/getorders/{id}")
    @Produces("application/json;charset=utf-8")
    public List<OrderProductEntity> getProductOrders(@PathParam("id") int id) {
        return bean.getOrders(id);
    }*/

    @GET
    @Path("/getbycategory/{categoryId}")
    @Produces("application/json;charset=utf-8")
    public List<ProductEntity> getByCategory(@PathParam("categoryId") int categoryId) {
        return bean.getByCategory(categoryId);
    }

    @GET
    @Path("/getinorder/{orderId}")
    @Produces("application/json;charset=utf-8")
    public List<ProductInOrder> getInOrder(@PathParam("orderId") int orderId) {
        return bean.getInOrder(orderId);
    }
}
