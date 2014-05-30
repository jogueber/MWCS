package aufgabe3;

import lombok.Getter;
import callback.Client_Handler.ServerpushPOA;
import callback.Client_Handler.Stock;

public class ServerPushImpl extends ServerpushPOA {
	
	@Getter
	 private	Stock recived;
	
	@Override
	public void push(Stock data) {
		recived=new Stock();
		recived.price = data.price;
		recived.name= data.name;
		recived.isn = data.isn;
	}

}
