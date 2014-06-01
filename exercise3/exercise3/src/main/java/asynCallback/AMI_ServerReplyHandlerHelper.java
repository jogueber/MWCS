package asynCallback;


/**
 * Generated from IDL interface "AMI_ServerReplyHandler".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 01.06.2014 13:09:15
 */

public abstract class AMI_ServerReplyHandlerHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(AMI_ServerReplyHandlerHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:asynCallback/AMI_ServerReplyHandler:1.0", "AMI_ServerReplyHandler");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final asynCallback.AMI_ServerReplyHandler s)
	{
			any.insert_Object(s);
	}
	public static asynCallback.AMI_ServerReplyHandler extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:asynCallback/AMI_ServerReplyHandler:1.0";
	}
	public static AMI_ServerReplyHandler read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(asynCallback._AMI_ServerReplyHandlerStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final asynCallback.AMI_ServerReplyHandler s)
	{
		_out.write_Object(s);
	}
	public static asynCallback.AMI_ServerReplyHandler narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof asynCallback.AMI_ServerReplyHandler)
		{
			return (asynCallback.AMI_ServerReplyHandler)obj;
		}
		else if (obj._is_a("IDL:asynCallback/AMI_ServerReplyHandler:1.0"))
		{
			asynCallback._AMI_ServerReplyHandlerStub stub;
			stub = new asynCallback._AMI_ServerReplyHandlerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static asynCallback.AMI_ServerReplyHandler unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof asynCallback.AMI_ServerReplyHandler)
		{
			return (asynCallback.AMI_ServerReplyHandler)obj;
		}
		else
		{
			asynCallback._AMI_ServerReplyHandlerStub stub;
			stub = new asynCallback._AMI_ServerReplyHandlerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
