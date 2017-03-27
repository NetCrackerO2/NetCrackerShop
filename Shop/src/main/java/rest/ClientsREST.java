package rest;


import beans.ClientBean;
import models.ClientEntity;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/client")
public class ClientsREST {

    @Inject
    ClientBean bean;

    @GET
    @Produces("application/json;charset=utf-8")
    public List<ClientEntity> getAll() {
        return bean.getAll();
    }

    @GET
    @Path("/get/{id}")
    @Produces("application/json;charset=utf-8")
    public ClientEntity get(@PathParam("id") int id) {
        return bean.get(id);
    }

    @GET
    @Path("/getbylogin/{login}")
    @Produces("application/json;charset=utf-8")
    public ClientEntity getByLogin(@PathParam("login") String login) {
        return bean.getByLogin(login);
    }

    @GET
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response function(@QueryParam("login") String login) {
        ClientEntity client;
        if ((client = getByLogin(login)) != null) {
            NewCookie cookie = new NewCookie(
                    "clientId",
                    Integer.toString(client.getId())
            );
            return Response.ok("OK").cookie(cookie).build();
        } else {
            return Response.serverError().entity("ERROR: invalid login.").build();
        }
    }

    @GET
    @Path("/foo")
    @Produces(MediaType.TEXT_PLAIN)
    public Response foo(@CookieParam("clientId") Cookie cookie) {
        if (cookie == null) {
            return Response.serverError().entity("ERROR").build();
        } else {
            return Response.ok(cookie.getValue()).build();
        }
    }
}
