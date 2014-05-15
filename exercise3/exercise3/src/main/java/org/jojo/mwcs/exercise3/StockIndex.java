package org.jojo.mwcs.exercise3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor 
public class StockIndex {
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private String isln;
	@Getter
	@Setter
	private double price;
}
