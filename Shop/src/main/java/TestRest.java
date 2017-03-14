import models.ProductEntity;
import sql.AbstractDAO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class TestRest {
    @Inject
    AbstractDAO dao;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ProductEntity function() {
        ProductEntity pe = dao.get(1);
        return pe;
    }
}
