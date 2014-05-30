package aufgabe3;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import callback.Client_Handler.Stock;

public class Server {

	public final static String[] symbols = { "ADS.DE", "ALV.DE", "BAS.DE",
			"BAYN.DE", "BEI.DE", "BMW.DE", "CBK.DE", "CON.DE", "DAI.DE",
			"DB1.DE", "DBK.DE", "DPW.DE", "DTE.DE", "EOAN.DE", "FME.DE",
			"FRE.DE", "HEI.DE", "HEN3.DE", "IFX.DE", "LHA.DE", "LIN.DE",
			"LXS.DE", "MRK.DE", "MUV2.DE", "RWE.DE", "SAP.DE", "SDF.DE",
			"SIE.DE", "TKA.DE", "VOW3.DE" };

	public final static String query = "Select * from select * from yahoo.finance.quote where symbol in(";

	public final static String yahooAdress = "http://query.yahooapis.com/v1/public/yql?";
	public final static String json = "&format=json";

	private static ServerRegisterImpl regist;

	public static void main(String[] args) throws InvalidName, AdapterInactive {
		ORB orb = ORB.init(args, null);

		org.omg.CORBA.Object objRef = orb
				.resolve_initial_references("NameService");
		NamingContext ncRef = NamingContextHelper.narrow(objRef);

		regist = new ServerRegisterImpl();
		regist.setOrb(orb);

		POA rootpoa = POAHelper.narrow(orb
				.resolve_initial_references("RootPOA"));
		rootpoa.the_POAManager().activate();

		orb.run();
		while (true) {
			try {
				List<Stock> refreshed = getDataFromYahoo();
				for (Stock s : refreshed)
					regist.refresh(s);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static List<Stock> getDataFromYahoo() throws IOException,
			ParserConfigurationException, SAXException {
		String unisymbols = StringUtils.join(symbols, "\",\"");
		String toSend = query + unisymbols + "\")";
		String urlFull = yahooAdress + URLEncoder.encode(toSend, "UTF-8")
				+ json;
		List<Stock> tmp = new ArrayList<Stock>();
		URL url = new URL(urlFull);
		InputStream is = url.openStream();

		DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
		DocumentBuilder bui = fac.newDocumentBuilder();
		Document doc = bui.parse(is);
		NodeList values = doc.getElementsByTagName("quote");
		for (int i = 0; i < values.getLength(); i++) {
			Stock tmpStock = new Stock();
			Node node = values.item(i);
			String symbol = node.getAttributes().getNamedItem("symbol")
					.getNodeValue();
			NodeList childs = node.getChildNodes();
			for (int t = 0; t < childs.getLength(); t++) {
				if (childs.item(t).getNodeName().equals(("Name"))) {
					tmpStock.name = childs.item(t).getNodeValue();
				}
				if (childs.item(t).getNodeName().equals("LastTradePriceOnly")) {
					tmpStock.price = Double.parseDouble(childs.item(t)
							.getNodeValue());
				}
			}
			tmp.add(tmpStock);
		}
		return tmp;
	}

}
