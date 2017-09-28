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
import javax.persistence.Query;

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
        if(email != "" && password != ""){
            Query query = em.createQuery("SELECT u FROM AppUser u WHERE u.email LIKE ?1");
            query.setParameter(1, email);
            query.setMaxResults(1);
            List list = query.getResultList();
            if(list == null || list.isEmpty() || !((AppUser) list.get(0)).getPassword().equals(password)){
                return null;
            }
            return (AppUser) list.get(0);
            
        }
        return null;
    }
}
