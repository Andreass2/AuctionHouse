/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;


import entities.Auction;
import boundary.AuctionFacade;
import enumclasses.CategoryType;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.util.Arrays;
import java.util.List;
import javax.faces.context.FacesContext;


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
    int bid;
    List<CategoryType> categorys; 

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }


   
    /**
     * Creates a new instance of AuctionSchemaView
     */
    public AuctionSchemaView() {
        this.auction=new Auction();
        this.setBid(0);
        this.categorys = Arrays.asList(CategoryType.values());        

    }

    public List<CategoryType> getCategorys() {
        return categorys;
    }
    
    public String goToNewAuction(){  
        return "/users/auctionSchema?faces-redirect=true";
    }
    
    //gives faclets acces to write data to entity object
    public Auction getAuction(){
        return auction;
    }
    
    public long getTimeLeftMillies(Long auctionId){
        Auction auction = auctionFacade.find(auctionId);
        long difference = 0;
        if(auction != null && auction.getTimeCreated()!= null){
            difference = 300000 - ((new Date()).getTime() - auction.getTimeCreated().getTime());
        }
        return difference;
    }
    
    public long getTimeLeftMinutes(Long auctionId){
        return (getTimeLeftMillies(auctionId)/60000)%60;
    }
    
    public long getTimeLeftSeconds(Long auctionId){
        long seconds = (getTimeLeftMillies(auctionId)/1000)%60;
        if(seconds<0){
            seconds = 0;
            auctionFacade.setFinished(auctionId);
        }
        return seconds;
    }
    
    public String postAuction() throws Exception{
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        auction.setStatus(false);
        auction.setAuctionOwner(username);
        auction.setFinished(false);
        this.auctionFacade.createAuction(auction, bid, username);
        auction = new Auction();
        return "/users/yourAuctions?faces-redirect=true";
    }
}
    

