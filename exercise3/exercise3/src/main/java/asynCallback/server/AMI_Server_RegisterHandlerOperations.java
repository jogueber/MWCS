package asynCallback.server;


/**
 * Generated from IDL interface "AMI_Server_RegisterHandler".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 31.05.2014 13:13:42
 */

public interface AMI_Server_RegisterHandlerOperations
	extends org.omg.Messaging.ReplyHandlerOperations
{
	/* constants */
	/* operations  */
	void register_stock();
	void register_stock_excep(org.omg.Messaging.ExceptionHolder excep_holder);
	void unregister_stock();
	void unregister_stock_excep(org.omg.Messaging.ExceptionHolder excep_holder);
}
