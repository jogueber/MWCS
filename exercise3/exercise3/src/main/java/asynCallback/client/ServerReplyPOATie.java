package asynCallback.client;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "ServerReply".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 31.05.2014 13:13:42
 */

public class ServerReplyPOATie
	extends ServerReplyPOA
{
	private ServerReplyOperations _delegate;

	private POA _poa;
	public ServerReplyPOATie(ServerReplyOperations delegate)
	{
		_delegate = delegate;
	}
	public ServerReplyPOATie(ServerReplyOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
	public asynCallback.client.ServerReply _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		asynCallback.client.ServerReply __r = asynCallback.client.ServerReplyHelper.narrow(__o);
		return __r;
	}
	public asynCallback.client.ServerReply _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		asynCallback.client.ServerReply __r = asynCallback.client.ServerReplyHelper.narrow(__o);
		return __r;
	}
	public ServerReplyOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(ServerReplyOperations delegate)
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
	public asynCallback.client.Stock updateStock(java.lang.String stockname)
	{
		return _delegate.updateStock(stockname);
	}

}
