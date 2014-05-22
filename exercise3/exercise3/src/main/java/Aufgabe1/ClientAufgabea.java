package Aufgabe1;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import Specfication.Quoter;
import Specfication.QuoterHelper;

public class ClientAufgabea {
	
	

	public static void main(String[] args) {
		try {
			ORB orb = ORB.init(args, null);
			org.omg.CORBA.Object objRef = orb
					.resolve_initial_references("NameService");

			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			String name = "Stock Quoter";
			Quoter quoter = QuoterHelper.narrow(ncRef.resolve_str(name));
			
			Double priceByISN=quoter.getStockByIsn(args[3]);
			Double priceByName=quoter.getStockByName(args[2]);
			
			System.out.println("Stock By ISN Price: "+priceByISN);
			System.out.println("Stock By Name: "+priceByName);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
