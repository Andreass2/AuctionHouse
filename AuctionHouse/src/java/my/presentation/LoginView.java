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
    private boolean loggedIn;
    private AppUser newUser;
    private String newUserPassword2;
    private boolean errorLogin;

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

    public String getLoginBarStatus() {
        return loginBarStatus;
    }

    public void setLoginBarStatus(String loginBarStatus) {
        this.loginBarStatus = loginBarStatus;
    }

    public boolean isErrorLogin() {
        return errorLogin;
    }

    public void setErrorLogin(boolean errorLogin) {
        this.errorLogin = errorLogin;
    }

    public LoginView() {
        this.user = new AppUser();
        this.newUser = new AppUser();
        loginBarStatus="log in";
        loggedIn = false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    public AppUser getUser(){
        return user;
    }
    
    public String goToLogin(){
        return "login";
    }
    
    public String goToSignUp(){
        return "signup";
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
            errorLogin = true;
            return "login";
        }
    }
    public String register(){
        if(!newUser.getEmail().equals("") && !newUser.getPassword().equals("") && newUser.getPassword().equals(newUserPassword2)){
            userFacade.create(newUser);
            user = newUser; 
            loggedIn = true;
            return "index";
        }
        return "login";
    }
    public String logout(){
        user = new AppUser(); 
        loggedIn = false;
        return "login";
    }
    
    
}
