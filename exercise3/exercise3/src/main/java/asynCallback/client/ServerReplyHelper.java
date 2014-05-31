package asynCallback.client;


/**
 * Generated from IDL interface "ServerReply".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 31.05.2014 13:13:42
 */

public abstract class ServerReplyHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(ServerReplyHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:asynCallback/client/ServerReply:1.0", "ServerReply");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final asynCallback.client.ServerReply s)
	{
			any.insert_Object(s);
	}
	public static asynCallback.client.ServerReply extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:asynCallback/client/ServerReply:1.0";
	}
	public static ServerReply read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(asynCallback.client._ServerReplyStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final asynCallback.client.ServerReply s)
	{
		_out.write_Object(s);
	}
	public static asynCallback.client.ServerReply narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof asynCallback.client.ServerReply)
		{
			return (asynCallback.client.ServerReply)obj;
		}
		else if (obj._is_a("IDL:asynCallback/client/ServerReply:1.0"))
		{
			asynCallback.client._ServerReplyStub stub;
			stub = new asynCallback.client._ServerReplyStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static asynCallback.client.ServerReply unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof asynCallback.client.ServerReply)
		{
			return (asynCallback.client.ServerReply)obj;
		}
		else
		{
			asynCallback.client._ServerReplyStub stub;
			stub = new asynCallback.client._ServerReplyStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}