package asynCallback.server;


/**
 * Generated from IDL interface "Server_Register".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 31.05.2014 13:13:42
 */

public class _Server_RegisterStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements asynCallback.server.Server_Register
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] ids = {"IDL:asynCallback/server/Server_Register:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = asynCallback.server.Server_RegisterOperations.class;
	public void unregister_stock(asynCallback.client.ServerReply client, java.lang.String stockname) throws asynCallback.NoSuchStock
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "unregister_stock", true);
					asynCallback.client.ServerReplyHelper.write(_os,client);
					java.lang.String tmpResult4 = stockname;
_os.write_string( tmpResult4 );
					_is = _invoke(_os);
					return;
				}
				catch( org.omg.CORBA.portable.RemarshalException _rx )
					{
						continue;
					}
				catch( org.omg.CORBA.portable.ApplicationException _ax )
				{
					String _id = _ax.getId();
					try
					{
						if( _id.equals("IDL:asynCallback/NoSuchStock:1.0"))
						{
							throw asynCallback.NoSuchStockHelper.read(_ax.getInputStream());
						}
						else 
						{
							throw new RuntimeException("Unexpected exception " + _id );
						}
					}
					finally
					{
						try
						{
							_ax.getInputStream().close();
						}
						catch (java.io.IOException e)
						{
							throw new RuntimeException("Unexpected exception " + e.toString() );
						}
					}
			}
			finally
			{
				if (_os != null)
				{
					try
					{
						_os.close();
					}
					catch (java.io.IOException e)
					{
						throw new RuntimeException("Unexpected exception " + e.toString() );
					}
				}
				this._releaseReply(_is);
			}
		}
		else
		{
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "unregister_stock", _opsClass );
			if( _so == null )
				continue;
			Server_RegisterOperations _localServant = (Server_RegisterOperations)_so.servant;
			try
			{
				_localServant.unregister_stock(client,stockname);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return;
			}
			catch (asynCallback.NoSuchStock ex) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(ex);
				throw ex;
			}
			catch (RuntimeException re) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(re);
				throw re;
			}
			catch (java.lang.Error err) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(err);
				throw err;
			}
			finally
			{
				_servant_postinvoke(_so);
			}
		}

		}

	}

	public void register_stock(asynCallback.client.ServerReply client, java.lang.String stockname) throws asynCallback.NoSuchStock
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "register_stock", true);
					asynCallback.client.ServerReplyHelper.write(_os,client);
					java.lang.String tmpResult5 = stockname;
_os.write_string( tmpResult5 );
					_is = _invoke(_os);
					return;
				}
				catch( org.omg.CORBA.portable.RemarshalException _rx )
					{
						continue;
					}
				catch( org.omg.CORBA.portable.ApplicationException _ax )
				{
					String _id = _ax.getId();
					try
					{
						if( _id.equals("IDL:asynCallback/NoSuchStock:1.0"))
						{
							throw asynCallback.NoSuchStockHelper.read(_ax.getInputStream());
						}
						else 
						{
							throw new RuntimeException("Unexpected exception " + _id );
						}
					}
					finally
					{
						try
						{
							_ax.getInputStream().close();
						}
						catch (java.io.IOException e)
						{
							throw new RuntimeException("Unexpected exception " + e.toString() );
						}
					}
			}
			finally
			{
				if (_os != null)
				{
					try
					{
						_os.close();
					}
					catch (java.io.IOException e)
					{
						throw new RuntimeException("Unexpected exception " + e.toString() );
					}
				}
				this._releaseReply(_is);
			}
		}
		else
		{
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "register_stock", _opsClass );
			if( _so == null )
				continue;
			Server_RegisterOperations _localServant = (Server_RegisterOperations)_so.servant;
			try
			{
				_localServant.register_stock(client,stockname);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return;
			}
			catch (asynCallback.NoSuchStock ex) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(ex);
				throw ex;
			}
			catch (RuntimeException re) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(re);
				throw re;
			}
			catch (java.lang.Error err) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(err);
				throw err;
			}
			finally
			{
				_servant_postinvoke(_so);
			}
		}

		}

	}

	public void sendc_unregister_stock(AMI_Server_RegisterHandler ami_handler, asynCallback.client.ServerReply client, java.lang.String stockname)
	{
		while(true)
		{
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "unregister_stock", true);
				asynCallback.client.ServerReplyHelper.write(_os,client);
				java.lang.String tmpResult6 = stockname;
_os.write_string( tmpResult6 );
				((org.jacorb.orb.Delegate)_get_delegate()).invoke(this, _os, ami_handler);
				return;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx )
			{
			}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
			}
		}

	}

	public void sendc_register_stock(AMI_Server_RegisterHandler ami_handler, asynCallback.client.ServerReply client, java.lang.String stockname)
	{
		while(true)
		{
			try
			{
				org.omg.CORBA.portable.OutputStream _os = _request( "register_stock", true);
				asynCallback.client.ServerReplyHelper.write(_os,client);
				java.lang.String tmpResult7 = stockname;
_os.write_string( tmpResult7 );
				((org.jacorb.orb.Delegate)_get_delegate()).invoke(this, _os, ami_handler);
				return;
			}
			catch( org.omg.CORBA.portable.RemarshalException _rx )
			{
			}
			catch( org.omg.CORBA.portable.ApplicationException _ax )
			{
			}
		}

	}

}
