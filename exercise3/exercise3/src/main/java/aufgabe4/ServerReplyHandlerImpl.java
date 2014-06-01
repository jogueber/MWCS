package aufgabe4;

import org.omg.Messaging.ExceptionHolder;

import asynCallback.AMI_ServerReplyHandlerPOA;
import asynCallback.Stock;

public class ServerReplyHandlerImpl extends AMI_ServerReplyHandlerPOA {

	@Override
	public void updateStock(Stock ami_return_val) {
		
		System.out.println("Recived Stock for Request:"+ami_return_val.name+" Price:"+ami_return_val.price);
		
	}

	@Override
	public void updateStock_excep(ExceptionHolder excep_holder) {
		// TODO Auto-generated method stub
		
	}

}
