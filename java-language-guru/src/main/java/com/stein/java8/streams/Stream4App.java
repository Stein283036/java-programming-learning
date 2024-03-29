package com.stein.java8.streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Supplier;
import java.util.stream.*;

/**
 * Creating Streams
 *
 * @author njl
 * @date 2023/2/22
 */
public class Stream4App {
	public static void main(String[] args) {
//		s1();
//		s2();
//		s3();
		s4();
	}

	public static void s7() throws IOException { // Stream of File
		Path path = Paths.get("C:\\file.txt");
		try (
				Stream<String> streamOfStrings = Files.lines(path);
				Stream<String> streamWithCharset =
						Files.lines(path, StandardCharsets.UTF_8);
		) {

		}
	}

	public static void s6() { // Stream of String
		IntStream chars = "string".chars();
	}

	public static void s5() { // Stream of primitive
		IntStream intStream = IntStream.range(1, 3);
		LongStream longStream = LongStream.rangeClosed(1, 3);
		DoubleStream ds = new Random().doubles(3);
	}

	public static void s4() { // Stream.iterate()
		List<String> list = Stream.iterate("*", t -> t.concat("*")).limit(5).collect(Collectors.toList());
		System.out.println("list = " + list);
	}

	public static void s3() { // Stream.generate()
		Supplier<User> userSupplier = User::new;
		long count = Stream.generate(userSupplier).limit(10).count();
		System.out.println("count = " + count);
	}

	public static void s2() { // Stream.builder()
		Stream<String> stream = Stream.<String>builder().add("a").add("b").add("c").build();
		List<String> list = stream.map(String::toUpperCase).collect(Collectors.toList());
		System.out.println("list = " + list); // list = [A, B, C]
	}

	public static void s1() { // Creating a Stream
		// 除了使用集合的 stream() 方法创建 Stream 流以外，还能使用 Stream 接口的工厂方法创建基于其它对象的流

		// Creating a Stream from a Collection or an Iterator
		int[] arr = {1, 2, 3, 4, 5, 6};
		Spliterator.OfInt spliterator = Spliterators.spliterator(arr, 0);
		List<Integer> l1 = StreamSupport.stream(spliterator, false).collect(Collectors.toList());
		System.out.println("l1 = " + l1); // l1 = [1, 2, 3, 4, 5, 6]

		// 创建空流
		List<Object> l2 = Stream.empty().collect(Collectors.toList());
		System.out.println("l2 = " + l2); // l2 = []

		// 基于可变长参数创建流
		// 内部调用 Arrays.stream()
		List<Integer> l3 = Stream.of(1, 2, 3).collect(Collectors.toList());
		System.out.println("l3 = " + l3); // l3 = [1, 2, 3]

		// Creating a Stream from a Supplier
		// This stream produces elements and never stops. It really produces an infinite stream.
//		Stream<String> generated = Stream.generate(() -> "+");

		// The limit() method is called a short-circuiting method: it can stop the consumption of the elements of a stream.
		// the data is processed on element at a time in a stream: each element traverses all the operations defined
		// in your stream, from the first one to the last one. This is the reason why this limit operation can stop
		// the generation of more elements.
		List<String> l4 = Stream.generate(() -> "+").limit(10).collect(Collectors.toList());
		System.out.println("l4 = " + l4); // l4 = [+, +, +, +, +, +, +, +, +, +]

		// Creating a Stream from a UnaryOperator and a Seed
		// Using a supplier is great if you need to generate constant streams.
		// If you need an infinite stream with varying values, then you can use the iterate() pattern.
		// 这种方式基于一元运算符对流中的元素进行运算，每次可以返回不同的值
		List<String> l5 = Stream.iterate("+", s -> s + "+").limit(5).collect(Collectors.toList());
		System.out.println("l5 = " + l5); // l5 = [+, ++, +++, ++++, +++++]

		// Creating a Stream from a Range of Numbers
		String[] letters = {"A", "B", "C", "D"};
		List<String> l6 = IntStream.rangeClosed(0, 10).mapToObj(index -> letters[index % letters.length]).collect(Collectors.toList());
		System.out.println("l6 = " + l6); // l6 = [A, B, C, D, A, B, C, D, A, B, C]

		// Creating a Stream from the Characters of a String
		String sentence = "Hello Duke";
		List<Character> l7 =
				sentence.chars()
						.mapToObj(codePoint -> (char) codePoint)
						.collect(Collectors.toList());
		System.out.println("l7 = " + l7); // l7 = [H, e, l, l, o,  , D, u, k, e]

		Stream.Builder<String> builder = Stream.<String>builder();
		builder.add("one")
				.add("two")
				.add("three")
				.add("four");
		Stream<String> stream = builder.build();
		List<String> l8 = stream.collect(Collectors.toList());
		System.out.println("l8 = " + l8); // l8 = [one, two, three, four]
	}
}
