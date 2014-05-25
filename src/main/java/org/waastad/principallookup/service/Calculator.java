/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.principallookup.service;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.waastad.principallookup.ejb.RegisterServiceLocal;
import org.waastad.principallookup.interceptor.LogInterceptor;

/**
 *
 * @author helge
 */
@Stateless
@WebService(
        portName = "CalculatorPort",
        serviceName = "CalculatorService",
        targetNamespace = "http://superbiz.org/wsdl",
        endpointInterface = "org.waastad.principallookup.service.CalculatorWs")
@Interceptors(LogInterceptor.class)
public class Calculator implements CalculatorWs {

    private static final Logger LOG = LoggerFactory.getLogger(Calculator.class);

    @Resource
    WebServiceContext wsContext;

    @EJB
    private RegisterServiceLocal registerServiceLocal;

    @Override
    public int sum(int add1, int add2) {
        return add1 + add2;
    }

    @Override
    public int multiply(int mul1, int mul2) {
        MessageContext msgCtxt = wsContext.getMessageContext();
        HttpServletRequest req = (HttpServletRequest) msgCtxt.get(MessageContext.SERVLET_REQUEST);
        String clientIP = req.getRemoteAddr();
        LOG.info("Remote IP: {}", clientIP);
        registerServiceLocal.doPrivilegedStuff();
        LOG.info("Logging out before return...");
        try {
            req.logout();
            req.getSession().invalidate();
        } catch (ServletException ex) {
            LOG.error("failure Logging out: {}", ex.getMessage());
        }
        return mul1 * mul2;
    }

}
