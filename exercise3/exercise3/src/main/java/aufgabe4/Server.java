package aufgabe4;

import java.awt.List;
import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POAHelper;

import asynCallback.ServerReply;
import asynCallback.ServerReplyHelper;

public class Server {

	public static void main(String[] args) {
		try {
			Properties props = System.getProperties();
			props.put("org.omg.CORBA.ORBInitialPort", args[0]);
			props.put("org.omg.CORBA.ORBInitialHost", args[1]);
			ORB orb = ORB.init(args, props);

			org.omg.PortableServer.POA poa = POAHelper.narrow(orb
					.resolve_initial_references("RootPOA"));
			poa.the_POAManager().activate();
			
			ServerReplyHandlerImpl impl=new ServerReplyHandlerImpl();
	        org.omg.CORBA.Object obj = poa.servant_to_reference( impl );
	        
	        ServerReply href=ServerReplyHelper.narrow(obj);
	        
	        org.omg.CORBA.Object objRef = orb
					.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
		      String name = "Async Server";
		      NameComponent path[] = ncRef.to_name( name );
		      ncRef.rebind(path, href);
		      
		      while(true){
		    	orb.run();
		    	Thread.currentThread().sleep(10000);
		      }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
