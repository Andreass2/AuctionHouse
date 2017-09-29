/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.AppUserFacade;
import entities.AppUser;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author fredrik
 */
@Named(value = "loginView")
@RequestScoped
public class LoginView {

    @EJB
    private AppUserFacade userFacade;
    private static AppUser user;
    private String loginBarStatus; 
    private boolean loggedIn;

    public String getLoginBarStatus() {
        return loginBarStatus;
    }

    public void setLoginBarStatus(String loginBarStatus) {
        this.loginBarStatus = loginBarStatus;
    }

    public LoginView() {
        this.user = new AppUser();
        loginBarStatus="log in";
        loggedIn = false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    public static AppUser getUser(){
        return user;
    }
    
    public String goToLogin(){
        return "login";
    }
    
    public String login(){
        AppUser authorizedUser = userFacade.Login(user.getEmail(), user.getPassword());
        if(authorizedUser != null){
            user = authorizedUser;
            loginBarStatus="log out";
            loggedIn = true;
            return "index";
        }else{
            user = new AppUser();
            return "login";
        }
    }
    public String logout(){
        user = null; 
        loggedIn = false;
        return "login";
    }
    
    
}
