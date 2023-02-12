package com.guhe.junit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 参数化测试 被测试的方法需要接收至少一个参数
 *
 * @author njl
 * @date 2023/2/7
 */
class StringUtilsTest {

	@ParameterizedTest
	@CsvFileSource(resources = "test-capitalize.csv")
	void testCapitalizeWithCsvFileSource(String input, String result) {
		assertEquals(result, StringUtils.capitalize(input));
	}

	/**
	 * 另一种参数化测试的方法是使用 @CsvSource 注解 其中每一个字符串表示一行数据 即一个测试方法所需的参数 在这里表示 input 和 result
	 */
	@ParameterizedTest
	@CsvSource({"apple, Apple", "love, Love"})
	// 局限性 如果有大量的测试数据就不方便 这时可以使用 @CsvFileSource 引入 csv 文件
	void testCapitalizeWithCsvSource(String input, String result) {
		assertEquals(result, StringUtils.capitalize(input));
	}

	/**
	 * 即要给出输入参数 也要给出预期结果参数
	 * 最简单的方法是通过@MethodSource注解，它允许我们编写一个同名的静态方法来提供测试参数
	 */
	@ParameterizedTest
	@MethodSource(value = "testCapitalize")
	// 可以指定引用的方法名称来引入测试方法需要的参数 默认是测试方法名称
	void testCapitalizeWithMethodSource(String input, String result) {
		assertEquals(result, StringUtils.capitalize(input));
	}

	static List<Arguments> testCapitalize() {
		ArrayList<Arguments> arguments = new ArrayList<>();
		arguments.add(Arguments.of("apple", "Apple"));
		arguments.add(Arguments.of("string", "String"));
		return arguments;
	}

	/**
	 * 对 Math.abs 进行参数化测试 用一组正数
	 */
	@ParameterizedTest
	@ValueSource(ints = {-1, -100})
	void testAbsWithNegativeValues(int x) {
		assertEquals(-x, Math.abs(x));
	}

	/**
	 * 对 Math.abs 进行参数化测试 用一组正数
	 */
	@ParameterizedTest
	@ValueSource(ints = {0, 1, 10, 100})
	void testAbsWithPositiveValues(int x) {
		assertEquals(x, Math.abs(x));
	}
}