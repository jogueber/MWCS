package asynCallback;


/**
 * Generated from IDL interface "ServerReply".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 01.06.2014 13:09:15
 */

public abstract class ServerReplyPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, asynCallback.ServerReplyOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "updateStock", Integer.valueOf(0));
	}
	private String[] ids = {"IDL:asynCallback/ServerReply:1.0"};
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
			case 0: // updateStock
			{
			try
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				asynCallback.StockHelper.write(_out,updateStock(_arg0));
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
