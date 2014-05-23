/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.principallookup.controller;

import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.ServletException;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.waastad.principallookup.ejb.RegisterServiceLocal;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@Named
@ViewScoped
public class ViewController implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(ViewController.class);

    private static final long serialVersionUID = -5993764783281077184L;

    @EJB
    private RegisterServiceLocal registerServiceLocal;

    public void priviledgedAction(ActionEvent event) {
        LOG.info("ViewController: doing priviledged action, SessionId:{}", Faces.getSession().getId());
        registerServiceLocal.doPrivilegedStuff();
    }

    public void unPriviledgedAction(ActionEvent event) {
        LOG.info("ViewController: doing un-priviledged action, SessionId:{}", Faces.getSession().getId());
        registerServiceLocal.doUnprivilegedStuff();
    }

    public void logout(ActionEvent event) throws ServletException, IOException {
        Faces.logout();
        Faces.invalidateSession();
        Faces.responseSendError(401, "You are logged out.");
        Faces.responseComplete();
    }

}
