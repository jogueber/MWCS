package aufgabe3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.apache.commons.collections.map.HashedMap;
import org.omg.CORBA.ORB;

import callback.NoSuchStock;
import callback.Client_Handler.Serverpush;
import callback.Client_Handler.Stock;
import callback.Server_Handler.Server_RegisterPOA;

public class ServerRegisterImpl extends Server_RegisterPOA {
	@Getter
	@Setter
	private ORB orb;

	private Set<Stock> stocks = new HashSet<>();

	private HashMap<Stock, List<Serverpush>> registered = new HashMap<>();

	public void addStock(Stock stock) {
		stocks.add(stock);
	}

	public boolean removeStock(Stock stock) {
		return stocks.remove(stock);
	}

	public void refresh(Stock stock) {
		Stock found = null;
		for (Stock s : stocks) {
			if (stock.name == s.name) {
				found = s;
				break;
			}
		}
		if (found == null) {
			return;
		}
		stocks.remove(found);
		stocks.add(found);
		if (!registered.containsKey(found))
			return;

		List<Serverpush> tmp = registered.get(found);
		registered.remove(found);
		registered.put(stock, tmp);
		for (Serverpush t : tmp) {
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
		if (tmp == null || !registered.containsKey(tmp)) {
			throw new NoSuchStock();
		}
		registered.get(tmp).remove(client);
	}

}
