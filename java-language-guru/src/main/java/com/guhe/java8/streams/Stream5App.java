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
//		s1();
		s2();
	}

	public static void s2() {
		// Converting a stream to the Collection (Collection, List or Set):
		List<Product> productList = Arrays.asList(
				new Product(23, "potatoes"),
				new Product(14, "orange"), new Product(13, "lemon"),
				new Product(23, "bread"), new Product(13, "sugar")
		);
		List<String> nameList = productList.stream().map(Product::getName).collect(Collectors.toList());
		// nameList = [potatoes, orange, lemon, bread, sugar]
		System.out.println("nameList = " + nameList);

		// Reducing to String
		String s = productList.stream().map(Product::getName).collect(Collectors.joining(", ", "[", "]"));
		// s = [potatoes, orange, lemon, bread, sugar]
		System.out.println("s = " + s);

		// Processing the average value of all numeric elements of the stream:
		Double sum1 = productList.stream().collect(Collectors.averagingDouble(Product::getPrice));
		// sum1 = 17.2
		System.out.println("sum1 = " + sum1);
		double averagePrice = productList.stream()
				.collect(Collectors.averagingInt(Product::getPrice));
		// averagePrice = 17.2
		System.out.println("averagePrice = " + averagePrice);

		// Processing the sum of all numeric elements of the stream:
		int sum3 = productList.stream().mapToInt(Product::getPrice).sum();
		System.out.println("sum3 = " + sum3);

		// Collecting statistical information about stream’s elements:
		IntSummaryStatistics statistics = productList.stream().collect(Collectors.summarizingInt(Product::getPrice));
		// statistics = IntSummaryStatistics{count=5, sum=86, min=13, average=17.200000, max=23}
		System.out.println("statistics = " + statistics);

		// Grouping of stream’s elements according to the specified function:
		Map<Integer, List<Product>> map1 = productList.stream().collect(Collectors.groupingBy(Product::getPrice));
		// map1 = {23=[Product{name='potatoes', price=23}, Product{name='bread', price=23}], 13=[Product{name='lemon', price=13}, Product{name='sugar', price=13}], 14=[Product{name='orange', price=14}]}
		System.out.println("map1 = " + map1);

		// Dividing stream’s elements into groups according to some predicate:
		Map<Boolean, List<Product>> map2 = productList.stream().collect(Collectors.partitioningBy(elem -> elem.getPrice() > 15));
		// map2 = {false=[Product{name='orange', price=14}, Product{name='lemon', price=13}, Product{name='sugar', price=13}], true=[Product{name='potatoes', price=23}, Product{name='bread', price=23}]}
		System.out.println("map2 = " + map2);

		// Parallel Streams
		Stream<Product> p1 = productList.stream().parallel();
		Stream<Product> p2 = productList.parallelStream();
		boolean parallel = p1.isParallel();
		// parallel = true
		System.out.println("parallel = " + parallel);
		Optional<Product> o1 = p1.findFirst();
		if (o1.isPresent()) {
			// product = Product{name='potatoes', price=23}
			Product product = o1.get();
			System.out.println("product = " + product);
		}
		boolean match = p2.map(product -> product.getPrice() * 12).anyMatch(price -> price > 200);
		// match = true
		System.out.println("match = " + match);
	}

	public static void s1() { // Collecting Stream Elements in a Collection, or an Array
		// 一个流不执行终端操作不会处理流中的任何数据（中间操作不会被执行），且一个流只能执行一次终端操作，但是可以执行多次中间操作。

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

class Product {
	private String name;
	private Integer price;

	public Product(Integer price, String name) {
		this.name = name;
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public Integer getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Product{" +
				"name='" + name + '\'' +
				", price=" + price +
				'}';
	}
}
