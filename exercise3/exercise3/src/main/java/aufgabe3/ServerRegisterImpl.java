package aufgabe3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.apache.commons.collections.map.HashedMap;

import callback.NoSuchStock;
import callback.Client_Handler.Serverpush;
import callback.Client_Handler.Stock;
import callback.Server_Handler.Server_RegisterPOA;

public class ServerRegisterImpl extends Server_RegisterPOA {

	HashMap<Stock, List<Serverpush>> serverClientMap = new HashMap<>();

	ArrayList<Stock> stocks = new ArrayList<>();

	@Override
	public void register_stock(Serverpush client, String stockname)
			throws NoSuchStock {
		
	
		Stock tmp = null;
		for (Stock st : stocks) {
			if (st.name.equals(stockname)) {
				tmp = st;
				break;
			}
		}
		if (tmp == null) {
			throw new NoSuchStock("Could not find the Stock !");
		}
		if (serverClientMap.containsKey(tmp)) {
			List<Serverpush> inMap = serverClientMap.get(tmp);
			inMap.add(client);
			serverClientMap.put(tmp, inMap);
			return;
		}
		List<Serverpush> toPut = new ArrayList<Serverpush>();
		toPut.add(client);
		serverClientMap.put(tmp, toPut);
	}

	@Override
	public void unregister_stock(Serverpush client, String stockname)
			throws NoSuchStock {
		// TODO Auto-generated method stub

	}

}
