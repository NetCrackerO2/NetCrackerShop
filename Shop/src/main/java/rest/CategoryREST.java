package rest;


import beans.CategoryBean;
import models.CategoryEntity;
import models.ProductEntity;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;


@Path("/category")
public class CategoryREST {
    @Inject
    CategoryBean cb;

    @GET
    @Produces("application/json;charset=utf-8")
    public List<CategoryEntity> function() {
        return cb.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json;charset=utf-8")
    public List<ProductEntity> getProducts(@PathParam("id") int id) {
        return cb.getProducts(id);
    }
}
