package callback;

/**
* callback/NoSuchStockHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from callback.idl
* Sonntag, 25. Mai 2014 13:25 Uhr MESZ
*/

public final class NoSuchStockHolder implements org.omg.CORBA.portable.Streamable
{
  public callback.NoSuchStock value = null;

  public NoSuchStockHolder ()
  {
  }

  public NoSuchStockHolder (callback.NoSuchStock initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = callback.NoSuchStockHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    callback.NoSuchStockHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return callback.NoSuchStockHelper.type ();
  }

}
