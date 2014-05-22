/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.principallookup.ejb;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import org.waastad.principallookup.qualifier.EventLog;

/**
 *
 * @author helge
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@DeclareRoles({"AdminGroup"})
public class RegisterService implements RegisterServiceLocal {

    @Inject
    @EventLog
    private Event<String> smsListener;
    
    @Resource
    private SessionContext context;

    @Override
    @RolesAllowed({"AdminGroup"})
    public void sayHello() {
        System.out.println("Caller Principal: " + context.getCallerPrincipal().getName());
        String string = "Im doing privileged stuff";
        System.out.println(string);
        smsListener.fire(string);
    }

    @Override
    public void doUnprivilegedStuff() {
        System.out.println("Caller Principal: " + context.getCallerPrincipal().getName());
        String string = "Im doing Un-privileged stuff";
        System.out.println(string);
        smsListener.fire(string);
    }

}
