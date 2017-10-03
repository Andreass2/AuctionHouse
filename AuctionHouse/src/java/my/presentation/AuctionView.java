/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author addi2
 */
@Named(value = "auctionView")
@RequestScoped
public class AuctionView {
    String id;

  
    /**
     * Creates a new instance of AuctionView
     */
    public AuctionView() {
    }
    
    public String goToAuction(){
        return "auction";
    }
    
   
      public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
