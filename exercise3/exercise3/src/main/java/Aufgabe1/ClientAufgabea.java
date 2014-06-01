package Aufgabe1;

import java.util.Properties;

import lombok.Getter;
import lombok.Setter;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import Specfication.Quoter;
import Specfication.QuoterHelper;

public class ClientAufgabea {

	@Getter
	public static double result1;
	@Getter
	public static double result2;

	public static void main(String[] args) {
		try {
			Properties props = System.getProperties();
			props.put("org.omg.CORBA.ORBInitialPort", "1050");
			// Replace MyHost with the name of the host on which you are running
			// the server
			props.put("org.omg.CORBA.ORBInitialHost", "localhost");
			ORB orb = ORB.init(args, props);
			org.omg.CORBA.Object objRef = orb
					.resolve_initial_references("NameService");

			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			String name = "Stock Quoter";
			Quoter quoter = QuoterHelper.narrow(ncRef.resolve_str(name));
			orb.run();

			Double priceByName = quoter.getStockByName(args[2]);
			result1 = priceByName;

			Double priceByISN = quoter.getStockByIsn(args[3]);
			result2 = priceByISN;
			System.out.println("Stock By ISN Price: " + priceByISN);
			System.out.println("Stock By Name: " + priceByName);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
