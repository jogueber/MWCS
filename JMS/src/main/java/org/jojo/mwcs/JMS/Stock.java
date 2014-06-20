package org.jojo.mwcs.JMS;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.joda.time.DateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@XmlRootElement
public class Stock {

	@Getter
	@XmlElement
	@Setter
	private String name;
	@Getter
	@XmlAttribute
	@Setter
	private String isn;
	@Getter
	@XmlElement
	@Setter
	private double price;
	@Getter
	@XmlElement
	@Setter
	private DateTime time;

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
