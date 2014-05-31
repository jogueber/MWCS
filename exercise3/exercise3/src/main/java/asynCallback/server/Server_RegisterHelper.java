package asynCallback.server;


/**
 * Generated from IDL interface "Server_Register".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 31.05.2014 13:13:42
 */

public abstract class Server_RegisterHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(Server_RegisterHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:asynCallback/server/Server_Register:1.0", "Server_Register");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final asynCallback.server.Server_Register s)
	{
			any.insert_Object(s);
	}
	public static asynCallback.server.Server_Register extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:asynCallback/server/Server_Register:1.0";
	}
	public static Server_Register read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(asynCallback.server._Server_RegisterStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final asynCallback.server.Server_Register s)
	{
		_out.write_Object(s);
	}
	public static asynCallback.server.Server_Register narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof asynCallback.server.Server_Register)
		{
			return (asynCallback.server.Server_Register)obj;
		}
		else if (obj._is_a("IDL:asynCallback/server/Server_Register:1.0"))
		{
			asynCallback.server._Server_RegisterStub stub;
			stub = new asynCallback.server._Server_RegisterStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static asynCallback.server.Server_Register unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof asynCallback.server.Server_Register)
		{
			return (asynCallback.server.Server_Register)obj;
		}
		else
		{
			asynCallback.server._Server_RegisterStub stub;
			stub = new asynCallback.server._Server_RegisterStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
