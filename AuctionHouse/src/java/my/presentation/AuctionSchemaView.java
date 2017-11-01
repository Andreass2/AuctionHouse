/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;


import entities.Auction;
import boundary.AuctionFacade;
<<<<<<< HEAD
=======
import entities.AppUser;
>>>>>>> 7448444f063f89c8dea91c4a182ee233c57843c6
import enumclasses.CategoryType;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.util.Arrays;
import java.util.List;
import javax.faces.context.FacesContext;
import publisher.PublisherPlainJava;


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
    private PublisherPlainJava publisher;

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
        publisher= new PublisherPlainJava();
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
        if(seconds<0 && !auctionFacade.find(auctionId).isFinished() ){
            seconds = 0;
            auctionFacade.setFinished(auctionId);
            publishWinner(auctionId);
        }
        return seconds;
    }
    
<<<<<<< HEAD
    public String postAuction() throws Exception{
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
=======
    private void publishWinner(Long auctionId){
        String name;
        String productName;
        String urlLink;
        
        name=auctionFacade.getBid(auctionId).getUser().getEmail();
        Auction auct =auctionFacade.find(auctionId);
        productName = auct.getAuctionName();
        urlLink= "https://localhost:8181/AuctionHouse/faces/auction.xhtml?cid="+auctionId;
        
        // navn på vinner, product y navn, link til produktet
        publisher.PublishAuctionWinner(name,productName,urlLink);
    }
    
    
    
     // Saves the auctions and then returns the string path "index"
    public void postAuction() throws Exception{
        AppUser user = singelton.getUser();
>>>>>>> 7448444f063f89c8dea91c4a182ee233c57843c6
        auction.setStatus(false);
        auction.setAuctionOwner(username);
        auction.setFinished(false);
        this.auctionFacade.createAuction(auction, bid, username);
        auction = new Auction();
        return "/users/yourAuctions?faces-redirect=true";
    }
}
    

