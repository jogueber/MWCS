package asynCallback.server;


/**
 * Generated from IDL interface "AMI_Server_RegisterHandler".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 31.05.2014 13:13:42
 */

public abstract class AMI_Server_RegisterHandlerPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, asynCallback.server.AMI_Server_RegisterHandlerOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "register_stock_excep", Integer.valueOf(0));
		m_opsHash.put ( "unregister_stock", Integer.valueOf(1));
		m_opsHash.put ( "register_stock", Integer.valueOf(2));
		m_opsHash.put ( "unregister_stock_excep", Integer.valueOf(3));
	}
	private String[] ids = {"IDL:asynCallback/server/AMI_Server_RegisterHandler:1.0","IDL:omg.org/Messaging/ReplyHandler:1.0"};
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
			case 0: // register_stock_excep
			{
				org.omg.Messaging.ExceptionHolder _arg0=(org.omg.Messaging.ExceptionHolder)((org.omg.CORBA_2_3.portable.InputStream)_input).read_value ("IDL:omg.org/Messaging/ExceptionHolder:1.0");
				_out = handler.createReply();
				register_stock_excep(_arg0);
				break;
			}
			case 1: // unregister_stock
			{
				_out = handler.createReply();
				unregister_stock();
				break;
			}
			case 2: // register_stock
			{
				_out = handler.createReply();
				register_stock();
				break;
			}
			case 3: // unregister_stock_excep
			{
				org.omg.Messaging.ExceptionHolder _arg0=(org.omg.Messaging.ExceptionHolder)((org.omg.CORBA_2_3.portable.InputStream)_input).read_value ("IDL:omg.org/Messaging/ExceptionHolder:1.0");
				_out = handler.createReply();
				unregister_stock_excep(_arg0);
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
