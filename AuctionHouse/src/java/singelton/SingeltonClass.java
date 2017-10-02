/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singelton;

import entities.AppUser;

/**
 *
 * @author addi2
 */
public class SingeltonClass {
    private boolean loggedIn;
    private AppUser user;

  
    
    /* A private Constructor prevents any other
    * class from instantiating.
    */
    private SingeltonClass() {
        loggedIn=false;
        user = new AppUser();
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
    
    public static SingeltonClass getInstance() {
        return SingeltonClassHolder.INSTANCE;
    }
    
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    private static class SingeltonClassHolder {

        private static final SingeltonClass INSTANCE = new SingeltonClass();
    }
}
