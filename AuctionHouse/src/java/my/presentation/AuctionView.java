/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.AuctionFacade;
import entities.Auction;
import entities.Bid;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import singelton.SingeltonClass;

/**
 *
 * @author addi2
 */
@Named(value = "auctionView")
@ConversationScoped
public class AuctionView implements Serializable {
    private String id;
    @EJB
    AuctionFacade auctionFacade;
    private Auction auction;
    private String bid;
    @Inject
    private Conversation conversation;
    SingeltonClass singelton;


    /**
     * Creates a new instance of testConversationJsfBean
     */
    public AuctionView() {
        singelton=SingeltonClass.getInstance();
    }
    
    
    // Control start and end of conversation
    public void start() {
        conversation.begin();
    }

    public void end() {
        conversation.end();
    }

    // Navigation
    public String onStart() {
       start();
       auction = auctionFacade.find(Long.parseLong(id));
        return "auction";
    }

public String bidOnAuction()throws IOException{
    Integer currentBid=null;
    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();   
    if((bid.equals("") || conversation == null)&& !singelton.isLoggedIn() ) {
        return "";
    }
    else{
     try{
            currentBid=Integer.parseInt(bid);
        }catch(NumberFormatException e){
            return"";
        }
        if( currentBid > auctionFacade.getBid(Long.parseLong(id)).getBid() ){
            Bid bid = new Bid();
            
            bid.setBid(currentBid);
            bid.setUser(singelton.getUser());
            bid.setAuction(auction);
            this.auctionFacade.saveBid(bid);
            end();
            response.sendRedirect("index.xhtml?Success");
        }else if (currentBid < auctionFacade.getBid(Long.parseLong(id)).getBid() ){
            return "";
        }
    }
    return "";
}

// Getters & Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }


    public Auction getAuction() {
        return auction;
    }

    /**
     *
     * @return
     */
    public int getAuctionBid(){
        return this.auctionFacade.getBid(Long.parseLong(id)).getBid();
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }
}
