package Aufgabe1;

import lombok.Setter;

import org.apache.commons.collections.map.HashedMap;
import org.omg.CORBA.DoubleHolder;
import org.omg.CORBA.ORB;

import Specfication.NoSuchStock;
import Specfication.QuoterPOA;

public class QuoterImpl extends QuoterPOA  {
	@Setter
	private ORB orb;
	@Setter
	private HashedMap stocks=new HashedMap();
	
	
	public void putStock(String key,double price){
		stocks.put(key, price);
	}

	@Override
	public double getStockByIsn(String isn) throws NoSuchStock {
		Double stockPrice=(Double) stocks.get(isn);
		if(stockPrice==null)
			throw new NoSuchStock();
		return stockPrice;	
	}

	@Override
	public double getStockByName(String name) throws NoSuchStock {
		Double stockPrice=(Double) stocks.get(name);
		if(stockPrice==null)
			throw new NoSuchStock();
		return stockPrice;		
	}

}
