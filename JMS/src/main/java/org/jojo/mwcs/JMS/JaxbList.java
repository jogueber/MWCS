package org.jojo.mwcs.JMS;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Stockliste")
@XmlAccessorType(XmlAccessType.FIELD)
public class JaxbList {
	public JaxbList() {
	}

	public JaxbList(List<Stock> stocks) {
		super();
		this.stocks = stocks;
	}

	public List<Stock> getStocks() {
		return this.stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	@XmlElement(name = "stocks", type = Stock.class)
	private List<Stock> stocks;
}
