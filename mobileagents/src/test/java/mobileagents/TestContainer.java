package mobileagents;

import static org.junit.Assert.*;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class TestContainer {

	@Before
	public void setup() {

	}

	@SuppressWarnings("resource")
	@Test
	public void test() throws RemoteException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			MalformedURLException, ClassNotFoundException {
		try{
		URLClassLoader loader;
		URL fileUrl = Paths.get(
				"/home/jojo/git/MWCS/mobileagents/src/a5-applications/")
				.toUri().toURL();
		URLClassLoader loader1 = new URLClassLoader(new URL[] { fileUrl });
		Class hello = loader1.loadClass("HelloWorld");
		CompiledContainer t = new CompiledContainer(hello);
		Map<String, Object> toInvoke = new HashMap<>();
		toInvoke.put("helloWorld", null);
		RMIMethods meth = new RMIMethodsImpl();
		Container a = meth.invokeCompile((CompiledContainer) t);
		assertNotNull(t.getResult().get(0));}
		catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
	}

}
