/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.Auction;

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
public class AuctionFacade extends AbstractFacade<Auction> {

    @PersistenceContext(unitName = "AuctionHousePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuctionFacade() {
        super(Auction.class);
    }
    
  /*
    returns all auctions by a specific user queryed by id
    */
    public List<Auction> findAllAuctions(){
        Query query = em.createQuery("SELECT a FROM Auction a WHERE a.finished = false AND a.status = true ");
        List<Auction> list= query.getResultList();
        return  list;
    }
    public List<Auction> findYourAuctions(long ownerId) {
        Query query = em.createQuery("SELECT a FROM Auction a WHERE CAST(a.auctionOwner.id AS VARCHAR(10)) LIKE CAST(?1 AS VARCHAR(10)) AND a.finished = false");
        query.setParameter(1, ownerId);
        List<Auction> list= query.getResultList();
        return  list;
    }
        public List<Auction> findYourPurchases(long ownerId) {
            //change to bidOwner
        Query query = em.createQuery("SELECT a FROM Auction a WHERE CAST(a.auctionOwner.id AS VARCHAR(10)) LIKE CAST(?1 AS VARCHAR(10)) AND a.finished = true");
        query.setParameter(1, ownerId);
        List<Auction> list= query.getResultList();
        return  list;
    }
    public void RateAuction(long auctionId, int rating){
        Query query = em.createQuery("UPDATE Auction a SET a.rating = ?1 WHERE CAST(a.id AS VARCHAR(10)) LIKE CAST(?1 AS VARCHAR(10))" );
        query.setParameter(1, auctionId);
        query.executeUpdate();   
    }
    public void setFinished(long auctionId){
        Query query = em.createQuery("UPDATE Auction a SET a.finished = true, a.status = false  WHERE CAST(a.id AS VARCHAR(10)) LIKE CAST(?1 AS VARCHAR(10))" );
        query.setParameter(1, auctionId);
        query.executeUpdate();
    }
    
 

  /*
  returns all auctions. SELECT * FROM auction did not work.
  */
  
}
