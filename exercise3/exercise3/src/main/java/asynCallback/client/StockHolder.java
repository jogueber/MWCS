package asynCallback.client;

/**
 * Generated from IDL struct "Stock".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 31.05.2014 13:13:42
 */

public final class StockHolder
	implements org.omg.CORBA.portable.Streamable
{
	public asynCallback.client.Stock value;

	public StockHolder ()
	{
	}
	public StockHolder(final asynCallback.client.Stock initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return asynCallback.client.StockHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = asynCallback.client.StockHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		asynCallback.client.StockHelper.write(_out, value);
	}
}
