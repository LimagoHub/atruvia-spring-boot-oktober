package de.atruvia.webapp;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class Demo {
	
	@Inject
	private Dep dep;
	
	public Demo() {
		System.out.println("############# Demo ###################");
	}
	
	@PostConstruct
	public void init() {
		dep.foo();
	}
}
