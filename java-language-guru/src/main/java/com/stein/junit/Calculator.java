package com.stein.junit;

/**
 * @author njl
 * @date 2023/2/7
 */
public class Calculator {
	private long n = 0;

	public long add(long x) {
		n = n + x;
		return n;
	}

	public long sub(long x) {
		n = n - x;
		return n;
	}
}
