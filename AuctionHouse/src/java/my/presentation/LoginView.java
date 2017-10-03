/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.AppUserFacade;
import entities.AppUser;
import java.io.IOException;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import singelton.SingeltonClass;

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
    SingeltonClass singelton;
    private boolean isLoggedIn;

   
    public LoginView() {
        this.user = new AppUser();
        this.newUser = new AppUser();
        singelton = SingeltonClass.getInstance();
        isLoggedIn= singelton.isLoggedIn();
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
    
    public void goToLogin() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
        response.sendRedirect("login.xhtml");
    }
    
    public void goToSignUp() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
        response.sendRedirect("signup.xhtml");
    }
    
    public void login() throws IOException{
        AppUser authorizedUser = userFacade.Login(user.getEmail(), user.getPassword());
        if(authorizedUser != null){
            user = authorizedUser;
            singelton.setLoggedIn(true);
            singelton.setUser(user);
            isLoggedIn=true;
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
            response.sendRedirect("index.xhtml");
        }else{
            user = new AppUser();
            errorLogin = true;
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
            response.sendRedirect("login.xhtml");
        }
    }
    public void register() throws IOException{
        if(!newUser.getEmail().equals("") && !newUser.getPassword().equals("") && newUser.getPassword().equals(newUserPassword2)){
            userFacade.create(newUser);
            user = newUser; 
            singelton.setLoggedIn(true);
            singelton.setUser(user);
            isLoggedIn=true;
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
            response.sendRedirect("index.xhtml");
        }
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
        response.sendRedirect("signup.xhtml");
    }
    public void logout() throws IOException {
        user = new AppUser(); 
        singelton.setLoggedIn(false); 
        isLoggedIn=false;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
        response.sendRedirect("login.xhtml");
    }
    
     public boolean isIsLoggedIn() {
        return isLoggedIn;
    }

    
    
}
