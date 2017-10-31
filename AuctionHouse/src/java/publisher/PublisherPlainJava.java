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
import my.presentation.AuctionSchemaView;

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
          

             
             
           
        }
        catch(Exception e){
            //TODO 
        }
    }//
    
    public void PublishAuctionWinner(String name, String pName, String url){
            try{
                  // get the initial context
             InitialContext ctx = new InitialContext();

             // lookup the topic object
              topic = (Topic) ctx.lookup("jmsTopic");

             // lookup the topic connection factory
              connFactory = (TopicConnectionFactory) ctx.
                 lookup("jmsConnectionFactory");
                
               
                
                 // create a topic connection
              topicConn = connFactory.createTopicConnection();

             // create a topic session
              topicSession = topicConn.createTopicSession(false,
                 Session.AUTO_ACKNOWLEDGE);

             // create a topic publisher
              topicPublisher = topicSession.createPublisher(topic);
             topicPublisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
             
             
             
             
             // create the message
             TextMessage message = topicSession.createTextMessage();
             message.setText(name+"|"+pName +"|"+url );

             // publish the messages
             topicPublisher.publish(message);

            }
            catch(JMSException | NamingException ee){
                //handle jsm 
                System.exit(0);
            }
    }

}

 