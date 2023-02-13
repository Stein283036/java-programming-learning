package com.guhe.java8.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author njl
 * @date 2023/2/13
 */
public class Streams1App {
	private static final List<String> stringCollection = new ArrayList<>();

	static {
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");
	}

	public static void main(String[] args) {
//		s1();
//		s2();
//		s3();
//		s4();
//		s5();
		s6();
	}

	public static void s6() { // Reduce
		// This terminal operation performs a reduction on the elements of the stream with the given function.
		// The result is an Optional holding the reduced value.
		// 这个方法和 JS 中的 reduce 非常类似 都是用来对一个集合中的元素做归纳
		Optional<String> reduced = stringCollection.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);
		reduced.ifPresent(System.out::println);
	}

	public static void s5() { // Count
		// Count is a terminal operation returning the number of elements in the stream as a long.
		// 返回集合中以字符串 "b" 开头的元素数量
		long count = stringCollection.stream().filter(s -> s.startsWith("b")).count();
		System.out.println("count = " + count);
	}

	public static void s4() { // Match
		// Various matching operations can be used to check whether a certain predicate matches the stream.
		// All of those operations are terminal and return a boolean result.
		// 集合中是否至少有一个元素满足 Predicate 指定的条件
		boolean b1 = stringCollection.stream().anyMatch(s -> s.startsWith("a"));
		System.out.println("b1 = " + b1); // true
		// 集合中是否所有的元素都满足 Predicate 指定的条件
		boolean b2 = stringCollection.stream().allMatch(s -> s.startsWith("a"));
		System.out.println("b2 = " + b2); // false
		// 集合中是否没有元素满足 Predicate 指定的条件
		boolean b3 = stringCollection.stream().noneMatch(s -> s.startsWith("z"));
		System.out.println("b3 = " + b3);
	}

	public static void s3() { // Map
		// The intermediate operation map converts each element into another object via the given function.
		stringCollection
				.stream()
				.map(String::toUpperCase)
				.sorted((s1, s2) -> s2.compareTo(s1))
				.forEach(System.out::println);
	}

	public static void s2() { // Sorted
		// 先过滤出集合中以字符 'a' 开头的 然后按照 String 的自然排序规则排序 也可以给 sorted 传入一个自定义的比较器
		// sorted does only create a sorted view of the stream without manipulating the ordering of the backed collection.
		// The ordering of stringCollection is untouched:
		stringCollection.stream().filter(s -> s.startsWith("a")).sorted().forEach(System.out::println);
		System.out.println("stringCollection = " + stringCollection);
	}

	public static void s1() { // Filter
		// 过滤 stringCollection 中以 字符 'a' 开头的并输出
		// filter 是  intermediate operation. forEach 是 terminal operation
		// System.out::println 成员方法引用
		stringCollection.stream().filter(str -> str.startsWith("a")).forEach(System.out::println);
	}
}
