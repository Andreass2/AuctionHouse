/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.AppUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fredrik
 */
@Stateless
public class AppUserFacade extends AbstractFacade<AppUser> {

    @PersistenceContext(unitName = "AuctionHousePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppUserFacade() {
        super(AppUser.class);
    }
    
    public AppUser Login(String email, String password){
        //AppUser user = (AppUser) em.createQuery("SELECT * FROM AppUser WHERE email = " + email + ";").getSingleResult();
        //List<AppUser> users = this.findAll();
        /*if(user != null && user.getPassword().equals(password)){
            return user;
        }else{
            return null;
        }*/
        return null;
    }
}
