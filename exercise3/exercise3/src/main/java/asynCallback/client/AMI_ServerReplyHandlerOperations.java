package asynCallback.client;


/**
 * Generated from IDL interface "AMI_ServerReplyHandler".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 31.05.2014 13:13:42
 */

public interface AMI_ServerReplyHandlerOperations
	extends org.omg.Messaging.ReplyHandlerOperations
{
	/* constants */
	/* operations  */
	void updateStock(asynCallback.client.Stock ami_return_val);
	void updateStock_excep(org.omg.Messaging.ExceptionHolder excep_holder);
}
