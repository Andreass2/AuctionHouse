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
import singelton.SingeltonClass;

/**
 * JSF Managed Bean
 * @author addi2
 */
@Named(value = "yourAuctionsView")
@RequestScoped
public class YourAuctionsView {
     @EJB
    AuctionFacade auctionFacade;
    SingeltonClass singelton;

    /**
     * Creates a new instance of YourAuctionsView
     */
    public YourAuctionsView() {
        singelton= SingeltonClass.getInstance();
    }
   
    
      // Returns all auctions
    public List<Auction> getYourAuctions(){
        long ownerId=1;
       return auctionFacade.findYourAuctions(ownerId);
    }
    
       //send user to auctionSchema.xhtml if user is authorized
    public String goToYourAuctions(){
        //TODO check if user is logged in or not. true if logged in
        boolean loggedIn=singelton.isLoggedIn();
  
        String uri=(loggedIn)?"yourAuctions":"login";      
        return uri;
    }
    
    
}
