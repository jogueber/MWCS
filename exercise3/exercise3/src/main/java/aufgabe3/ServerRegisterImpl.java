package aufgabe3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.apache.commons.collections.map.HashedMap;

import callback.NoSuchStock;
import callback.Client_Handler.Serverpush;
import callback.Client_Handler.Stock;
import callback.Server_Handler.Server_RegisterPOA;

public class ServerRegisterImpl extends Server_RegisterPOA {

	private Set<Stock> stocks = new HashSet<>();

	private HashMap<Stock, List<Serverpush>> registered = new HashMap<>();

	public void addStock(Stock stock) {
		stocks.add(stock);
	}

	public boolean removeStock(Stock stock) {
		return stocks.remove(stock);
	}
	
	public void refresh(Stock stock){
		List<Serverpush> tmp=registered.get(stock);
		for(Serverpush t:tmp){
			t.push(stock);
		}
	}

	@Override
	public void register_stock(Serverpush client, String stockname)
			throws NoSuchStock {
		Stock tmp = null;
		for (Stock s : stocks) {
			if (s.name.equals(stockname) || s.name.equals(stockname)) {
				tmp = s;
				break;
			}
		}
		if (tmp == null) {
			throw new NoSuchStock();
		}
		client.push(tmp);
		if (registered.containsKey(tmp) && registered.get(tmp) != null) {
			registered.get(tmp).add(client);
		} else {
			registered.put(tmp,
					new ArrayList<Serverpush>(Arrays.asList(client)));
		}

	}
	
	
	

	@Override
	public void unregister_stock(Serverpush client, String stockname)
			throws NoSuchStock {
		Stock tmp = null;
		for (Stock s : stocks) {
			if (s.name.equals(stockname) || s.name.equals(stockname)) {
				tmp = s;
				break;
			}
		}
		if (tmp == null||!registered.containsKey(tmp)) {
			throw new NoSuchStock();
		}
		registered.get(tmp).remove(client);	
	}
	
	

}
