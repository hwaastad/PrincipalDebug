/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.principallookup.event;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.waastad.principallookup.qualifier.DBLog;
import org.waastad.principallookup.qualifier.EventLog;

/**
 *
 * @author helge
 */
@Stateless
public class LogReceiver {

    @Resource
    private SessionContext context;
    
    @Inject
    @DBLog
    private Event<String> dbLog;

    @Asynchronous
    public void logEvent(@Observes @EventLog String message) {
        System.out.println("LogReceiver: Caller Principal: " + context.getCallerPrincipal().getName());
        dbLog.fire("I will logg this to db");
    }
}
