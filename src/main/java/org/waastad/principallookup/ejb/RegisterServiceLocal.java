/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.waastad.principallookup.ejb;

import javax.ejb.Local;

/**
 *
 * @author helge
 */
@Local
public interface RegisterServiceLocal {

    void doPrivilegedStuff();

    void doUnprivilegedStuff();
    
}
