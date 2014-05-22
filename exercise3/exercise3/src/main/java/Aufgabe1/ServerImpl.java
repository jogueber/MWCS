package Aufgabe1;


import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import Specfication.Quoter;
import Specfication.QuoterHelper;

public class ServerImpl {

	public static void main(String[] args) {
		try {
			org.omg.CORBA.ORB orb = ORB.init(args, null);

			QuoterImpl quo = new QuoterImpl();
			POA rootpoa = POAHelper.narrow(orb
					.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(quo);
			Quoter href = QuoterHelper.narrow(ref);

			org.omg.CORBA.Object objRef = orb
					.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
		      String name = "Stock Quoter";
		      NameComponent path[] = ncRef.to_name( name );
		      ncRef.rebind(path, href);
		      
		      orb.run();

			quo.setOrb(orb);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}

	}
}
