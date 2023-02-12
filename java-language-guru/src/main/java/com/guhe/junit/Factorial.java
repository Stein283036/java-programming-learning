package com.guhe.junit;

/**
 * @author njl
 * @date 2023/2/7
 */
public class Factorial {
	public static long fact(long n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		long r = 1;
		for (long i = 1; i <= n; i++) {
			r = r * i;
		}
		return r;
	}
}
