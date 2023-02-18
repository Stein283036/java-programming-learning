package com.guhe.java8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author njl
 * @date 2023/2/13
 */
public class Lambda1App {
	static int outerStaticNum;
	int outerNum;

	public static void main(String[] args) {
//		l1();
//		l2();
//		l3();
//		l4();
		l6();
	}

	public static void l6() {
		Consumer<String> c1 = str -> System.out.println(str);
		Consumer<String> c2 = c1.andThen((Object obj) -> System.out.println(obj));
		c2.accept("HelloWorld");
		Predicate<String> predicate = s -> s.length() == 3;
		boolean hello = predicate.test("Hello");
		boolean abc = predicate.test("abc");
		System.out.println("abc = " + abc);
		System.out.println("hello = " + hello);
		int totalPrice = 0;
		Consumer<String> c3 = str -> {
//			totalPrice+=2; // lambda 的抽象方法实现体中无法修改外部的局部变量，因为作用域和值传递的因素
		};
//		totalPrice = 2;
	}

	public void l5() { // Accessing fields and static variables
		Converter<Integer, String> converter1 = (from -> {
			outerNum = 23;
			return String.valueOf(from);
		});

		Converter<Integer, String> converter2 = (from -> {
			outerStaticNum = 72;
			return String.valueOf(from);
		});
	}

	public static void l4() { // Lambda Scope
		// 在 Lambda 作用域中访问外部变量和再匿名内部类中类似，既可以访问 final 变量也可以访问实例和静态字段
		// 但与匿名内部类不同的是 外部变量比如下面的 num 不一定必须是 final 修饰 但是 num 必须 隐式是 final 即不可修改的
//		final int num = 1;
		int num = 1;
		// Variable used in lambda expression should be final or effectively final
		Converter<Integer, String> converter = (from -> String.valueOf(from + num));
		String s1 = converter.convert(2);
		System.out.println("s1 = " + s1);
//		num = 3;
	}

	public static void l3() { // 使用 Lambda 表达式 实现函数式接口
		Converter<String, Integer> converter = (from -> Integer.valueOf(from));
		Integer i1 = converter.convert("123456");
		System.out.println("i1 = " + i1);
//		Converter<String, Integer> converter = (Integer::valueOf); // 使用类的静态方法引用简化
	}

	public static void l2() { // 使用 lambda
		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
		Collections.sort(names, (s1, s2) -> s2.compareTo(s1));
		Collections.sort(names, Comparator.reverseOrder()); // 上述写法的变体
		names.sort(((s1, s2) -> s2.compareTo(s1)));
	}

	public static void l1() { // 不使用 Lambda
		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
		Collections.sort(names, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o2.compareTo(o1);
			}
		});
		System.out.println("names = " + names);
	}

}
