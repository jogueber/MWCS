package mobileagents;

import java.lang.reflect.InvocationTargetException;
import java.rmi.Remote;
import java.rmi.*;

public interface RMIMethods extends Remote {
	
	public Container invokeCompile(CompiledContainer container) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,RemoteException;
	
	public Container invokeRaw(SourceContainer container) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, RemoteException;

}
