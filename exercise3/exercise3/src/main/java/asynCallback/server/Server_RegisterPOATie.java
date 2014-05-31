package asynCallback.server;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "Server_Register".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 31.05.2014 13:13:42
 */

public class Server_RegisterPOATie
	extends Server_RegisterPOA
{
	private Server_RegisterOperations _delegate;

	private POA _poa;
	public Server_RegisterPOATie(Server_RegisterOperations delegate)
	{
		_delegate = delegate;
	}
	public Server_RegisterPOATie(Server_RegisterOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public asynCallback.server.Server_Register _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		asynCallback.server.Server_Register __r = asynCallback.server.Server_RegisterHelper.narrow(__o);
		return __r;
	}
	public asynCallback.server.Server_Register _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		asynCallback.server.Server_Register __r = asynCallback.server.Server_RegisterHelper.narrow(__o);
		return __r;
	}
	public Server_RegisterOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(Server_RegisterOperations delegate)
	{
		_delegate = delegate;
	}
	public POA _default_POA()
	{
		if (_poa != null)
		{
			return _poa;
		}
		return super._default_POA();
	}
	public void unregister_stock(asynCallback.client.ServerReply client, java.lang.String stockname) throws asynCallback.NoSuchStock
	{
_delegate.unregister_stock(client,stockname);
	}

	public void register_stock(asynCallback.client.ServerReply client, java.lang.String stockname) throws asynCallback.NoSuchStock
	{
_delegate.register_stock(client,stockname);
	}

}
