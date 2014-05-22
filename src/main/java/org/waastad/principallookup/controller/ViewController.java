/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.principallookup.controller;

import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.ServletException;
import org.waastad.principallookup.ejb.RegisterServiceLocal;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@Named
@SessionScoped
public class ViewController implements Serializable {

    private static final long serialVersionUID = -5993764783281077184L;

    @EJB
    private RegisterServiceLocal registerServiceLocal;

    public void priviledgedAction(ActionEvent event) {
        System.out.println("ViewController: doing priviledged action");
        registerServiceLocal.doPrivilegedStuff();
    }

    public void unPriviledgedAction(ActionEvent event) {
        System.out.println("ViewController: doing un-priviledged action");
        registerServiceLocal.doUnprivilegedStuff();
    }

    public void logout(ActionEvent event) throws ServletException, IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.invalidateSession();
        externalContext.responseSendError(401, "You are logged out.");
        facesContext.responseComplete();
    }

}
