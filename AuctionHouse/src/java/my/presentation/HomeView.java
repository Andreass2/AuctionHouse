/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.AuctionFacade;
import entities.Auction;
import enumclasses.CategoryType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author addi2
 */
@ManagedBean(name="homeView")
@RequestScoped
public class HomeView {
    @EJB
    AuctionFacade auctionFacade;
    List<Auction> auctions;
    List<Auction> filteredAuctions;
    List<CategoryType> categories;
    /**
     * Creates a new instance of HomeView
     */
    public HomeView() {
        this.categories = Arrays.asList(CategoryType.values());
    }
    @PostConstruct
    public void init(){
        getAllAuctions();
        this.categories = Arrays.asList(CategoryType.values());
    }
    public List<Auction> getAuctions() {
        return auctions;
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
    
     // Returns the total number of auctions
    public int getNumberOfAuctions(){
       return auctionFacade.findAllAuctions().size();
    }

    public List<CategoryType> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryType> categories) {
        this.categories = categories;
    }
    
      // Returns all auctions
    public void getAllAuctions(){
        try{
            auctions =  auctionFacade.findAllAuctions();
        }catch(Exception e){
            auctions = new ArrayList();
        }
 
    }
    public int getBid(long id){
        return this.auctionFacade.getBid(id).getBid();
    }
    
    public String goToHome(){
        return "/index?faces-redirect=true";
    }
}
