package se.skillytaire.belastingdienst.ee.service.jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
@Stateless
public class MailSender {

	@Resource(lookup = "jms/JmsFactory")
	private ConnectionFactory factory;
	@Resource(lookup = "jms/sendmail")
	private Queue queue;

	public void send(MailTo mail) {
		TextMessage message;
		try (Connection connection = factory.createConnection();
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				MessageProducer producer = session.createProducer(queue)) {
			System.out.println("Sending a new message");
			message = session.createTextMessage();
			message.setText("FF Wat tekst "+mail.getTo());
			producer.send(message);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
