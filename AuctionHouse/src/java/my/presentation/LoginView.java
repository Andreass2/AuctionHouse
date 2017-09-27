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

    public LoginView() {
        this.user = new AppUser();
    }
    
    public AppUser getUser(){
        return user;
    }
    
    public String goToLogin(){
        return "login";
    }
    
}
