package de.atruvia.webapp;

import javax.inject.Named;

@Named
public class Dep {
	
	public void foo() {
		System.out.println("foo");
	}

}
