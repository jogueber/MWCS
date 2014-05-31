package asynCallback.server;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "AMI_Server_RegisterHandler".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 31.05.2014 13:13:42
 */

public class AMI_Server_RegisterHandlerPOATie
	extends AMI_Server_RegisterHandlerPOA
{
	private AMI_Server_RegisterHandlerOperations _delegate;

	private POA _poa;
	public AMI_Server_RegisterHandlerPOATie(AMI_Server_RegisterHandlerOperations delegate)
	{
		_delegate = delegate;
	}
	public AMI_Server_RegisterHandlerPOATie(AMI_Server_RegisterHandlerOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public asynCallback.server.AMI_Server_RegisterHandler _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		asynCallback.server.AMI_Server_RegisterHandler __r = asynCallback.server.AMI_Server_RegisterHandlerHelper.narrow(__o);
		return __r;
	}
	public asynCallback.server.AMI_Server_RegisterHandler _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		asynCallback.server.AMI_Server_RegisterHandler __r = asynCallback.server.AMI_Server_RegisterHandlerHelper.narrow(__o);
		return __r;
	}
	public AMI_Server_RegisterHandlerOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(AMI_Server_RegisterHandlerOperations delegate)
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
	public void register_stock_excep(org.omg.Messaging.ExceptionHolder excep_holder)
	{
_delegate.register_stock_excep(excep_holder);
	}

	public void unregister_stock()
	{
_delegate.unregister_stock();
	}

	public void register_stock()
	{
_delegate.register_stock();
	}

	public void unregister_stock_excep(org.omg.Messaging.ExceptionHolder excep_holder)
	{
_delegate.unregister_stock_excep(excep_holder);
	}

}
