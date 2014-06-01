package aufgabe2;

import java.util.Properties;

import lombok.Getter;
import lombok.Setter;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Request;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TypeCodeHolder;

public class ClientAufgabeB {

	@Getter
	private static double testResult1;
	@Getter
	private static double testResult2;

	public static void main(String[] args) {
		try {
			Properties props = System.getProperties();
			props.put("org.omg.CORBA.ORBInitialPort", "1050");
			// Replace localhost with the name of the host on which you are running
			// the server
			props.put("org.omg.CORBA.ORBInitialHost", "localhost");

			ORB orb = ORB.init(args, null);
			org.omg.CORBA.Object stockService = orb
					.resolve_initial_references("NameService");
			// Dynamic Method call
			Request r = stockService._request("getStockByIsn");
			r.add_in_arg().insert_string(args[2]);
			r.set_return_type(orb.get_primitive_tc(TCKind.tk_double));
			r.send_deferred();

			Double priceByISN = r.return_value().extract_double();
			testResult1 = priceByISN;
			System.out.println("Price by ISN: " + priceByISN);
			// Call by Name
			Request r2 = stockService._request("getStockByName");
			r2.add_in_arg().insert_string(args[3]);
			r2.set_return_type(orb.get_primitive_tc(TCKind.tk_double));
			r2.send_deferred();

			Double priceByName = r2.return_value().extract_double();
			testResult2 = priceByName;
			System.out.println("Price by Name: " + priceByName);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
