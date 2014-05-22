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
import javax.enterprise.event.Observes;
import org.waastad.principallookup.qualifier.DBLog;

/**
 *
 * @author helge
 */
@Stateless
public class DbReceiver {

    @Resource
    private SessionContext context;

    @Asynchronous
    public void logToDb(@Observes @DBLog String logMessage) {
        System.out.println("DbReceiver: Caller Principal: " + context.getCallerPrincipal().getName());
    }
}
