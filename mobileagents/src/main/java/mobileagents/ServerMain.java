package mobileagents;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {

	public static void main(String[] args /*
										 * args[0]: port
										 */) throws RemoteException {
		int port = (args.length > 0) ? Integer.parseInt(args[0]) : 4711;
		RMIMethodsImpl requestHandler = new RMIMethodsImpl();
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		String objName = "RequestHandler";
		Registry registry = LocateRegistry.getRegistry(port);
		boolean bound = false;
		for (int i = 0; !bound && i < 2; i++) {
			try {
				registry.rebind(objName, requestHandler);
				bound = true;
				System.out.println(objName + " bound to registry, port " + port
						+ ".");
			} catch (RemoteException e) {
				System.out.println("Rebinding " + objName + " failed, "
						+ "retrying ...");
				registry = LocateRegistry.createRegistry(port);
				System.out.println("Registry started on port" + port + ".");
			}
			while (true) {
			}
		}
	}
}
