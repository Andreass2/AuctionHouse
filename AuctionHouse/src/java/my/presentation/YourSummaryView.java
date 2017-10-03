/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.AuctionFacade;
import entities.Auction;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
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
    SingeltonClass singelton;
    /**
     * Creates a new instance of YourSummaryView
     */
    public YourSummaryView() {
      singelton= SingeltonClass.getInstance();
    }
      // Returns all auctions
   
    
}
