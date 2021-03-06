package clientInfo;


import beans.ClientBean;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class AdminInterceptor {
    @Inject
    ClientInfo clientInfo;

    @Inject
    ClientBean clientBean;

    @AroundInvoke
    public Object test(InvocationContext context) throws Exception {
        if (needAdmin(context) && (!clientInfo.isLoggedIn() || !clientBean.get(clientInfo.getId()).getAdmin())) {
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
