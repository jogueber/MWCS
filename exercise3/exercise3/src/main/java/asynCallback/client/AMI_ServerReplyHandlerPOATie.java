package asynCallback.client;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "AMI_ServerReplyHandler".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 31.05.2014 13:13:42
 */

public class AMI_ServerReplyHandlerPOATie
	extends AMI_ServerReplyHandlerPOA
{
	private AMI_ServerReplyHandlerOperations _delegate;

	private POA _poa;
	public AMI_ServerReplyHandlerPOATie(AMI_ServerReplyHandlerOperations delegate)
	{
		_delegate = delegate;
	}
	public AMI_ServerReplyHandlerPOATie(AMI_ServerReplyHandlerOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public asynCallback.client.AMI_ServerReplyHandler _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		asynCallback.client.AMI_ServerReplyHandler __r = asynCallback.client.AMI_ServerReplyHandlerHelper.narrow(__o);
		return __r;
	}
	public asynCallback.client.AMI_ServerReplyHandler _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		asynCallback.client.AMI_ServerReplyHandler __r = asynCallback.client.AMI_ServerReplyHandlerHelper.narrow(__o);
		return __r;
	}
	public AMI_ServerReplyHandlerOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(AMI_ServerReplyHandlerOperations delegate)
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
	public void updateStock_excep(org.omg.Messaging.ExceptionHolder excep_holder)
	{
_delegate.updateStock_excep(excep_holder);
	}

	public void updateStock(asynCallback.client.Stock ami_return_val)
	{
_delegate.updateStock(ami_return_val);
	}

}
