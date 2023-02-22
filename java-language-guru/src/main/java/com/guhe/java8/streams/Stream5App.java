package com.guhe.java8.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Adding a Terminal Operation on a Stream
 *
 * @author njl
 * @date 2023/2/22
 */
public class Stream5App {
	public static void main(String[] args) {
		s1();
	}

	public static void s1() { // Collecting Stream Elements in a Collection, or an Array
		// 一个流不执行终端操作不会处理流中的任何数据，且一个流只能执行一次终端操作，但是可以执行多次中间操作。

		// Collecting in a Plain ArrayList
		Stream<String> stream1 = Stream.of("one", "two", "three", "four");
		List<String> l1 =
				stream1.filter(s -> s.length() == 3)
						.map(String::toUpperCase)
						.collect(Collectors.toList());

		Stream<String> stream2 = Stream.of("one", "two", "three", "four");
		List<String> l2 =
				stream2.filter(s -> s.length() == 3)
						.map(String::toUpperCase)
						.collect(Collectors.toCollection(LinkedList::new));
		System.out.println(l1.getClass()); // class java.util.ArrayList
		System.out.println(l2.getClass()); // class java.util.LinkedList

		// Collecting in a Set
		Stream<String> stream3 = Stream.of("one", "two", "three", "four");
		Set<String> l3 =
				stream3.filter(s -> s.length() == 3)
						.map(String::toUpperCase)
						.collect(Collectors.toCollection(HashSet::new));
//						.collect(Collectors.toSet());

		// Collecting in a Array
		Stream<String> stream4 = Stream.of("one", "two", "three", "four");
		String[] l4 = stream4.filter(s -> s.length() == 3)
				.map(String::toUpperCase)
//				.toArray(size -> new String[size]);
				.toArray(String[]::new);
		System.out.println(Arrays.asList(l4));
	}
}
