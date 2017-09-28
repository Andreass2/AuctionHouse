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
import javax.persistence.TypedQuery;

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
  public List<Auction> findYourAuctions(int ownerId) {
     List<Auction> list= (List<Auction>)em.createQuery("SELECT a FROM Auction a WHERE auctionowner_id="+ownerId).getResultList();
    return  list;
  }
    
 

  /*
  returns all auctions. SELECT * FROM auction did not work.
  */
  public List<Auction> findAllAuctions() {
    return (List<Auction>)em.createQuery("SELECT a FROM Auction a").getResultList();
    }
  
}
