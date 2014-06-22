package asynCallback;

/**
 * Generated from IDL struct "Stock".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 01.06.2014 13:09:15
 */

public final class Stock
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public Stock(){}
	public java.lang.String name = "";
	public java.lang.String isn = "";
	public double price;
	public Stock(java.lang.String name, java.lang.String isn, double price)
	{
		this.name = name;
		this.isn = isn;
		this.price = price;
	}
}
