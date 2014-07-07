package org.jojo.mwcs.JMS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

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
import javax.jms.TemporaryQueue;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.joda.time.DateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
// Aufgabe d
@AllArgsConstructor
public class RequestSingle implements ActionListener, MessageListener {
	ClientGuiB gui;
	@Getter
	private static final String requestName = "REQUEST";
	//Anfrage Senden
	@Override
	public void actionPerformed(ActionEvent e) {
		if (gui.stockName.getText() == null
				|| gui.stockName.getText().equals(" "))
			return;
		try {
			ActiveMQConnectionFactory mqfac = new ActiveMQConnectionFactory(
					ActiveMQConnectionFactory.DEFAULT_USER, ActiveMQConnectionFactory.DEFAULT_PASSWORD,
					ActiveMQConnectionFactory.DEFAULT_BROKER_URL);

			QueueConnection con = mqfac.createQueueConnection();
			QueueSession session = con.createQueueSession(false,
					Session.AUTO_ACKNOWLEDGE);
			Destination re = session.createQueue(requestName);
			MessageProducer prod = session.createProducer(re);
			TemporaryQueue tempQue = session.createTemporaryQueue();
			MessageConsumer tempCon = session.createConsumer(tempQue);
			tempCon.setMessageListener(this);
			MapMessage msg = session.createMapMessage();
			msg.setString(ServerJMS.getNAMECON(), gui.stockName.getText());
			msg.setJMSReplyTo(tempQue);
			msg.setJMSCorrelationID(UUID.randomUUID().toString());
			prod.send(msg);

		} catch (JMSException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	//Antwort verarbeiten
	@Override
	public void onMessage(Message message) {

		try {
			if (message instanceof TextMessage) {
				System.out.println(((TextMessage) message).getText());
				return;
			}

			String name = message.getStringProperty(ServerJMS.getNAMECON());
			String id = message.getStringProperty(ServerJMS.getISNCON());
			Double price = message.getDoubleProperty(ServerJMS.getPRICECON());
			DateTime time = DateTime.parse(message.getStringProperty(ServerJMS
					.getTIMECON()));
			Stock tmp = new Stock(name, id, price, time);
			System.out.println("Got Stock! Name:" + name + "Price:" + price
					+ " From " + time.toString());
			gui.insertStock(tmp);
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
