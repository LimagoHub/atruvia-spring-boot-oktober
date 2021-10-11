package de.client;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import de.math.Calculator;

@Component
public class CalcClient {
	
	
	private final Calculator calculator;
	
	public CalcClient(@Qualifier("secure")final Calculator calculator) {
		this.calculator = calculator;
	}

	@PostConstruct
	public  void go() {
		System.out.println(calculator.add(3, 4));
	}

}
