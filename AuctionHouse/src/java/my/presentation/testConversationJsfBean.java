/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.AuctionFacade;
import entities.Auction;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author addi2
 */
@Named(value = "testConversationJsfBean")
@ConversationScoped
public class testConversationJsfBean implements Serializable {
    private String id;
    @EJB
    AuctionFacade auctionFacade;
    private Auction auction;
    private String bid;
    @Inject
    private Conversation conversation;

    /**
     * Creates a new instance of testConversationJsfBean
     */
    public testConversationJsfBean() {
    }
    
    
    // Control start and end of conversation
    public void start() {
        conversation.begin();
    }

    public void end() {
        conversation.end();
    }

    // Navigation
    public String onClick() {
       start();
       auction = auctionFacade.find(Long.parseLong(id));
        return "auction";
    }

public String onKeepGoing()throws IOException{
    Integer currentBid=null;
    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
    
    if(bid.equals("") || conversation == null) {
        return "auction?ERROR";
    }
    else{
     try{
            currentBid=Integer.parseInt(bid);
        }catch(Exception e){
            return"auction?errorNEEDStoBEnumber";
        }
        if(currentBid != null && currentBid > auction.getBid() ){
            auction.setBid(currentBid);
            //set bid owner id TODO
            this.auctionFacade.edit(auction);
            end();
            response.sendRedirect("index.xhtml?Success");
        }else if (currentBid != null || currentBid < auction.getBid() ){
            return "auction?BIDtoLOW";
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

    public void setAuction(Auction auction) {
        this.auction = auction;
    }
}
