package asynCallback;

/**
 * Generated from IDL struct "Stock".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 01.06.2014 13:09:15
 */

public final class StockHolder
	implements org.omg.CORBA.portable.Streamable
{
	public asynCallback.Stock value;

	public StockHolder ()
	{
	}
	public StockHolder(final asynCallback.Stock initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return asynCallback.StockHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = asynCallback.StockHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		asynCallback.StockHelper.write(_out, value);
	}
}
