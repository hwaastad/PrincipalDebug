/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.principallookup.scheduler;

import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import org.waastad.principallookup.ejb.RegisterServiceLocal;

/**
 *
 * @author helge
 */
@Singleton
@RunAs("AdminGroup")
public class TimerBean {

    @EJB
    private RegisterServiceLocal registerServiceLocal;

    @Schedule(hour = "*",minute = "*",second = "*/20")
    public void test() {
        System.out.println("TimerBean (Admin): Start running");
        registerServiceLocal.doPrivilegedStuff();
    }
}
