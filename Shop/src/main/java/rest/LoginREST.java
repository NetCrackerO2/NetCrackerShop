package rest;


import beans.ClientBean;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("/auth")
public class LoginREST {

    @Inject
    ClientBean bean;

    @POST
    @Path("/login")
    @Produces("application/json;charset=utf-8")
    public void login(@FormParam("login") String login) {
        bean.login(login);
    }
}
