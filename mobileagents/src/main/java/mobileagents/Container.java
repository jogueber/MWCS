package mobileagents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.google.common.collect.Multimap;

import lombok.Getter;
import lombok.Setter;

public abstract class Container implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 11043044898722191L;
	@Getter
	
	private Iterable<String> args;
	@Getter
	@Setter
	private Object instance;
	@Getter
	@Setter
	private Long billing;
	@Getter
	@Setter
	private List result;
	@Getter
	@Setter
	private Map<String, Object[]> methodsToInvoke;
	
	

}
