/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.soap;

import boundary.AuctionFacade;
import entities.Auction;
import entities.Bid;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.ejb.Stateless;
import javax.jms.Message;

/**
 *
 * @author fredrik
 */
@WebService(serviceName = "SoapService")
@Stateless()
public class SoapService {
    @EJB
    AuctionFacade auctionFacade;
    /**
     * This is a sample web service operation
     */
   /*@WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    */
    
    @WebMethod(operationName = "getActiveAuctions")
    public List<Auction> getActiveAuctions() {
        return auctionFacade.findAllAuctions();
    }
    
    @WebMethod(operationName = "bidForAuction")
    public String bidForAuction(String newBid, String id) {
        Integer currentBid = null;  
        try{
            currentBid=Integer.parseInt(newBid);
        }catch(NumberFormatException e){
            return"Exception";
        }
        if( currentBid > auctionFacade.getBid(Long.parseLong(id)).getBid() ){
            Bid bid = new Bid();
            
            bid.setBid(currentBid);
            bid.setBidOwner(null);
            bid.setAuction(auctionFacade.find(Long.parseLong(id)));
            this.auctionFacade.saveBid(bid);
        }else if (currentBid < auctionFacade.getBid(Long.parseLong(id)).getBid() ){
            return "For lavt";
        }
    return "Fungerer"; 
    }
}
