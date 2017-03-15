import models.ProductEntity;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;
import java.util.Objects;

@Path("/test")
public class TestRest {
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
}
