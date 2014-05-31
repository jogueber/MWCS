package aufgabe4;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;

import org.omg.Messaging.ExceptionHolder;

import asynCallback.client.AMI_ServerReplyHandlerPOA;
import asynCallback.client.Stock;

public class ServerHandlerImpl extends AMI_ServerReplyHandlerPOA {
	@Getter
	private Set<Stock> updatedStocks=new HashSet<>();
	
	@Override
	public void updateStock(Stock ami_return_val) {
		updatedStocks.add(ami_return_val);
		System.out.println("Got update for Stock: "+ami_return_val.name+"; Price:"+ami_return_val.price);
		
	}

	@Override
	public void updateStock_excep(ExceptionHolder excep_holder) {
		// TODO Auto-generated method stub
		
	}

}
