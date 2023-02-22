package com.guhe.java8.streams;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Adding Intermediate Operations on a Stream
 *
 * @author njl
 * @date 2023/2/22
 */
public class Streams3App {
	public static void main(String[] args) {
//		s1();
//		s2();
//		s3();
//		s4();
//		s5();
//		s6();
//		s7();
//		s8();
		s9();
	}

	public static void s9() { // Debugging Streams
		// It may sometimes be convenient to examine the elements processed by a stream at run time.
		// The Stream API has a method for that: the peek() method.
		// This method is meant to be used to debug your data processing pipeline.
		// You should not use this method in your production code.
		List<String> strings = Arrays.asList("one", "two", "three", "four");
		List<String> result =
				strings.stream()
						.peek(s -> System.out.println("Starting with = " + s))
						.filter(s -> s.startsWith("t"))
						.peek(s -> System.out.println("Filtered = " + s))
						.map(String::toUpperCase)
						.peek(s -> System.out.println("Mapped = " + s))
						.collect(Collectors.toList());
		System.out.println("result = " + result);
		// 使用 peek 在开发阶段进行调试，不要在生产阶段使用。
		// Debugging a stream is hard because you need to be careful where you put your breakpoints.
		// Most of the time, putting breakpoints on a stream processing will send you to the implementation of
		// the Stream interface. This is not what you need. Most of the time you need to put these breakpoints in
		// the code of your lambda expressions.
	}

	public static void s8() { // Concatenating Streams 链接组合流
		List<Integer> list0 = Arrays.asList(1, 2, 3);
		List<Integer> list1 = Arrays.asList(4, 5, 6);
		List<Integer> list2 = Arrays.asList(7, 8, 9);
		// 最常用的方法是使用 Stream 的工厂方法 concat 对两个流进行合并，
		List<Integer> l1 = Stream.concat(list0.stream(), list1.stream()).collect(Collectors.toList());
		System.out.println("l1 = " + l1); // l1 = [1, 2, 3, 4, 5, 6]
		// 如果要对多个流进行合并，那么 Java API 推荐使用基于 flatMap 的形式
		List<Integer> l2 = Stream.of(list0.stream(), l1.stream(), list2.stream()).flatMap(Function.identity()).collect(Collectors.toList());
		System.out.println("l2 = " + l2); // l2 = [1, 2, 3, 1, 2, 3, 4, 5, 6, 7, 8, 9]
	}

	public static void s7() { // Limiting and Skipping the Elements of a Stream
		List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		// skip 执行后返回一个新的 Stream 流，因此 limit 会从第原来的流中的第三个元素开始处理新的流，而对它自己来说是从第一个元素开始处理的。
		// 因为每一个对流进行的中间操作都会创建并返回一个新的流。
		List<Integer> newInts = ints.stream().skip(2).limit(10).collect(Collectors.toList());
		System.out.println("newInts = " + newInts); // newInts = [3, 4, 5, 6, 7, 8, 9]
	}

	public static void s6() { // Removing Duplicates and Sorting a Stream
		List<String> list = Arrays.asList("good", "bad", "far", "far", "good", "bad", "test");
		//  The distinct() method uses the hashCode() and equals() methods to spot the duplicates.
		// Indeed, to spot duplicates, the distinct() method needs to store the elements of your stream.
		// When it processes an element, it first checks if that element has already been seen or not.
		String result = list.stream().distinct().sorted().reduce(String::concat).get(); // result = badfargoodtest
		System.out.println("result = " + result);
	}

	public static void s5() { // Filtering a Stream
		// 过滤一个流就是排除一个流中不满足 Predicate 预言的表达式的元素并返回满足预言的元素的新的 Stream 流。
		List<String> list = Arrays.asList("good", "Good", "bad", "Bad");
		// 过滤出首字母是大写的字符串
		List<String> match = list.stream().filter(str -> str.charAt(0) <= 'Z' && str.charAt(0) >= 'A').collect(Collectors.toList());
		System.out.println("match = " + match); // match = [Good, Bad]
	}

	public static void s4() { // Mapping a Stream to Another Stream
		// Mapping a stream consists of transforming its elements using a function. This transformation may change the
		// types of the elements processed by that stream, but you can also transform them without changing their type.
		List<String> strings = Arrays.asList("one", "two", "three", "four");
		// Stream 的中间操作（intermediate operation）不会处理数据，只有终端操作（terminal operation）才会处理数据。
		List<Integer> counts = strings
				.stream()
//				.map(Streams3App::countLengthOfString) // 静态方法引用
				.map(String::length) // 实例方法引用
				.collect(Collectors.toList());
		System.out.println("counts = " + counts); // counts = [3, 3, 5, 4]

		// There is no collect() method that takes a Collector as an argument on specialized streams. 对于 IntStream
		// 、DoubleStream、LongStream 这三个特殊流 没有提供对应的 collect 方法。
		// This summaryStatistics() method is very handy and is only available on these specialized streams of primitive types.
		IntSummaryStatistics statistics = strings.stream().mapToInt(String::length).summaryStatistics();
		System.out.println("statistics = " + statistics); // statistics = IntSummaryStatistics{count=4, sum=15, min=3, average=3.750000, max=5}
	}

	public static Integer countLengthOfString(String str) {
		return str.length();
	}

	public static void s3() { // Avoiding Boxing with Specialized Streams of Numbers
		IntSummaryStatistics iss = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).summaryStatistics();
		System.out.println("iss = " + iss); // iss = IntSummaryStatistics{count=10, sum=55, min=1, average=5.500000, max=10}
		// iss.getXXX 获得详细的各项统计值
		long sum = iss.getSum(); // 不用自动装箱拆箱
		System.out.println("sum = " + sum);

		List<User> users = LongStream.of(100L, 200L, 1000L, 2000L)
				.filter(Streams3App::filterSalary)
				.mapToObj(User::new)
				.collect(Collectors.toList());
		users.forEach(Streams3App::printUser);
	}

	public static void printUser(User user) {
		System.out.println("user = " + user);
	}

	public static boolean filterSalary(long salary) {
		return salary >= 1000L;
	}

	public static void s2() {
		List<String> list = Arrays.asList("egg", "APPLE", "china", "CHINA");
		Stream<String> s1 = list.stream().distinct();
		Stream<String> s2 = s1.filter(s -> s.length() > 3);
		s2.forEach(System.out::println);
		long count = s2.count(); // Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
		System.out.println("count = " + count);
	}

	public static void s1() { // 这个例子蛮复杂的
		List<String> strings = Arrays.asList("one", "two", "three", "four");
		Map<Integer, Long> map = strings.stream()
				.collect(groupingBy(String::length, counting()));
		map.forEach((key, value) -> System.out.println(key + " :: " + value));
	}
}

class User {
	private Long salary;

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public User(Long salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "User{" +
				"salary=" + salary +
				'}';
	}
}