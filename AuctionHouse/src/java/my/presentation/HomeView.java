/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.AuctionFacade;
import entities.Auction;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author addi2
 */
@ManagedBean(name="homeView")
@ViewScoped
public class HomeView {
    @EJB
    AuctionFacade auctionFacade;
    List<Auction> auctions;
    List<Auction> filteredAuctions;
    String searchText;

    /**
     * Creates a new instance of HomeView
     */
    public HomeView() {
    }
    @PostConstruct
    public void init(){
        getAllAuctions();
        searchText="";
    }
    public List<Auction> getAuctions() {
        return auctions;
    }
    public void search(){
        getAllAuctions();
        filteredAuctions =  auctions.stream().filter((a) -> (a.getAuctionName().toUpperCase().contains(searchText.toUpperCase()))).collect(Collectors.toList());
    }

    public List<Auction> getFilteredAuctions() {
        return filteredAuctions;
    }

    public void setFilteredAuctions(List<Auction> filteredAuctions) {
        this.filteredAuctions = filteredAuctions;
    }

    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
    
    
     // Returns the total number of auctions
    public int getNumberOfAuctions(){
       return auctionFacade.findAll().size();
    }
    
      // Returns all auctions
    public void getAllAuctions(){
        try{
            auctions =  auctionFacade.findAll();
        }catch(Exception e){
            auctions = new ArrayList();
        }
 
    }
    
    public String goToHome(){
        return "index";
    }
}
