package rest;


import beans.CategoryBean;
import models.CategoryEntity;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;


@Path("/category")
public class CategoryREST {
    @Inject
    CategoryBean bean;

    @GET
    @Produces("application/json;charset=utf-8")
    public List<CategoryEntity> getAll() {
        return bean.getAll();
    }

    @GET
    @Path("/get/{id}")
    @Produces("application/json;charset=utf-8")
    public CategoryEntity get(@PathParam("id") int id) {
        return bean.get(id);
    }

    /*@GET
    @Path("/getproductsbyid/{id}")
    @Produces("application/json;charset=utf-8")
    public List<ProductEntity> getProductsById(@PathParam("id") int id) {
        List<ProductEntity> list = bean.getProductsById(id);
        return list;
    }*/
}
