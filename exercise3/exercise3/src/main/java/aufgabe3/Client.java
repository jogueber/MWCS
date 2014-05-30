package aufgabe3;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import callback.Client_Handler.Serverpush;
import callback.Client_Handler.ServerpushHelper;

public class Client {
	
	private static Serverpush imp;
	
	public static void main(String[]args) throws InvalidName, AdapterInactive, ServantNotActive, WrongPolicy, NotFound, CannotProceed, org.omg.CosNaming.NamingContextPackage.InvalidName{
		ORB orb = ORB.init(args, null);
	    POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

	    org.omg.CORBA.Object objRef = 
                     orb.resolve_initial_references("NameService");
	    NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

	    String name = "Client Stock";
	    
	    imp=ServerpushHelper.narrow(ncRef.resolve_str(name));
	    
	    ServerPushImpl serv= new ServerPushImpl();
	    serv.setOrb(orb);
	    
	    
	    org.omg.CORBA.Object ref = rootpoa.servant_to_reference(serv);

	    Serverpush cref = ServerpushHelper.narrow(ref);
	    
	    while(true){
	    	
	    }
	}

}
