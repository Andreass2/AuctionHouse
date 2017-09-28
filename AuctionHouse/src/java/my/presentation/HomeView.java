/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.AuctionFacade;
import entities.Auction;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author addi2
 */
@Named(value = "homeView")
@RequestScoped
public class HomeView {
     @EJB
    AuctionFacade auctionFacade;

    /**
     * Creates a new instance of HomeView
     */
    public HomeView() {
    }
    
    
     // Returns the total number of auctions
    public int getNumberOfAuctions(){
       return auctionFacade.findAll().size();
    }
    
      // Returns all auctions
    public List<Auction> getAllAuctions(){
       return auctionFacade.findAllAuctions();
    }
    
    public String goToHome(){
        return "index";
    }
}
