package org.jojo.mwcs.JMS;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.naming.*;
import javax.jms.*;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.joda.time.DateTime;

public class ServerJMS {

	private static List<Stock> stocks;
	@Getter
	private final String SERVERNAME = "LOCAL SERVER";

	final static String[] names = { "addidas", "Deutsche Bank", "SAP" };
	@Getter
	private static final String[] isns = { "t354", "DAB45", "SAPGER" };
	@Getter
	private final static String ISNCON = "ISN";
	@Getter
	private final static String PRICECON = "PRICE";
	@Getter
	private final static String TIMECON = "TIME";
	@Getter
	private final static String NAMECON = "NAME";

	static Context ctx;
	private static BrokerService broker;

	public static void main(String[] args) throws URISyntaxException, Exception {
		try {
			// BrokerService broker = new BrokerService();
			// TransportConnector connector = new TransportConnector();
			// connector.setUri(new URI("tcp://localhost:61616"));
			// broker.addConnector(connector);
			// broker.start();
			//
			BrokerService broker = new BrokerService();
			broker.setPersistent(false);
			broker.setUseShutdownHook(false);
			// configure the broker
			broker.addConnector("tcp://localhost:61616");
			broker.addConnector("vm://localhost");
			broker.start();
			ActiveMQConnectionFactory mqfac = new ActiveMQConnectionFactory(
					"vm://localhost");
			TopicConnection connect = mqfac.createTopicConnection();
			connect.start();
			TopicSession session = connect.createTopicSession(false,
					Session.AUTO_ACKNOWLEDGE);
			ExecutorService exe = Executors.newCachedThreadPool();
			setUpStocks();
			for (Stock s : stocks) {
				Topic topic;
				topic = session.createTopic(s.getName());
				Runnable r = new StockRunner(s, topic, session);
				exe.execute(r);
			}

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void setUpStocks() {
		if (names.length != isns.length) {
			return;
		}
		stocks = new ArrayList<Stock>();
		int i = 0;
		Random r = new Random();
		for (String name : names) {
			Stock t = new Stock(name, isns[i], r.nextInt(500), DateTime.now());
			i++;
			stocks.add(t);
		}
	}

	@AllArgsConstructor
	private static class StockRunner implements Runnable {
		private Stock stock;
		private Topic topic;
		private TopicSession session;

		public void run() {
			try {
				stock = refreshStock(stock);
				TopicPublisher publisher = session.createPublisher(topic);
				MapMessage msg = session.createMapMessage();
				msg.setStringProperty(ISNCON, stock.getIsn());
				msg.setStringProperty(NAMECON, stock.getName());
				msg.setDouble(PRICECON, stock.getPrice());
				msg.setString(TIMECON, stock.getTime().toString());
				publisher.send(msg);
				Long timeout = (long) (5000 + Math.rint(10000 * Math.random()));
				try {
					Thread.sleep(timeout.longValue());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}

		private Stock refreshStock(Stock s) {
			s.setPrice((-0.2 + Math.random() * 0.4) * s.getPrice());
			s.setTime(DateTime.now());
			return s;
		}
	}

}
