/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;


import entities.Auction;
import boundary.AuctionFacade;
import static com.sun.faces.facelets.util.Path.context;
import entities.AppUser;
import entities.Bid;
import enumclasses.CategoryType;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletResponse;
import singelton.SingeltonClass;
import java.lang.Exception;
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
    SingeltonClass singelton;
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
        singelton = SingeltonClass.getInstance();
        this.categorys = Arrays.asList(CategoryType.values());        

    }

    public List<CategoryType> getCategorys() {
        return categorys;
    }
    
    //send user to auctionSchema.xhtml if user is authorized
    public String goToNewAuction(){
        //TODO check if user is logged in or not. true if logged in
        boolean loggedIn=singelton.isLoggedIn();
        String uri=(loggedIn)?"auctionSchema":"login";      
        return uri;
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
    
     // Saves the auctions and then returns the string path "index"
    public void postAuction() throws Exception{
        AppUser user = singelton.getUser();
        auction.setStatus(false);
        auction.setAuctionOwner(user);
        auction.setFinished(false);
        this.auctionFacade.createAuction(auction, bid, user);
        auction = new Auction();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
        response.sendRedirect("yourAuctions.xhtml");
    }
}
    

