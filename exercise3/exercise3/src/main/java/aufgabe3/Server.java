package aufgabe3;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.helper.ProjectHelper2.RootHandler;
import org.json.JSONTokener;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

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
	
	public static void main(String[] args) throws InvalidName {
		ORB orb = ORB.init(args, null);

		org.omg.CORBA.Object objRef = orb
				.resolve_initial_references("NameService");
		NamingContext ncRef = NamingContextHelper.narrow(objRef);
		
		regist=new ServerRegisterImpl();
		
		POA rootpoa = POAHelper.narrow(orb
				.resolve_initial_references("RootPOA"));
		rootpoa.the_POAManager().activate();

	}

	public void getDataFromYahoo() throws IOException {
		String unisymbols = StringUtils.join(symbols, "\",\"");
		String toSend = query + unisymbols + "\")";
		String urlFull = yahooAdress + URLEncoder.encode(toSend, "UTF-8")
				+ json;
		URL url = new URL(urlFull);
		InputStream is =url.openStream();

			
	}

}
