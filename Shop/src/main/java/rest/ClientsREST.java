package rest;


import beans.ClientBean;
import beans.GenericBean;
import clientInfo.ClientInfo;
import clientInfo.NeedAuthorization;
import models.ClientEntity;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;


@Path("/client")
public class ClientsREST extends GenericREST<ClientEntity> {

    @Inject
    ClientBean bean;

    @Inject
    ClientInfo clientInfo;

    @Override
    protected GenericBean<ClientEntity> getBean() {
        return bean;
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
    @NeedAuthorization
    public Response foo(@CookieParam("clientId") Cookie cookie) {
        return Response.ok().entity(clientInfo.getId()).build();
    }
}
