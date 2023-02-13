package com.guhe.java8.misc;

/**
 * @author njl
 * @date 2023/2/13
 */
public interface Formula {
	double calculate(int num);

	default double sqrt(int num) {
		return Math.sqrt(num);
	}
}
