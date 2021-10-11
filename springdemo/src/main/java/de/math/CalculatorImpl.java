package de.math;

public class CalculatorImpl implements Calculator {
	
	public double add(double a, double b) {
		return a + b;
	}
	public double sub(double a, double b) {
		return add(a, -b);
	}

}
