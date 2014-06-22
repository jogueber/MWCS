package org.jojo.mwcs.JMS;

import java.util.List;

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.Session;
import lombok.Setter;

import org.apache.activemq.ActiveMQConnectionFactory;
//Aufgabe d handling of Single Requests
public class SendSingle implements MessageListener, Runnable {
	@Setter
	private List<Stock> stocks;
	private MessageProducer prod;
	private QueueSession session;

	@Override
	public void onMessage(Message message) {
		try {
			if (!(message instanceof MapMessage)) {
				return;
			}
			String name = (String) message.getStringProperty(ServerJMS
					.getNAMECON());
			Stock toReturn = null;
			for (Stock s : stocks) {
				if (s.getName().equals(name)) {
					toReturn = s;
					break;
				}
			}
			Message response;
			if (toReturn == null) {
				response = session.createTextMessage("Stock not Found");
			} else {
				response = session.createMapMessage();
				response.setStringProperty(ServerJMS.getISNCON(),
						toReturn.getIsn());
				response.setStringProperty(ServerJMS.getNAMECON(),
						toReturn.getName());
				response.setDoubleProperty(ServerJMS.getPRICECON(),
						toReturn.getPrice());
				response.setStringProperty(ServerJMS.getTIMECON(), toReturn
						.getTime().toString());
			}
			response.setJMSCorrelationID(message.getJMSCorrelationID());
			prod.send(message.getJMSReplyTo(), response);

		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		try {
			ActiveMQConnectionFactory mqfac = new ActiveMQConnectionFactory(
					"admin", "admin",
					ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
			QueueConnection con = mqfac.createQueueConnection("admin", "admin");
			session = con.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination dest = session.createQueue(RequestSingle
					.getRequestName());
			MessageConsumer consumer = session.createConsumer(dest);
			consumer.setMessageListener(this);
			prod = session.createProducer(null);
			prod.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			while (true) {
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}
