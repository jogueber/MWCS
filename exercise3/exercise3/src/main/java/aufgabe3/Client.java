package aufgabe3;

import java.util.Properties;

import lombok.Getter;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import callback.Server_Handler.Server_Register;
import callback.Client_Handler.Serverpush;
import callback.Client_Handler.ServerpushHelper;
import callback.Server_Handler.Server_RegisterHelper;

public class Client {
	@Getter
	private static ServerPushImpl imp;

	public static void main(String[] args) {
		try {
			Properties props = System.getProperties();
			props.put("org.omg.CORBA.ORBInitialPort", args[0]);
			props.put("org.omg.CORBA.ORBInitialHost", args[1]);
			ORB orb = ORB.init(args, props);
			POA rootpoa = POAHelper.narrow(orb
					.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();
			if (imp == null) {
				ServerPushImpl imp = new ServerPushImpl();
			}
			rootpoa.activate_object(imp);

			Serverpush ref = ServerpushHelper.narrow(rootpoa
					.servant_to_reference(imp));

			Server_Register server = Server_RegisterHelper.narrow(orb
					.resolve_initial_references("MessageServer"));

			server.register_stock(ref, args[2]);

			orb.run();

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
}
