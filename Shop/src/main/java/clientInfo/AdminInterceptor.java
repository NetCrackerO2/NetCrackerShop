package clientInfo;


import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class AdminInterceptor {
    @Inject
    ClientInfo clientInfo;

    @AroundInvoke
    public Object test(InvocationContext context) throws Exception {
        if (needAdmin(context) && (!clientInfo.isLoggedIn() || !clientInfo.getAdmin())) {
            return null;
        }
        return context.proceed();
    }

    private boolean needAdmin(InvocationContext context) {
        return context.getTarget().getClass().isAnnotationPresent(NeedAdmin.class)
                || context.getTarget().getClass().getSuperclass().isAnnotationPresent(NeedAdmin.class)
                || context.getMethod().isAnnotationPresent(NeedAdmin.class);
    }
}
