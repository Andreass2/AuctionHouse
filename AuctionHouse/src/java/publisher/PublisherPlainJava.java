/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publisher;

import entities.Auction;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author addi2
 */
public class PublisherPlainJava{
     private TopicConnection topicConn;
     private Topic topic;
     private TopicSession topicSession;
     private  TopicConnectionFactory connFactory;
     private TopicPublisher topicPublisher;

    public PublisherPlainJava() {
         try{
            // get the initial context
             InitialContext ctx = new InitialContext();

             // lookup the topic object
              topic = (Topic) ctx.lookup("jmsTopic");

             // lookup the topic connection factory
              connFactory = (TopicConnectionFactory) ctx.
                 lookup("jmsConnectionFactory");

             // create a topic session
              topicSession = topicConn.createTopicSession(false,
                 Session.AUTO_ACKNOWLEDGE);

             // create a topic publisher
              topicPublisher = topicSession.createPublisher(topic);
             topicPublisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);            
        }
        catch(NamingException | JMSException e){
            //TODO 
        }
    }//
    
    public void PublishAuctionWinner(int auctionId)throws Exception{
        
         // create a topic connection
             topicConn = connFactory.createTopicConnection();
         
             // create the "Hello World" message
             TextMessage message = topicSession.createTextMessage();
             message.setText(auctionId+"");

             // publish the messages
             topicPublisher.publish(message);

             // print what we did
             System.out.println("Message published: " + message.getText());

             // close the topic connection
             topicConn.close();     
    }
    
       
        
       
    
   
}
