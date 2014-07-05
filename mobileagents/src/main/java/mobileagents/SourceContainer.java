package mobileagents;

import java.io.File;

import lombok.Getter;
import lombok.Setter;

public  class SourceContainer extends Container {
	
	@Getter
	private String className;
	@Getter
	private File sources;
	

}
