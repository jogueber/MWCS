package asynCallback;


/**
 * Generated from IDL interface "AMI_ServerReplyHandler".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 01.06.2014 13:09:15
 */

public abstract class AMI_ServerReplyHandlerPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, asynCallback.AMI_ServerReplyHandlerOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "updateStock_excep", Integer.valueOf(0));
		m_opsHash.put ( "updateStock", Integer.valueOf(1));
	}
	private String[] ids = {"IDL:asynCallback/AMI_ServerReplyHandler:1.0","IDL:omg.org/Messaging/ReplyHandler:1.0"};
	public asynCallback.AMI_ServerReplyHandler _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		asynCallback.AMI_ServerReplyHandler __r = asynCallback.AMI_ServerReplyHandlerHelper.narrow(__o);
		return __r;
	}
	public asynCallback.AMI_ServerReplyHandler _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		asynCallback.AMI_ServerReplyHandler __r = asynCallback.AMI_ServerReplyHandlerHelper.narrow(__o);
		return __r;
	}
	public org.omg.CORBA.portable.OutputStream _invoke(String method, org.omg.CORBA.portable.InputStream _input, org.omg.CORBA.portable.ResponseHandler handler)
		throws org.omg.CORBA.SystemException
	{
		org.omg.CORBA.portable.OutputStream _out = null;
		// do something
		// quick lookup of operation
		java.lang.Integer opsIndex = (java.lang.Integer)m_opsHash.get ( method );
		if ( null == opsIndex )
			throw new org.omg.CORBA.BAD_OPERATION(method + " not found");
		switch ( opsIndex.intValue() )
		{
			case 0: // updateStock_excep
			{
				org.omg.Messaging.ExceptionHolder _arg0=(org.omg.Messaging.ExceptionHolder)((org.omg.CORBA_2_3.portable.InputStream)_input).read_value ("IDL:omg.org/Messaging/ExceptionHolder:1.0");
				_out = handler.createReply();
				updateStock_excep(_arg0);
				break;
			}
			case 1: // updateStock
			{
				asynCallback.Stock _arg0=asynCallback.StockHelper.read(_input);
				_out = handler.createReply();
				updateStock(_arg0);
				break;
			}
		}
		return _out;
	}

	public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] obj_id)
	{
		return ids;
	}
}
