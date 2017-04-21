package ru.ite.ee.ejb.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: MyMDB
 */
@MessageDriven(mappedName = "jms/QueueFromClientToServer")
public class MyMDB implements MessageListener {

	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        if(message instanceof TextMessage) {
        	TextMessage tm = (TextMessage)message;
        	try {
				System.out.println(tm.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
        }
    }

}
