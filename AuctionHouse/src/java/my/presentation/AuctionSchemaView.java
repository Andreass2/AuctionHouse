/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;


import entities.Auction;
import entities.AppUser;
import boundary.AuctionFacade;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;


/**
 *
 * @author addi2
 */
@Named(value = "auctionSchemaView")
@RequestScoped
public class AuctionSchemaView {
    @EJB
    AuctionFacade auctionFacade;
    Auction auction;
   
    /**
     * Creates a new instance of AuctionSchemaView
     */
    public AuctionSchemaView() {
        this.auction=new Auction();
    }
    
    //send user to auctionSchema.xhtml if user is authorized
    public String goToNewAuction(){
        //TODO check if user is logged in or not. true if logged in
        boolean loggedIn=true;
  
        String uri=(loggedIn)?"auctionSchema":"login";      
        return uri;
    }
    
    //gives faclets acces to write data to entity object
    public Auction getAuction(){
        return auction;
    }
    
    
    
     // Saves the auctions and then returns the string path "index"
    public String postAuction(){
        //AppUser user = LoginView.getUser();
        //if(user != null){
            auction.setStatus(true);
            auction.setBid(0);    
            auction.setAuctionOwner(null);
            this.auctionFacade.create(auction);
            auction = new Auction();
            return "index";
        //}
        //return "login";
    }
    
    
              
}
