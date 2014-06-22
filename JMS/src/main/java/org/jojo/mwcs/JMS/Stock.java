package org.jojo.mwcs.JMS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.time.DateTime;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Stock {

	public Stock() {
	}

	@XmlElement
	private String name;

	@XmlAttribute
	private String isn;
	@XmlElement
	private double price;
	@XmlElement
	private DateTime time;

	public Stock(String name, String isn, double price, DateTime time) {
		super();
		this.name = name;
		this.isn = isn;
		this.price = price;
		this.time = time;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsn() {
		return this.isn;
	}

	public void setIsn(String isn) {
		this.isn = isn;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public DateTime getTime() {
		return this.time;
	}

	public void setTime(DateTime time) {
		this.time = time;
	}

	@Override
	public boolean equals(Object obj) {
		if (getClass() != getClass())
			return false;
		if (obj == null)
			return false;
		Stock tmp = (Stock) obj;
		if (tmp.getName().equals(this.name) || tmp.getIsn().equals(this.isn))
			return true;
		else
			return false;

	}

	@Override
	public int hashCode() {
		HashCodeBuilder bui = new HashCodeBuilder(17, 31);
		bui.append(name);
		bui.append(isn);
		return bui.toHashCode();
	}

}
