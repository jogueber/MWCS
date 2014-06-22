package aufgabe4;

import java.util.HashSet;
import java.util.Set;

import lombok.Setter;
import asynCallback.NoSuchStock;
import asynCallback.ServerReplyPOA;
import asynCallback.Stock;

public class ServerReplyImpl extends ServerReplyPOA {
	@Setter
	private Set<Stock> availableStocks = new HashSet<>();

	@Override
	public Stock updateStock(String stockname) throws NoSuchStock {
		Stock tmp = null;
		for (Stock s : availableStocks) {
			if (s.name.equals(stockname) || s.isn.equals(stockname))
				tmp = s;
			break;
		}
		if (tmp == null) {
			throw new NoSuchStock();
		}
		return tmp;
	}

	protected void addStock(Stock s) {
		availableStocks.add(s);
	}

	protected void removeStock(String stockname) {
		for (Stock s : availableStocks) {
			if (s.name.equals(stockname) || s.isn.equals(stockname))
				availableStocks.remove(s);
		}
	}
	

}
