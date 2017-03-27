package clientInfo;


import beans.ClientBean;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


/**
 * Фильтр аутентификации
 * При каждом запросе пытается аутентифицировать пользователя, опираясь на cookies.
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Inject
    ClientBean bean;

    @Inject
    ClientInfo clientInfo;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        Cookie token = containerRequestContext.getCookies().get("clientId");

        if (token != null && !token.getValue().isEmpty()) {
            clientInfo.init(bean.getUserInfo(Integer.valueOf(token.getValue())));
        }
    }
}
