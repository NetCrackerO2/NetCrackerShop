package rest;


import clientInfo.ClientInfo;
import clientInfo.NeedAuthorization;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;


public class AuthorizationInterceptor {
    @Inject
    ClientInfo clientInfo;

    @AroundInvoke
    public Object logMethodEntry(InvocationContext invocationContext) throws Exception {
        if (!(needAuthorization(invocationContext) && !clientInfo.isLoggedIn()))
            return invocationContext.proceed();
        return null;
    }

    private boolean needAuthorization(InvocationContext invocationContext) {
        return invocationContext.getClass().isAnnotationPresent(NeedAuthorization.class)
                || invocationContext.getMethod().isAnnotationPresent(NeedAuthorization.class);
    }
}
