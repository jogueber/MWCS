package asynCallback.server;


/**
 * Generated from IDL interface "AMI_Server_RegisterHandler".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 31.05.2014 13:13:42
 */

public abstract class AMI_Server_RegisterHandlerHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(AMI_Server_RegisterHandlerHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:asynCallback/server/AMI_Server_RegisterHandler:1.0", "AMI_Server_RegisterHandler");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final asynCallback.server.AMI_Server_RegisterHandler s)
	{
			any.insert_Object(s);
	}
	public static asynCallback.server.AMI_Server_RegisterHandler extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:asynCallback/server/AMI_Server_RegisterHandler:1.0";
	}
	public static AMI_Server_RegisterHandler read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(asynCallback.server._AMI_Server_RegisterHandlerStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final asynCallback.server.AMI_Server_RegisterHandler s)
	{
		_out.write_Object(s);
	}
	public static asynCallback.server.AMI_Server_RegisterHandler narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof asynCallback.server.AMI_Server_RegisterHandler)
		{
			return (asynCallback.server.AMI_Server_RegisterHandler)obj;
		}
		else if (obj._is_a("IDL:asynCallback/server/AMI_Server_RegisterHandler:1.0"))
		{
			asynCallback.server._AMI_Server_RegisterHandlerStub stub;
			stub = new asynCallback.server._AMI_Server_RegisterHandlerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static asynCallback.server.AMI_Server_RegisterHandler unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof asynCallback.server.AMI_Server_RegisterHandler)
		{
			return (asynCallback.server.AMI_Server_RegisterHandler)obj;
		}
		else
		{
			asynCallback.server._AMI_Server_RegisterHandlerStub stub;
			stub = new asynCallback.server._AMI_Server_RegisterHandlerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
