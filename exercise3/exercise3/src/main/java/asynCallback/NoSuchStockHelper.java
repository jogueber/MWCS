package asynCallback;


/**
 * Generated from IDL exception "NoSuchStock".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 31.05.2014 13:13:42
 */

public abstract class NoSuchStockHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(NoSuchStockHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_exception_tc(asynCallback.NoSuchStockHelper.id(),"NoSuchStock",new org.omg.CORBA.StructMember[0]);
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final asynCallback.NoSuchStock s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static asynCallback.NoSuchStock extract (final org.omg.CORBA.Any any)
	{
		org.omg.CORBA.portable.InputStream in = any.create_input_stream();
		try
		{
			return read (in);
		}
		finally
		{
			try
			{
				in.close();
			}
			catch (java.io.IOException e)
			{
			throw new RuntimeException("Unexpected exception " + e.toString() );
			}
		}
	}

	public static String id()
	{
		return "IDL:asynCallback/NoSuchStock:1.0";
	}
	public static asynCallback.NoSuchStock read (final org.omg.CORBA.portable.InputStream in)
	{
		String id = in.read_string();
		if (!id.equals(id())) throw new org.omg.CORBA.MARSHAL("wrong id: " + id);
		final asynCallback.NoSuchStock result = new asynCallback.NoSuchStock(id);
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final asynCallback.NoSuchStock s)
	{
		out.write_string(id());
	}
}
