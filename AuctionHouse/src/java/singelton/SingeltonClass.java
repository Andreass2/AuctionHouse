/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singelton;

/**
 *
 * @author addi2
 */
public class SingeltonClass {
    private boolean loggedIn;

  
    
    /* A private Constructor prevents any other
    * class from instantiating.
    */
    private SingeltonClass() {
        loggedIn=false;
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
