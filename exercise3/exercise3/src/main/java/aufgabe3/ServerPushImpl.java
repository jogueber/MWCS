package aufgabe3;

import org.omg.CORBA.ORB;

import lombok.Getter;
import lombok.Setter;
import callback.Client_Handler.ServerpushPOA;
import callback.Client_Handler.Stock;

public class ServerPushImpl extends ServerpushPOA {
	
	@Getter
	@Setter
	private ORB orb;
	
	@Getter
	 private Stock recived;
	
	@Override
	public void push(Stock data) {
		recived=new Stock();
		recived.price = data.price;
		recived.name= data.name;
		recived.isn = data.isn;
	}

}
