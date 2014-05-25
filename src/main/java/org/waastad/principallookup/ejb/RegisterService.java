/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.principallookup.ejb;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.waastad.principallookup.event.LogReceiver;
import org.waastad.principallookup.interceptor.LogInterceptor;

/**
 *
 * @author helge
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@DeclareRoles({"AdminGroup"})
@Interceptors(LogInterceptor.class)
public class RegisterService implements RegisterServiceLocal {

    private static final Logger LOG = LoggerFactory.getLogger(RegisterService.class);

    @EJB
    private LogReceiver receiver;

    @Resource
    private SessionContext context;

    @Resource
    private ManagedExecutorService executor;

    @Inject
    private LogReceiver logReceiver;

    @Override
    @RolesAllowed({"AdminGroup"})
    public void doPrivilegedStuff() {
        String string = "Im doing privileged stuff";
        logReceiver.setMessage("Privileged stuff");
        executor.submit(logReceiver);
    }

    @Override
    public void doUnprivilegedStuff() {
        String string = "Im doing Un-privileged stuff";
        logReceiver.setMessage("Un-privileged stuff");
        executor.submit(logReceiver);
//        receiver.logEventCall(string);
//        receiver.logEventAsynchCall(string);
    }

}
