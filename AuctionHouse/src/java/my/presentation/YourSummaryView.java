/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.AppUserFacade;
import boundary.AuctionFacade;
import entities.Auction;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

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
    List<Auction> auctions;
    private int newRating;
    /**
     * Creates a new instance of YourSummaryView
     */
    public YourSummaryView() {
      auctions = new ArrayList();
      newRating = 1;
      //getYourPurchases();
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
       String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        try{
            List<Auction> temp = auctionFacade.findYourPurchases(username);
            if(temp != null){
                return temp;
            }else{
                throw new Exception();
            }
        }catch(Exception e){
            return new ArrayList();
        }
    }
    public String goToYourPurchases() throws IOException{  
        return "/users/yourSummary?faces-redirect=true";
    }
    /*public void RateAuction(Auction auction){
        auction.setRating(newRating);
        auctionFacade.edit(auction);
        AppUser user = auction.getAuctionOwner();
        List<Double> ratings = user.getFeedbacks();
        ratings.add(new Double(newRating));
        user.setFeedbacks(ratings);
        appUserFacade.edit(user);
    }*/
   
    
}
