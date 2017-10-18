/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.AppUserFacade;
import boundary.AuctionFacade;
import entities.AppUser;
import entities.Auction;
import enumclasses.CategoryType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import singelton.SingeltonClass;

/**
 *
 * @author Sathiesh
 */
@Named(value = "yourSummaryView")
@RequestScoped
public class YourSummaryView {
 @EJB
    AuctionFacade auctionFacade;
    AppUserFacade appUserFacade;
    SingeltonClass singelton;
    List<Auction> auctions;
    private int newRating;
    /**
     * Creates a new instance of YourSummaryView
     */
    public YourSummaryView() {
      singelton= SingeltonClass.getInstance();
      auctions = new ArrayList();
      newRating = 1;
      getYourPurchases();
    }

    public List<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
    }

    public int getNewRating() {
        return newRating;
    }

    public void setNewRating(int newRating) {
        this.newRating = newRating;
    }
    
    
      // Returns all auctions
    public List<Auction> getYourPurchases(){
        try{
            List<Auction> temp = auctionFacade.findYourPurchases(singelton.getUser().getId());
            if(temp != null){
                return temp;
            }else{
                throw new Exception();
            }
        }catch(Exception e){
            return new ArrayList();
        }
    }
    public void goToYourPurchases() throws IOException{
        boolean loggedIn=singelton.isLoggedIn();
        String uri=(loggedIn)?"yourSummary.xhtml":"login.xhtml";      
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
        response.sendRedirect(uri);
    }
    public void RateAuction(Auction auction){
        auction.setRating(newRating);
        auctionFacade.edit(auction);
        AppUser user = auction.getAuctionOwner();
        List<Double> ratings = user.getFeedbacks();
        ratings.add(new Double(newRating));
        user.setFeedbacks(ratings);
        appUserFacade.edit(user);
    }
   
    
}
