/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.principallookup.event;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author helge
 */
public class LogReceiver implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(LogReceiver.class);

    private String message;

    @Resource
    private SessionContext context;

    public LogReceiver(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        LOG.info("I will logg msg: {} to db", message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
