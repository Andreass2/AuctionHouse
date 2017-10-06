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
 * dynamic(translated at runtime) queris made with JPQL (java presitence query language)
 * dynamic queries is translated every time, vs static witch is only compiled onced and can be used by many different methods (effektiv med mange kall)
 * dynamic/static/native are all prone to runtime errors (som vi har sett flere ganger under testingen), criteria based queries fikser dette.
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
