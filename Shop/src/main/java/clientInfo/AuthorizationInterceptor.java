package clientInfo;


import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;


public class AuthorizationInterceptor {
    @Inject
    ClientInfo clientInfo;

    @AroundInvoke
    public Object test(InvocationContext context) throws Exception {
        if (needAuthorization(context) && !clientInfo.isLoggedIn()) {
            return null;
        }
        return context.proceed();
    }

    private boolean needAuthorization(InvocationContext context) {
        return context.getTarget().getClass().isAnnotationPresent(NeedAuthorization.class)
                || context.getTarget().getClass().getSuperclass().isAnnotationPresent(NeedAuthorization.class)
                || context.getMethod().isAnnotationPresent(NeedAuthorization.class);
    }
}
