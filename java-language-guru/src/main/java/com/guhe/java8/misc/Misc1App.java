package com.guhe.java8.misc;

/**
 * @author njl
 * @date 2023/2/13
 */
public class Misc1App {
	public static void main(String[] args) {

	}

	public static void m1() {
		Formula formula = new FormulaImpl();
		System.out.println(formula.calculate(-10));
		System.out.println(formula.sqrt(4));
	}
}

class FormulaImpl implements Formula {

	@Override
	public double calculate(int num) {
		return Math.abs(num);
	}
}
