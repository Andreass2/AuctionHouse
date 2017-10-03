/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.AuctionFacade;
import entities.Auction;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author addi2
 */
@Named(value = "auctionView")
@RequestScoped
public class AuctionView {
    private String id;
    @EJB
    AuctionFacade auctionFacade;
    private Auction auction;
    private String bid;
    
    
  
    /**
     * Creates a new instance of AuctionView
     */
    public AuctionView() {
    }
    
    public String goToAuction(){
        auction = auctionFacade.find(Long.parseLong(id));
        return "auction";
    }
    
    public String BidOnAuction(){
        String url="";
        Integer currentBid;
        try{
             currentBid=Integer.parseInt(bid);
        }catch(NumberFormatException e){
            return "";
        }
        if(currentBid != null && currentBid > auction.getBid() ){
            auction.setBid(currentBid);
            url= "index";
        }
        return url;
    }
    
   
      public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    
}
