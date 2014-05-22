package aufgabe2;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Request;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TypeCodeHolder;

public class ClientAufgabeB {

	public static void main(String[] args) {
		try {
			if (args.length < 3)
				return;
			ORB orb = ORB.init(args, null);
			org.omg.CORBA.Object stockService = orb
					.resolve_initial_references("NameService");
			//Dynamic Method call
			Request r = stockService._request("getStockByIsn");
			r.add_in_arg().insert_string(args[2]);
			r.set_return_type(orb.get_primitive_tc(TCKind.tk_double));
			r.send_deferred();
			
			Double priceByISN=r.return_value().extract_double();
			System.out.println("Price by ISN: "+priceByISN);
			//Call by Name
			Request r2 = stockService._request("getStockByName");
			r2.add_in_arg().insert_string(args[3]);
			r2.set_return_type(orb.get_primitive_tc(TCKind.tk_double));
			r2.send_deferred();
			
			Double priceByName=r2.return_value().extract_double();
			System.out.println("Price by Name: "+priceByName);
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
