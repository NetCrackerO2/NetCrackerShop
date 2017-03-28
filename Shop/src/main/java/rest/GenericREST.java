package rest;


import beans.GenericBean;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;


public abstract class GenericREST<T> {
    GenericREST() {

    }

    protected abstract GenericBean<T> getBean();

    @GET
    @Produces("application/json;charset=utf-8")
    public List<T> getAll() {
        return getBean().getAll();
    }

    @GET
    @Path("/get/{id}")
    @Produces("application/json;charset=utf-8")
    public T get(@PathParam("id") int id) {
        return getBean().get(id);
    }
}
