package org.jojo.mwcs.JMS;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
		recoverStocks();
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
		area.setSize(200, 200);
		area.setVisible(true);
		area.setLineWrap(true);
		area.setEditable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addWindowListener(new Serial(this));
		JPanel addPanel = new JPanel(new FlowLayout());
		JLabel nameLabel = new JLabel("Type Name of Stock");
		stockName = new JTextField("");
		stockName.setSize(30, 30);
		JButton ok = new JButton("Request Stock");
		// Single Request Handling
		ok.addActionListener(new RequestSingle(this));
		getContentPane().add((new JPanel()).add(area));
		addPanel.add(nameLabel);
		addPanel.add(ok);
		addPanel.add(stockName);
		getContentPane().add(addPanel);
		pack();
		setVisible(true);
	}

	// deserialisierung
	private void recoverStocks() {

		if (!Files.exists(Paths.get(Serial.getSerialpath()
				+ Serial.getFileName()))) {

			return;
		}
		try {
			JAXBContext context = JAXBContext.newInstance(JaxbList.class);
			Unmarshaller un = context.createUnmarshaller();
			JaxbList toadd = (JaxbList) un.unmarshal(Paths.get(
					Serial.getSerialpath() + Serial.getFileName()).toFile());
			this.setRows(new HashSet<Stock>(toadd.getStocks()));

		} catch (JAXBException e1) {
			e1.printStackTrace();
		}
	}

	public void insertStock(final Stock s) throws InvocationTargetException,
			InterruptedException {
		SwingUtilities.invokeAndWait(new Runnable() {

			@Override
			public void run() {
				rows.add(s);
				StringBuffer b = new StringBuffer();
				for (Stock t : rows) {

					b.append("ISN:" + t.getIsn() + "  ");
					b.append("Name:" + t.getName() + " ");
					b.append("Price: " + t.getPrice() + " Time");
					b.append(t.getTime().toString());
					b.append(System.lineSeparator());
				}
				area.setText(b.toString());
				repaint();

			}
		});

	}

	public void updateStocks() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				StringBuffer b = new StringBuffer();
				area = new JTextArea();
				for (Stock t : rows) {

					b.append("ISN:" + t.getIsn() + "  ");
					b.append("Name:" + t.getName() + " ");
					b.append("Price: " + t.getPrice() + " Time");
					b.append(t.getTime().toString());
					b.append(System.lineSeparator());
				}
				area.setText(b.toString());

			}
		});

	}

	// Aufgabe c Serialisierung
	private static class Serial extends WindowAdapter {
		private List<Stock> toSerial;

		private List<Stock> recovered;

		private ClientGuiB gui;
		@Getter
		private static final String serialpath = System
				.getProperty("java.io.tmpdir") + "\\";
		@Getter
		private static final String fileName = "StocksSerial.xml";

		public Serial(ClientGuiB a) {
			this.gui = a;
		}

		// Serialisieren
		@Override
		public void windowClosing(WindowEvent e) {
			if (toSerial == null || toSerial.isEmpty()) {
				return;
			}
			try {
				toSerial = new ArrayList<Stock>(gui.getRows());
				JAXBContext context = JAXBContext.newInstance(JaxbList.class);
				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				Files.deleteIfExists(Paths.get(serialpath + fileName));
				Path f = Files.createFile(Paths.get(serialpath + fileName));
				OutputStream stream = Files.newOutputStream(f,
						StandardOpenOption.WRITE, StandardOpenOption.APPEND);
				JaxbList list = new JaxbList(toSerial);

				m.marshal(list, stream);
				stream.flush();
				stream.close();
			} catch (JAXBException t) {
				t.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
