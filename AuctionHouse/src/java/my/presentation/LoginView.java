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
    private AppUser user;
    private String loginBarStatus; 

    public String getLoginBarStatus() {
        return loginBarStatus;
    }

    public void setLoginBarStatus(String loginBarStatus) {
        this.loginBarStatus = loginBarStatus;
    }

    public LoginView() {
        this.user = new AppUser();
        loginBarStatus="log in";
    }
    
    public AppUser getUser(){
        return user;
    }
    
    public String goToLogin(){
        return "login";
    }
    
    public String login(){
        if(userFacade.findAll() != null){
            loginBarStatus="log out";
            return "index";
        }else{
            return "login";
        }
    }
    
    
}
