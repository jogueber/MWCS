package Aufgabe1;

import java.util.Properties;

import lombok.Setter;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import Specfication.Quoter;
import Specfication.QuoterHelper;

public class ServerImpl {
	@Setter
	private static QuoterImpl quo;

	public static void main(String[] args) {
		try {
			Properties props = System.getProperties();
			props.put("org.omg.CORBA.ORBInitialPort", "1050");
			// Replace MyHost with the name of the host on which you are running
			// the server
			props.put("org.omg.CORBA.ORBInitialHost", "localhost");
			org.omg.CORBA.ORB orb = ORB.init(args, props);

			POA rootpoa = POAHelper.narrow(orb
					.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			if (quo == null) {
				quo = new QuoterImpl();
			}
			quo.setOrb(orb);
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(quo);
			Quoter href = QuoterHelper.narrow(ref);

			org.omg.CORBA.Object objRef = orb
					.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			String name = "Stock Quoter";
			NameComponent path[] = ncRef.to_name(name);
			ncRef.rebind(path, href);

			orb.run();

		} catch (Exception e) {
			System.out.print(e.getMessage());
		}

	}
}
