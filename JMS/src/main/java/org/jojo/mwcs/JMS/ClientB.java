package org.jojo.mwcs.JMS;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import lombok.Getter;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.joda.time.DateTime;

public class ClientB {
	static ClientGuiB gui;
	@Getter
	private static String username;
	@Getter
	private static String password;
	@Getter
	private static String url;

	// Abfrage von Publish subscirbe
	public static void main(String[] args) throws JMSException, NamingException {
		gui = new ClientGuiB(new ArrayList<Stock>());
		String[] isns = ServerJMS.getIsns();
		Context ctx = null;
		TopicConnection connect = null;
		TopicSession session = null;
		 username=ActiveMQConnectionFactory.DEFAULT_USER;
		 password=ActiveMQConnectionFactory.DEFAULT_USER;
		 url=ActiveMQConnectionFactory.DEFAULT_BROKER_URL;
		if(args.length==3){
			username=args[0];
			password=args[1];
			url=args[2];
		}

		try {
			ctx = new InitialContext();
			ActiveMQConnectionFactory fact = new ActiveMQConnectionFactory(username,password,url);
			connect = fact.createTopicConnection();
			session = connect.createTopicSession(false,
					Session.AUTO_ACKNOWLEDGE);
			// für alle subscriben
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
							System.out.println("Got Stock! Name:" + name
									+ "Price:" + price + " From "
									+ time.toString());
							gui.insertStock(tmp);
						} catch (JMSException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
						
							e.printStackTrace();
						} catch (InterruptedException e) {
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

}
