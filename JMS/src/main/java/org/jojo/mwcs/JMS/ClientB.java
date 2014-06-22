package org.jojo.mwcs.JMS;

import java.util.ArrayList;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.jndi.ActiveMQInitialContextFactory;
import org.apache.avalon.framework.activity.Suspendable;
import org.joda.time.DateTime;

public class ClientB {
	static ClientGuiB gui;

	public static void main(String[] args) throws JMSException, NamingException {
		gui = new ClientGuiB(new ArrayList<Stock>());
		String[] isns = ServerJMS.getIsns();
		Context ctx = null;
		TopicConnection connect = null;
		TopicSession session = null;

		try {
			ctx = new InitialContext();
			ActiveMQConnectionFactory fact = new ActiveMQConnectionFactory(
					ActiveMQConnectionFactory.DEFAULT_USER,
					ActiveMQConnectionFactory.DEFAULT_PASSWORD,
					ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
			connect = fact.createTopicConnection();
			session = connect.createTopicSession(false,
					Session.AUTO_ACKNOWLEDGE);
			for (String top : isns) {

				Topic dest = session.createTopic(top);

				MessageConsumer subscrib = session.createConsumer(dest);

				MessageListener list = new MessageListener() {

					@Override
					public void onMessage(Message message) {
						try {
							String name = message.getStringProperty(ServerJMS
									.getNAMECON());
							String id = message.getStringProperty(ServerJMS
									.getISNCON());
							Double price = message.getDoubleProperty(ServerJMS
									.getPRICECON());
							DateTime time = DateTime.parse(message
									.getStringProperty(ServerJMS.getTIMECON()));
							Stock tmp = new Stock(name, id, price, time);
							System.out.println("Got Stock! Name:"+name+"Price:"+price+" From "+time.toString());
							gui.insertStock(tmp);
						} catch (JMSException e) {
							e.printStackTrace();
						}

					}
				};
				subscrib.setMessageListener(list);

			}
			connect.start();
			while (true) {
			}
		} finally {

			try {
				if (null != session)
					session.close();
			} catch (Exception ex) {/* ok */
			}
			try {
				if (null != connect)
					connect.close();
			} catch (Exception ex) {/* ok */
			}
			try {
				if (null != ctx)
					ctx.close();
			} catch (Exception ex) {/* ok */
			}
		}

	}

	public static void addTopic(String isn) {

	}
}
