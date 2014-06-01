package aufgabe3;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
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
import callback.Server_Handler.Server_Register;
import callback.Server_Handler.Server_RegisterHelper;

public class Server {

	public final static String[] symbols = { "ADS.DE", "ALV.DE", "BAS.DE",
			"BAYN.DE", "BEI.DE", "BMW.DE", "CBK.DE", "CON.DE", "DAI.DE",
			"DB1.DE", "DBK.DE", "DPW.DE", "DTE.DE", "EOAN.DE", "FME.DE",
			"FRE.DE", "HEI.DE", "HEN3.DE", "IFX.DE", "LHA.DE", "LIN.DE",
			"LXS.DE", "MRK.DE", "MUV2.DE", "RWE.DE", "SAP.DE", "SDF.DE",
			"SIE.DE", "TKA.DE", "VOW3.DE" };

	public final static String query = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quote%20where%20symbol%20in%20(%22";

	


	private static ServerRegisterImpl regist;

	public static void main(String[] args) throws InvalidName, AdapterInactive {
		try {

			Properties props = System.getProperties();
			props.put("org.omg.CORBA.ORBInitialPort", "1050");
			// Replace MyHost with the name of the host on which you are running
			// the server
			props.put("org.omg.CORBA.ORBInitialHost", "<MyHost>");
			ORB orb = ORB.init(args, null);
			System.out.println("Initialized ORB");

			POA rootPOA = POAHelper.narrow(orb
					.resolve_initial_references("RootPOA"));

			regist = new ServerRegisterImpl();

			regist.setOrb(orb);
			rootPOA.activate_object(regist);

			Server_Register reg = Server_RegisterHelper.narrow(rootPOA
					.servant_to_reference(regist));

			NamingContext namingContext = NamingContextHelper.narrow(orb
					.resolve_initial_references("NameService"));
			System.out.println("Resolved NameService");
			NameComponent[] nc = { new NameComponent("MessageServer", "") };
			namingContext.rebind(nc, reg);

			rootPOA.the_POAManager().activate();

			orb.run();
			
			



		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static List<Stock> getDataFromYahoo() throws IOException,
			ParserConfigurationException, SAXException {
		String unisymbols = StringUtils.join(symbols, "\",\"");
		String urlFull = query + URLEncoder.encode(unisymbols, "UTF-8")+"%22)";
                
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
