package asynCallback.server;


/**
 * Generated from IDL interface "Server_Register".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 31.05.2014 13:13:42
 */

public interface Server_RegisterOperations
{
	/* constants */
	/* operations  */
	void register_stock(asynCallback.client.ServerReply client, java.lang.String stockname) throws asynCallback.NoSuchStock;
	void unregister_stock(asynCallback.client.ServerReply client, java.lang.String stockname) throws asynCallback.NoSuchStock;
}
