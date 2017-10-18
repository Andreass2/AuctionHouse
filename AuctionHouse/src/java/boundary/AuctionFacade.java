/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.AppUser;
import entities.Auction;
import entities.Bid;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fredrik
 * dynamic(translated at runtime) queris made with JPQL (java presitence query language)
 * dynamic queries is translated every time, vs static witch is only compiled onced and can be used by many different methods
 * dynamic/static/native are all prone to runtime errors (som vi har sett flere ganger under testingen), criteria based queries fikser dette
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
    //dynamic queris 
    public List<Auction> findYourAuctions(long ownerId) {
        Query query = em.createQuery("SELECT a FROM Auction a WHERE CAST(a.auctionOwner.id AS VARCHAR(10)) LIKE CAST(?1 AS VARCHAR(10)) AND a.finished = false");
        query.setParameter(1, ownerId);
        List<Auction> list= query.getResultList();
        return  list;
    }
        public List<Auction> findYourPurchases(long ownerId) {
            //change to bidOwner
        Query query = em.createQuery("SELECT a FROM Auction a WHERE CAST(a.bidOwner.id AS VARCHAR(10)) LIKE CAST(?1 AS VARCHAR(10)) AND a.finished = true");
        query.setParameter(1, ownerId);
        List<Auction> list= query.getResultList();
        return  list;
    }
    public void setFinished(long auctionId){
        Query query = em.createQuery("UPDATE Auction a SET a.finished = true, a.status = false  WHERE CAST(a.id AS VARCHAR(10)) LIKE CAST(?1 AS VARCHAR(10))" );
        query.setParameter(1, auctionId);
        query.executeUpdate();
    }
    public void createAuction(Auction auction, int bid, AppUser user) {
        this.create(auction);
        Bid b = new Bid();
        b.setAuction(auction);
        b.setBid(bid);
        b.setUser(user);
        em.persist(b);
    }

    /**
     *
     * @param auctionId
     * @return
     */
    public Bid getBid(long auctionId){
        Query query = em.createQuery("SELECT b FROM Bid b WHERE b.bid = (SELECT MAX(b.bid) FROM Bid b WHERE CAST(b.auction.id AS VARCHAR(10)) LIKE CAST (?1 AS VARCHAR(10)))");
        query.setParameter(1, auctionId).setMaxResults(1);
        try{
            Bid bid = (Bid)query.getSingleResult();
            return bid;
        }catch(NoResultException e){
            
        }
        return null;
    }
    public void saveBid(Bid b) {
        em.persist(b);
    }
  
}
