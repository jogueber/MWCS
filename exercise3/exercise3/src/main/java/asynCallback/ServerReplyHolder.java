package asynCallback;

/**
 * Generated from IDL interface "ServerReply".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 01.06.2014 13:09:15
 */

public final class ServerReplyHolder	implements org.omg.CORBA.portable.Streamable{
	 public ServerReply value;
	public ServerReplyHolder()
	{
	}
	public ServerReplyHolder (final ServerReply initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return ServerReplyHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = ServerReplyHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		ServerReplyHelper.write (_out,value);
	}
}
