package org.jojo.mwcs.JMS;

import java.util.ArrayList;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
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
			TopicConnectionFactory fact = (TopicConnectionFactory) ctx
					.lookup("ConnectionFactory");
			connect = fact.createTopicConnection();
			session = connect.createTopicSession(false,
					Session.AUTO_ACKNOWLEDGE);
			connect.start();
			for (String top : isns) {
				Topic topic;
				try {
					topic = (Topic) ctx.lookup(top);
				} catch (NameNotFoundException ex) {
					topic = session.createTopic(top);
					ctx.bind(top, topic);
				}
				TopicSubscriber subscrib = session.createSubscriber(topic);

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
							gui.insertStock(tmp);
						} catch (JMSException e) {
							e.printStackTrace();
						}

					}
				};
				subscrib.setMessageListener(list);
				connect.start();

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
