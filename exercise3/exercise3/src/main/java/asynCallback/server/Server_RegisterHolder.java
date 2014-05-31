package asynCallback.server;

/**
 * Generated from IDL interface "Server_Register".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 31.05.2014 13:13:42
 */

public final class Server_RegisterHolder	implements org.omg.CORBA.portable.Streamable{
	 public Server_Register value;
	public Server_RegisterHolder()
	{
	}
	public Server_RegisterHolder (final Server_Register initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return Server_RegisterHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = Server_RegisterHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		Server_RegisterHelper.write (_out,value);
	}
}
