package clientInfo;


import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


/**
 * Фильтр авторизации
 * Запрещает выполнение запроса, если вызываемый метод требует авторизации
 * и нет аутентифицированного пользователя
 */
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {
    @Inject
    ClientInfo clientInfo;

    @Context
    ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        if (needAuthorization() && !clientInfo.isLoggedIn()) {
            containerRequestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
        }
    }

    private boolean needAuthorization() {
        return resourceInfo.getResourceClass().isAnnotationPresent(NeedAuthorization.class)
                || resourceInfo.getResourceMethod().isAnnotationPresent(NeedAuthorization.class);
    }
}
