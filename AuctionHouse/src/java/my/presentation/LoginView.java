/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.AppUserFacade;
import entities.AppUser;
import java.io.IOException;
import java.security.Principal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

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
    private AppUser newUser;
    private String newUserPassword2;
    private boolean errorLogin;

   
    public LoginView() {
        this.user = new AppUser();
        this.newUser = new AppUser();
    }
    
    public AppUser getNewUser() {
        return newUser;
    }

    public void setNewUser(AppUser newUser) {
        this.newUser = newUser;
    }

    public String getNewUserPassword2() {
        return newUserPassword2;
    }

    public void setNewUserPassword2(String newUserPassword2) {
        this.newUserPassword2 = newUserPassword2;
    }

    public boolean isErrorLogin() {
        return errorLogin;
    }

    public void setErrorLogin(boolean errorLogin) {
        this.errorLogin = errorLogin;
    }

    
    public AppUser getUser(){
        return user;
    }
    
    public String goToLogin() throws IOException{
        return "login.xhtml";
    }
    
    public String goToSignUp() throws IOException{
        return "signup?faces-redirect=true";
    }
    

    /*public String register() throws IOException{
        if(!newUser.getEmail().equals("") && !newUser.getPassword().equals("") && newUser.getPassword().equals(newUserPassword2)){
            userFacade.create(newUser);
            user = newUser; 
            isLoggedIn=true;
            return "index.xhtml";
        }
        return "signup.xhtml";
    }*/
    public String logout() throws IOException {
        //user = new AppUser(); 
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }
    public boolean isLoggedIn(){
        Principal user = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        if(user == null || user.getName() == null || user.getName().equals("")){
            return false;
        }
        return true;
    }
}
