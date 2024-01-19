package com.stein.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author njl
 * @date 2023/2/7
 */
class FactorialTest {
	@Test
	public void testFact() {
		// assertEquals(expected, actual)是最常用的测试方法
		assertEquals(1, Factorial.fact(1));
		assertEquals(2, Factorial.fact(2));
		assertEquals(6, Factorial.fact(3));
		assertEquals(3628800, Factorial.fact(10));
		assertEquals(2432902008176640000L, Factorial.fact(20));
	}

	@Test
	public void t1() {
		// assertTrue(): 期待结果为true
		// assertFalse(): 期待结果为false
		// assertNotNull(): 期待结果为非null
		// assertArrayEquals(): 期待结果为数组并与期望数组每个元素的值均相等
		boolean a = true;
		assertTrue(a);
	}

	/**
	 * 异常测试
	 */
	@Test
	void testNegative() {
		assertThrows(IllegalArgumentException.class, () -> {
			Factorial.fact(-1);
		});
	}


}