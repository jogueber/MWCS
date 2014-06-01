package asynCallback;


/**
 * Generated from IDL interface "AMI_ServerReplyHandler".
 *
 * @author JacORB IDL compiler V 3.4
 * @version generated at 01.06.2014 13:09:15
 */

public interface AMI_ServerReplyHandlerOperations
	extends org.omg.Messaging.ReplyHandlerOperations
{
	/* constants */
	/* operations  */
	void updateStock(asynCallback.Stock ami_return_val);
	void updateStock_excep(org.omg.Messaging.ExceptionHolder excep_holder);
}
