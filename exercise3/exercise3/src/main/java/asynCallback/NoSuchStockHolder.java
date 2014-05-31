package asynCallback;

/**
 * Generated from IDL exception "NoSuchStock".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 31.05.2014 13:13:42
 */

public final class NoSuchStockHolder
	implements org.omg.CORBA.portable.Streamable
{
	public asynCallback.NoSuchStock value;

	public NoSuchStockHolder ()
	{
	}
	public NoSuchStockHolder(final asynCallback.NoSuchStock initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return asynCallback.NoSuchStockHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = asynCallback.NoSuchStockHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		asynCallback.NoSuchStockHelper.write(_out, value);
	}
}
