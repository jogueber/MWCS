package asynCallback;


/**
 * Generated from IDL struct "Stock".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 01.06.2014 13:09:15
 */

public abstract class StockHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(StockHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(asynCallback.StockHelper.id(),"Stock",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("name", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("isn", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("price", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(7)), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final asynCallback.Stock s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static asynCallback.Stock extract (final org.omg.CORBA.Any any)
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
		return "IDL:asynCallback/Stock:1.0";
	}
	public static asynCallback.Stock read (final org.omg.CORBA.portable.InputStream in)
	{
		asynCallback.Stock result = new asynCallback.Stock();
		result.name=in.read_string();
		result.isn=in.read_string();
		result.price=in.read_double();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final asynCallback.Stock s)
	{
		java.lang.String tmpResult0 = s.name;
out.write_string( tmpResult0 );
		java.lang.String tmpResult1 = s.isn;
out.write_string( tmpResult1 );
		out.write_double(s.price);
	}
}
