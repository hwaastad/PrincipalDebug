/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.principallookup.controller;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import org.waastad.principallookup.ejb.RegisterServiceLocal;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@Named
@ViewScoped
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

}
