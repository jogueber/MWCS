package asynCallback;


/**
 * Generated from IDL interface "ServerReply".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 01.06.2014 13:09:15
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
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:asynCallback/ServerReply:1.0", "ServerReply");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final asynCallback.ServerReply s)
	{
			any.insert_Object(s);
	}
	public static asynCallback.ServerReply extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:asynCallback/ServerReply:1.0";
	}
	public static ServerReply read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(asynCallback._ServerReplyStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final asynCallback.ServerReply s)
	{
		_out.write_Object(s);
	}
	public static asynCallback.ServerReply narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof asynCallback.ServerReply)
		{
			return (asynCallback.ServerReply)obj;
		}
		else if (obj._is_a("IDL:asynCallback/ServerReply:1.0"))
		{
			asynCallback._ServerReplyStub stub;
			stub = new asynCallback._ServerReplyStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static asynCallback.ServerReply unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof asynCallback.ServerReply)
		{
			return (asynCallback.ServerReply)obj;
		}
		else
		{
			asynCallback._ServerReplyStub stub;
			stub = new asynCallback._ServerReplyStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
