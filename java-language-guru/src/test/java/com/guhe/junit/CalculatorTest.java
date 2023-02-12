package com.guhe.junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 在一个单元测试中，我们经常编写多个@Test方法对被测试类中的方法（通常是实例方法）进行单元测试，所以就需要创建对象来进行方法的调用，但每个
 * 测试方法如果都创建对象就显得很不方便，此外还有在所有的测试方法后释放一些资源的操作等一系列测试方法都需要的操作，可以使用junit的 Fixture
 *
 * @author njl
 * @date 2023/2/7
 */
class CalculatorTest {

	Calculator calculator;

	@BeforeEach
	void setUp() { // 每一个测试方法运行前执行 初始化
		calculator = new Calculator();
	}

	@AfterEach
	void tearDown() { // 每一个测试方法运行后执行 释放资源
		calculator = null;
	}

	@BeforeAll
	void beforeAll() {
		System.out.println("before all");
	}

	@Test
	void add() {
		assertEquals(100, calculator.add(100));
		assertEquals(150, calculator.add(50));
		assertEquals(130, calculator.add(-20));
	}

	@Test
	void sub() {
		assertEquals(-100, calculator.sub(100));
		assertEquals(-120, calculator.sub(20));
	}
}