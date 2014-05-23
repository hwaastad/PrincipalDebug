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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.waastad.principallookup.qualifier.DBLog;

/**
 *
 * @author helge
 */
@Stateless
public class DbReceiver {

    private static final Logger LOG = LoggerFactory.getLogger(DbReceiver.class);

    @Resource
    private SessionContext context;

    @Asynchronous
    public void logToDb(@Observes @DBLog String logMessage) {
        LOG.info("DbReceiver: Caller Principal: {}", context.getCallerPrincipal().getName());
    }
}
