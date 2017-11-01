/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easysoapjmsclient;

import java.util.List;
import java.util.Scanner;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.ws.WebServiceRef;
import service.soap.Auction;
import service.soap.SoapService_Service;

/**
 *
 * @author addi2
 */
public class Main {

    @WebServiceRef(wsdlLocation = "META-INF/wsdl/localhost_8080/SoapService/SoapService.wsdl")
    private static SoapService_Service service;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //getActiveAuctions()
        
        
        new Thread(() -> {
    // Insert some method call here.
        try{
            while(true){
         // get the initial context
         InitialContext ctx = new InitialContext();

         // lookup the topic object
         Topic topic = (Topic) ctx.lookup("jmsTopic");

         // lookup the topic connection factory
         TopicConnectionFactory connFactory = (TopicConnectionFactory) ctx.
             lookup("jmsConnectionFactory");

         // create a topic connection
         TopicConnection topicConn = connFactory.createTopicConnection();

         // create a topic session
         TopicSession topicSession = topicConn.createTopicSession(false,
             Session.AUTO_ACKNOWLEDGE);

         // create a topic subscriber
         TopicSubscriber topicSubscriber = topicSession.createSubscriber(topic);

         // start the connection
         topicConn.start();

         // receive the message
         TextMessage message = (TextMessage) topicSubscriber.receive();
         if(message != null){
             System.out.println("An auction is over");
             //print all active auctins again (minus the one who just finished + new ones)
             printAllAuctions();
             message=null;
             
         }
        }
        }catch(JMSException  | NamingException ee){
            //TODO
        }
      
    }).start();
        
        while(true){

        Scanner sc = new Scanner(System.in); 
        
        printAllAuctions();
        
      
        System.out.println("Write id: ");
        int id = sc.nextInt();
        System.out.println("Write bid: ");
        int bid = sc.nextInt(); 
         String a= bidForAuction(bid+"", id+"");
        System.out.println("nytt bud:" +a);
        
        }
        
    }

    private static String bidForAuction(java.lang.String arg0, java.lang.String arg1) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        service.soap.SoapService port = service.getSoapServicePort();
        return port.bidForAuction(arg0, arg1);
    }

    private static java.util.List<service.soap.Auction> getActiveAuctions() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        service.soap.SoapService port = service.getSoapServicePort();
        return port.getActiveAuctions();
    }
    
    
    
    
    
    static private void printAllAuctions(){
          List<Auction> list = getActiveAuctions();
        for(Auction auction: list) {
            int bid=0;
            if(auction.getBids().size() >0 ){
               bid= auction.getBids().get(auction.getBids().size()-1).getBid();
            }
                
        System.out.println(auction.getAuctionName() + " || "  + "bid: "+bid+ " || " + "id: "+auction.getId() );

        }
    }
}
