/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.principallookup.interceptor;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import org.omnifaces.util.Faces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author helge
 */
public class LogInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(LogInterceptor.class);

    @Resource
    private SessionContext context;

    @AroundInvoke
    public Object log(InvocationContext invocationContext) throws Exception {
        String originName = Thread.currentThread().getName();
        String currentUser = context.getCallerPrincipal().getName();
        LOG.info("originName: {} currentUser: {}", originName, currentUser);
        return invocationContext.proceed();
    }

}
