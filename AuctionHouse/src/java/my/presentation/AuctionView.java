/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.AuctionFacade;
import entities.Auction;
import enumclasses.CategoryType;
import java.io.IOException;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author addi2
 */
@Named(value = "auctionView")
@RequestScoped
//scoped viewscoe so that it lives so long as the jsf page is redisplaed. wont work
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
    
    public String BidOnAuction()throws IOException {
        auction = auctionFacade.find(Long.parseLong(id));
         Integer currentBid=null;
         FacesContext context = FacesContext.getCurrentInstance();
         HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
        try{
            Thread.sleep(1000);
            currentBid=Integer.parseInt(bid);
        }catch(Exception e){
            response.sendRedirect("auction.xhtml?errorINT");
        }
        if(currentBid != null && currentBid > auction.getBid() ){
            auction.setBid(currentBid);
            response.sendRedirect("login.xhtml");
        }else if (currentBid != null && currentBid < auction.getBid() ){
            response.sendRedirect("auction.xhtml?errorBID");
        }
        return "";
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

    public void setAuction(Auction auction) {
        this.auction = auction;
    }
    

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getBid() {
        return bid;
    }

    
}
