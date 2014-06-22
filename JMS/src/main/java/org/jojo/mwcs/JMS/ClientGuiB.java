package org.jojo.mwcs.JMS;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueRequestor;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.joda.time.DateTime;

import sun.awt.SunToolkit.InfiniteLoop;
import lombok.Getter;
import lombok.Setter;

public class ClientGuiB extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@Getter
	@Setter
	private Set<Stock> rows;
	JTextArea area;
	@Getter
	@Setter
	JTextField stockName;

	public ClientGuiB(List<Stock> stocks) {
		super("Client");

		setLayout(new BorderLayout());
		setSize(300, 300);
		setLocation(300, 300);
		rows = new HashSet<>(stocks);

		StringBuffer b = new StringBuffer();
		for (Stock s : stocks) {
			b.append(s.getIsn());
			b.append(s.getName());
			b.append(s.getPrice());
			b.append(s.getTime().toString());
			b.append(System.lineSeparator());
		}
		area = new JTextArea(b.toString());
		area.setLineWrap(true);
		area.setEditable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addWindowListener(new Serial(this));
		JPanel addPanel = new JPanel(new FlowLayout());
		JLabel nameLabel = new JLabel("Type Name of Stock");
		stockName = new JTextField("");
		JButton ok = new JButton("Request Stock");
		ok.addActionListener(new RequestSingle(this));
		getContentPane().add(area);
		addPanel.add(nameLabel);
		addPanel.add(ok);
		getContentPane().add(addPanel);
		pack();
		setVisible(true);
	}

	public void insertStock(Stock s) {
		rows.add(s);
		StringBuffer b = new StringBuffer();
		for (Stock t : rows) {
			b.append(t.getIsn());
			b.append(t.getName());
			b.append(t.getPrice());
			b.append(t.getTime().toString());
			b.append(System.lineSeparator());
		}
		area.setText(b.toString());
	}

	public void updateStocks() {
		StringBuffer b = new StringBuffer();
		for (Stock t : rows) {
			b.append(t.getIsn());
			b.append(t.getName());
			b.append(t.getPrice());
			b.append(t.getTime().toString());
			b.append(System.lineSeparator());
		}
		area.setText(b.toString());

	}

	private static class Serial extends WindowAdapter {
		private List<Stock> toSerial;

		private List<Stock> recovered;

		private ClientGuiB gui;

		private static final String serialpath = System
				.getProperty("java.io.tmpdir") + "\\";

		private static final String fileName = "StocksSerial.xml";

		public Serial(ClientGuiB a) {
			toSerial = new ArrayList<Stock>(a.getRows());
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// if (toSerial == null || toSerial.isEmpty()) {
			// return;
			// }
			try {
				JAXBContext context = JAXBContext.newInstance(JaxbList.class);
				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				Files.deleteIfExists(Paths.get(serialpath + fileName));
				Path f = Files.createFile(Paths.get(serialpath + fileName));
				OutputStream stream = Files.newOutputStream(f,
						StandardOpenOption.WRITE, StandardOpenOption.APPEND);
				Stock testStock = new Stock("Test", "hallo", 30, DateTime.now());
				Stock testStock2 = new Stock("test2", "e", 32, DateTime.now());
				toSerial = new ArrayList<Stock>();
				toSerial.add(testStock);
				toSerial.add(testStock2);
				JaxbList list = new JaxbList(toSerial);

				m.marshal(list, stream);
				stream.flush();
				stream.close();
			} catch (JAXBException t) {
				t.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		@Override
		public void windowOpened(WindowEvent e) {
			if (!Files.exists(Paths.get(serialpath + fileName))) {

				return;
			}
			try {
				JAXBContext context = JAXBContext.newInstance(JaxbList.class);
				Unmarshaller un = context.createUnmarshaller();
				JaxbList toadd = (JaxbList) un.unmarshal(Paths.get(
						serialpath + fileName).toFile());
				gui.setRows(new HashSet<Stock>(toadd.getStocks()));

			} catch (JAXBException e1) {
				e1.printStackTrace();
			}
		}

		@XmlRootElement(name = "Stockliste")
		@XmlAccessorType(XmlAccessType.FIELD)
		private static class JaxbList {
			public JaxbList() {
			}

			public JaxbList(List<Stock> stocks) {
				super();
				this.stocks = stocks;
			}

			public List<Stock> getStocks() {
				return this.stocks;
			}

			public void setStocks(List<Stock> stocks) {
				this.stocks = stocks;
			}

			@XmlElement(name = "stocks", type = Stock.class)
			private List<Stock> stocks;
		}
	}
}
