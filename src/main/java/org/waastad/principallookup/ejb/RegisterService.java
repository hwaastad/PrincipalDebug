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
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.waastad.principallookup.event.LogReceiver;
import org.waastad.principallookup.interceptor.LogInterceptor;
import org.waastad.principallookup.qualifier.EventLog;

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

    @Inject
    @EventLog
    private Event<String> smsListener;

    @EJB
    private LogReceiver receiver;

    @Resource
    private SessionContext context;

    @Override
    @RolesAllowed({"AdminGroup"})
    public void doPrivilegedStuff() {
        String string = "Im doing privileged stuff";
        smsListener.fire(string);
        receiver.logEventCall(string);
        receiver.logEventAsynchCall(string);
    }

    @Override
    public void doUnprivilegedStuff() {
        String string = "Im doing Un-privileged stuff";
        smsListener.fire(string);
        receiver.logEventCall(string);
        receiver.logEventAsynchCall(string);
    }

}
