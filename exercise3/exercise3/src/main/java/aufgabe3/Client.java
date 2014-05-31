package aufgabe3;

import java.util.Properties;

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
	
	private static Serverpush imp;
	
	public static void main(String[]args) {
	try{	
		Properties props = System.getProperties();
        props.put("org.omg.CORBA.ORBInitialPort", "1050");
        props.put("org.omg.CORBA.ORBInitialHost", "<MyHost>");
        ORB orb = ORB.init(args, props);
	    POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
         rootpoa.the_POAManager().activate();
         
        ServerPushImpl impl=new ServerPushImpl();
        rootpoa.activate_object(impl);
        
        Serverpush ref=ServerpushHelper.narrow(rootpoa.servant_to_reference(impl));
        
        Server_Register server=Server_RegisterHelper.narrow(orb.string_to_object("corbaname:iiop:1.2@localhost:1050#MessageServer"));
        
        server.register_stock(ref, "BEI.DE");
        
        orb.run();
      
	}catch(Exception e){
		System.out.println(e.getStackTrace());
	}

}
