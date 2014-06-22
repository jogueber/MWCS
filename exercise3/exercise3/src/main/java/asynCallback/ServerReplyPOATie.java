package asynCallback;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "ServerReply".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 01.06.2014 13:09:15
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
	public asynCallback.ServerReply _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		asynCallback.ServerReply __r = asynCallback.ServerReplyHelper.narrow(__o);
		return __r;
	}
	public asynCallback.ServerReply _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		asynCallback.ServerReply __r = asynCallback.ServerReplyHelper.narrow(__o);
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
	public asynCallback.Stock updateStock(java.lang.String stockname) throws asynCallback.NoSuchStock
	{
		return _delegate.updateStock(stockname);
	}

}
