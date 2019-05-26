package se.skillytaire.belastingdienst.ee.service.jms;

import javax.annotation.PostConstruct;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MessageDriven(name = "MailListener",
activationConfig = {  
   @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),  
   @ActivationConfigProperty(propertyName="destination", propertyValue="sendmailQueueID"),  
   @ActivationConfigProperty(propertyName="acknowledgeMode", propertyValue="Auto-acknowledge")  
})
public class MailListener implements MessageListener {
	private static Logger log = LoggerFactory.getLogger(MailListener.class);
	
    @PostConstruct
    public void init() {
        System.out.println("Initialize mailListener");
    }
	
	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
			String txt = textMessage.getText();
			System.out.println("-- msg: " + txt);

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
