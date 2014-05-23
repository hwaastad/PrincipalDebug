/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.principallookup.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import org.omnifaces.util.Faces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.waastad.principallookup.ejb.RegisterServiceLocal;

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
public class Calculator implements CalculatorWs {
    private static final Logger LOG = LoggerFactory.getLogger(Calculator.class);

    @EJB
    private RegisterServiceLocal registerServiceLocal;

    @Override
    public int sum(int add1, int add2) {
        return add1 + add2;
    }

    @Override
    public int multiply(int mul1, int mul2) {
        LOG.info("ViewController: doing priviledged action");
        registerServiceLocal.doPrivilegedStuff();
        return mul1 * mul2;
    }
    

}
