package mobileagents;

import java.lang.reflect.InvocationTargetException;
import java.rmi.Remote;
import java.rmi.*;

public interface RMIMethods extends Remote {
	
	public Container invokeCompile(CompiledContainer container) throws RemoteException;
	
	public Container invokeRaw(SourceContainer container) throws  RemoteException;

	public Container invokeThis(CompiledContainer con)throws RemoteException;
}
