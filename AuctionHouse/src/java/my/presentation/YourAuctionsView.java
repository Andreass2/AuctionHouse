/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.AuctionFacade;
import entities.Auction;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * JSF Managed Bean
 * @author addi2
 */
@Named(value = "yourAuctionsView")
@RequestScoped
public class YourAuctionsView {
     @EJB
    AuctionFacade auctionFacade;

    /**
     * Creates a new instance of YourAuctionsView
     */
    public YourAuctionsView() {
    }
   
    
      // Returns all auctions
    public List<Auction> getYourAuctions(){
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
       return auctionFacade.findYourAuctions(username);
    }
   
    public String goToYourAuctions(){    
        return "/users/yourAuctions?faces-redirect=true";
    }
    public void startAuction(Auction auction ){
        auction.setStatus(true);
        auction.setTimeCreated(new Date());
        auctionFacade.edit(auction);
    }
    
    
}
