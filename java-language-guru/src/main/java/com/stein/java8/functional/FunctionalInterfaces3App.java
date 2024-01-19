package com.stein.java8.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;

/**
 * @author njl
 * @date 2023/2/18
 */
public class FunctionalInterfaces3App {
	public static void main(String[] args) {
//		f1();
//		f3();
//		f4();
//		f5();
		f6();
	}

	public static void f6() { // Creating an Identity Function
		// Returns a function that always returns its input argument.
		Function<Object, Object> identity = Function.identity();
		Object o1 = identity.apply("Creating an Identity Function");
		if (o1 instanceof String) {
			String s1 = (String) o1;
			System.out.println("s1 = " + s1);
		}
	}

	public static void f5() { // Chaining and Composing Functions with Default Methods
		// Chain
		Function<Integer, String> f1 = String::valueOf;
		Function<String, StringBuilder> f2 = StringBuilder::new;
		Function<Integer, StringBuilder> f3 = f1.andThen(f2);
		StringBuilder sb = f3.apply(123456789);
		System.out.println("sb = " + sb);
		// Compose - 暂时没想到好的使用例子
	}

	public static void f4() { // Chaining Consumers with Default Methods
		Logger logger = Logger.getLogger("com.guhe.java8.functional.FunctionalInterfaces3App");
		Consumer<String> log = logger::info; // 实例方法引用
		Consumer<String> print = System.out::println; // 实例方法引用
		Consumer<String> logAndPrint = log.andThen(print);
		logAndPrint.accept("Chaining Consumers with Default Methods");
	}

	public static void f3() { // Creating Predicates with Factory Methods
		Predicate<String> p1 = Predicate.isEqual("Hello Predicate");
		boolean b1 = p1.test("Hello Predicate");
		boolean b2 = p1.test("Hello Consumer");
		System.out.println("b1 = " + b1);
		System.out.println("b2 = " + b2);

		Predicate<Collection<String>> p2 = Collection::isEmpty;
		boolean b3 = p2.test(new ArrayList<>());
		boolean b4 = p2.test(Arrays.asList("a", "b"));
		System.out.println("b3 = " + b3);
		System.out.println("b4 = " + b4);
	}

	public static void f2() { // 使用 Predicate 的默认方法 or 和 negate
		Predicate<String> isNull = Objects::isNull;
		Predicate<String> isEmpty = String::isEmpty;
		Predicate<String> isNullOrEmpty = isNull.or(isEmpty);
		Predicate<String> isNotNullNorEmpty = isNullOrEmpty.negate();
		Predicate<String> lengthLess5 = str -> str.length() < 5;
		Predicate<String> p = isNotNullNorEmpty.and(lengthLess5);
	}

	public static void f1() { // Chaining Predicates with Default Methods
		// process a list of strings, to keep only the strings that are
		// non-null, non-empty, and shorter than 5 characters.
		// 不适用 Predicate 的默认方法链式处理，可读性不好
		Predicate<String> p1 = str -> (str != null) && (!str.isEmpty()) && (str.length() < 5);
		// 使用 Predicate 的默认方法链式处理
		Predicate<String> nonNull = Objects::nonNull;
		Predicate<String> nonEmpty = str -> !str.isEmpty();
		Predicate<String> lengthLess5 = str -> str.length() < 5;
		Predicate<String> p2 = nonNull.and(nonEmpty).and(lengthLess5);
		// Hiding the technical complexity and exposing the intent of the code is
		// what combining lambda expressions is about.

		boolean b1 = p1.test("abc");
		boolean b2 = p1.test("");
		System.out.println("b1 = " + b1);
		System.out.println("b2 = " + b2);

		boolean b3 = p2.test("WORLD");
		boolean b4 = p2.test("ABC");
		System.out.println("b3 = " + b3);
		System.out.println("b4 = " + b4);
	}
}
