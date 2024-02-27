package com.stein.misc;

import java.util.*;
import java.util.function.*;
import java.util.logging.Logger;

/**
 * @author njl
 * @date 2023/7/18
 */
public class Misc4App {
	public static void main(String[] args) {
//		misc1();
//		misc2();
//		misc3();
//		misc4();
		misc5();
	}

	public static void misc6() {
//		Comparator<Integer> comparator = Integer::compare;
		Comparator<String> comparator = Comparator.comparing(String::length);

	}

	public static void misc5() {
		Function<Object, Object> objectIdentity = Function.identity();
		Object original = new Object();
		Object sameAsOriginal = objectIdentity.apply(original);
		System.out.println(original == sameAsOriginal);

		Function<String, String> identity = Function.identity();
		String tennis = identity.apply("tennis");
		System.out.println("tennis = " + tennis);
	}

	public static void misc4() {
		Predicate<Collection<String>> isEmpty = Collection::isEmpty;
		Predicate<Collection<String>> isNotEmpty = isEmpty.negate();

		Logger logger = Logger.getLogger("Misc4App");
		Consumer<String> log = logger::info;
		Consumer<String> print = System.out::println;
		Consumer<String> logAndPrint = log.andThen(print);
		logAndPrint.accept("debug info");
	}

	public static void misc3() {
		Predicate<Object> equalToDuke = Predicate.isEqual("Duke");
		boolean res = equalToDuke.test("Duke");
		System.out.println("res = " + res);
	}

	public static void misc2() {
		// unbound method references
		Predicate<String> nonNull = Objects::nonNull;
		Predicate<String> empty = String::isEmpty;
		Predicate<String> nonEmpty = empty.negate();
		Predicate<String> shorterThan5 = s -> s.length() < 5;
		Predicate<String> predicate = nonNull.and(nonEmpty).and(shorterThan5);
		System.out.println(predicate.test(""));
		System.out.println(predicate.test(null));
		System.out.println(predicate.test("love"));
		System.out.println(predicate.test("destiny"));
	}

	public static void misc1() {
//		 DoubleUnaryOperator sqrt = num -> Math.sqrt(num);
		 DoubleUnaryOperator sqrt = Math::sqrt;
		double result = sqrt.applyAsDouble(16);
		System.out.println("result = " + result);

//		 IntBinaryOperator max = (a, b) -> Math.max(a,b);
		 IntBinaryOperator max = Math::max;
		Supplier<List<String>> newListOfStrings = ArrayList::new;
		Function<Integer, List<String>> newListOfNStrings = ArrayList::new;
		List<String> list = newListOfNStrings.apply(10);
	}
}
