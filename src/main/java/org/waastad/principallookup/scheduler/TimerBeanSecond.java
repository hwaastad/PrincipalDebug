/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.waastad.principallookup.scheduler;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import org.waastad.principallookup.ejb.RegisterServiceLocal;

/**
 *
 * @author helge
 */
@Singleton
public class TimerBeanSecond {

    @EJB
    private RegisterServiceLocal registerServiceLocal;

    @Schedule(hour = "*",minute = "*",second = "*/10")
    public void test() {
        System.out.println("TimerBeanSecond: Start running");
        registerServiceLocal.doUnprivilegedStuff();
    }
}
