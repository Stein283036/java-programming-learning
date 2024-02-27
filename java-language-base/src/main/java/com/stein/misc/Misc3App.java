package com.stein.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.*;

/**
 * @author njl
 * @date 2023/7/18
 */
public class Misc3App {
	public static void main(String[] args) {
//		misc1();
//		misc2();
		misc3();
//		misc4();
//		misc5();
//		misc6();
//		misc7();
//		misc8();
//		misc9();
//		misc10();
//		misc11();
//		misc12();
//		misc13();
//		misc14();
		misc15();
//		misc16();
	}

	public static void misc16() {
		IntBinaryOperator sumOfTwoNums = Integer::sum;
		int sum = sumOfTwoNums.applyAsInt(99, 1);
		System.out.println("sum = " + sum);
	}

	public static void misc15() {
		BinaryOperator<String> binaryOperator = (s1, s2) -> s1.concat(" ").concat(s2);
		String name = binaryOperator.apply("Tom", "Cruise");
		System.out.println("name = " + name);
	}

	public static void misc14() {
		BiFunction<String, String, Integer> findWordInSentence = (word, sentence) -> sentence.indexOf(word);
		Integer index = findWordInSentence.apply("given", "Applies this function to the given arguments.");
		System.out.println("index = " + index);
	}

	public static void misc13() {
		List<String> strings = Arrays.asList("one", "two", "three");
		UnaryOperator<String> toUpperCase = String::toUpperCase;
		strings.replaceAll(toUpperCase);
		System.out.println(strings);
		strings.add("four");
	}

	public static void misc12() {
		Function<String, Integer> toLength = String::length;
		String girlfriend = "hejingwen";
		int length = toLength.apply(girlfriend);
		System.out.println("length = " + length);
	}

	public static void misc11() {
		List<String> immutableStrings = Arrays.asList("one", "two", "three", "four", "five");
		List<String> strings = new ArrayList<>(immutableStrings);
		Predicate<String> isOddLength = s -> s.length() % 2 == 0;
		strings.removeIf(isOddLength);
		strings.forEach(System.out::println);
	}

	public static void misc10() {
		BiPredicate<String, Integer> isOfLength3 = (s, length) -> s.length() == length;
		boolean res1 = isOfLength3.test("dog", 3);
		System.out.println("res1 = " + res1);
		boolean res2 = isOfLength3.test("love", 3);
		System.out.println("res2 = " + res2);
	}

	public static void misc9() {
//		IntPredicate isGreaterThan10 = i -> i > 10;
		IntPredicate isGreaterThan10 = i -> i > 10;
		boolean res1 = isGreaterThan10.test(11);
		System.out.println("res1 = " + res1);
		boolean res2 = isGreaterThan10.test(9);
		System.out.println("res2 = " + res2);
	}

	public static void misc8() {
		Predicate<String> isOfLength3 = s -> s.length() == 3;
		boolean abc = isOfLength3.test("ABC");
		System.out.println("abc = " + abc);
		boolean love = isOfLength3.test("love");
		System.out.println("love = " + love);
	}

	public static void misc7() {
		List<String> list = Arrays.asList("费德勒", "纳达尔", "德约科维奇");
		Consumer<String> consumer = System.out::println;
		list.forEach(consumer);
	}

	public static void misc6() {
		ObjIntConsumer<Random> objIntConsumer = ((random, value) -> {
			for (int i = 0; i < value; i++) {
				System.out.println("next random = " + random.nextInt(10));
			}
		});
		objIntConsumer.accept(new Random(314L), 5);
	}

	public static void misc5() {
		BiConsumer<Random, Integer> biConsumer = ((random, integer) -> {
			for (int i = 0; i < integer; i++) {
				System.out.println("next random = " + random.nextInt(10));
			}
		});
		biConsumer.accept(new Random(314L), 5);
	}

	public static void misc4() {
		Random random = new Random(314L);
		IntSupplier intSupplier = () -> random.nextInt(10);
//		Consumer<String> consumer = System.out::println;
		Consumer<String> consumer = System.out::println;
		for (int i = 0; i < 5; i++) {
			int nextRandom = intSupplier.getAsInt();
			consumer.accept("next random = " + nextRandom);
		}
	}

	public static void misc3() {
		Random random = new Random(314L);
		IntSupplier intSupplier = () -> random.nextInt(10);
		for (int i = 0; i < 5; i++) {
			int nextRandom = intSupplier.getAsInt();
			System.out.print(nextRandom + " ");
		}
	}

	public static void misc2() {
		Random random = new Random(314L);
		// Variable used in lambda expression should be final or effectively final
		Supplier<Integer> supplier = () -> random.nextInt(10);
		for (int i = 0; i < 5; i++) {
			System.out.print(supplier.get() + " ");
		}
	}

	public static void misc1() {
//		Supplier<String> supplier = () -> "WHEN I CARE ABOUT YOU";
		Supplier<String> supplier = () -> "当我想你的时候";
		String s = supplier.get();
		System.out.println("s = " + s);
	}
}
