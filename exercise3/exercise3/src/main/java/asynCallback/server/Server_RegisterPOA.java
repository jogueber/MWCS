package asynCallback.server;


/**
 * Generated from IDL interface "Server_Register".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 31.05.2014 13:13:42
 */

public abstract class Server_RegisterPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, asynCallback.server.Server_RegisterOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "unregister_stock", Integer.valueOf(0));
		m_opsHash.put ( "register_stock", Integer.valueOf(1));
	}
	private String[] ids = {"IDL:asynCallback/server/Server_Register:1.0"};
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
			case 0: // unregister_stock
			{
			try
			{
				asynCallback.client.ServerReply _arg0=asynCallback.client.ServerReplyHelper.read(_input);
				java.lang.String _arg1=_input.read_string();
				_out = handler.createReply();
				unregister_stock(_arg0,_arg1);
			}
			catch(asynCallback.NoSuchStock _ex0)
			{
				_out = handler.createExceptionReply();
				asynCallback.NoSuchStockHelper.write(_out, _ex0);
			}
				break;
			}
			case 1: // register_stock
			{
			try
			{
				asynCallback.client.ServerReply _arg0=asynCallback.client.ServerReplyHelper.read(_input);
				java.lang.String _arg1=_input.read_string();
				_out = handler.createReply();
				register_stock(_arg0,_arg1);
			}
			catch(asynCallback.NoSuchStock _ex0)
			{
				_out = handler.createExceptionReply();
				asynCallback.NoSuchStockHelper.write(_out, _ex0);
			}
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
