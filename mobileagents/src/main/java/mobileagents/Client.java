package mobileagents;

import java.net.URL;
import java.net.URLClassLoader;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.HashMap;

public class Client {

	public static void main(String[] args) {
		String host = (args.length < 1) ? "localhost" : args[0];
		int port = (args.length < 2) ? 4711 : Integer.parseInt(args[1]);
		try {
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new RMISecurityManager());
			}
			
			RMIMethods obj = (RMIMethods) Naming.lookup("RequestHandler");

			URLClassLoader loader = new URLClassLoader(
					new URL[] { new URL(
							"file://"
									+ "/home/jojo/workspace/mobileagents/src/a5-applications/") });
			Class hello = loader.loadClass("HelloWorld");

			HashMap<String, Object[]> map = new HashMap<>();
			map.put("helloWorld", null);
			CompiledContainer con = new CompiledContainer(hello);
			con.setMethodsToInvoke(map);
			obj.invokeCompile(con);
			for (Object t : con.getResult()) {
				System.out.println(t.toString());
			}

		} catch (Exception e) {
			System.out.println("Client failed, caught exception "
					+ e.getMessage());
		}

	}
}
