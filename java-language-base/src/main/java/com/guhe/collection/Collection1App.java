package com.guhe.collection;

import java.util.*;

/**
 * Extending Collection with Set, SortedSet and NavigableSet
 * Creating and Processing Data with the Collections Factory Methods
 *
 * @author njl
 * @date 2023/2/19
 */
public class Collection1App {
	public static void main(String[] args) {
//		c1();
//		c2();
//		c3();
//		c4();
//		c5();
//		c6();
		c7();
	}

	public static void c8() { // Wrapping a Collection in a Synchronized Collection
		// 与支持不可变集合功能类似，Collections 也为我们提供了很多创建线程安全的集合方法，名称规范
		// 也与创建不可变包装器类似，以 synchronized 开始，以要创建的集合类型结束。
		// Synchronizing collections using the Collections factory methods may not be your best choice.
		// The Java Util Concurrent framework has better solutions to offer.
		// 优先使用 JUC 的并发集合框架中的实现
		// 观察源码可以发现，返回的同步容器包装器对象都是 Collections 的内部类对象，其中对于在多线程环境下需要进行同步方法的方法
		// 都使用了 synchronized 关键字应用在代码块级别，通过 final Object mutex; 这个互斥变量控制多线程的同步访问。
		List<String> l1 = Arrays.asList("a", "b", "c");
		List<String> l2 = Collections.synchronizedList(l1);
	}

	public static void c7() { // Wrapping a Collection in an Immutable Collection
		// Collections 集合工具类给我们提供了一些方法创建包装了 List 或 Map 的不可变的集合类，
		// 包装的集合元素内容不是复制的，而是原来集合的引用，所有尝试对不可变集合的修改操作都会抛出
		// 异常，比如 UnSupportedOperationException，这些方法都以 unmodifiable 开始，以你
		// 要包装的集合类型结尾。
		List<String> l1 = new ArrayList<>();
		l1.add("1");
		l1.add("2");
		l1.add("3");
		l1.add("4");
		l1.add("5");
		List<String> l2 = Collections.unmodifiableList(l1);
//		l2.add("5"); // UnsupportedOperationException
		// 虽然不可以通过 l2 这个不可变集合包装器对象修改集合，但是可以通过目标集合（源集合） l1 修改，
		// 改变后的结果也会影响 l2 因为 l2 内部引用了 l1 源集合对象，而不是集合元素的拷贝，所以这里是浅拷贝。
		l1.add("5");
		boolean has5 = l2.contains("5");
		System.out.println("has5 = " + has5); // has5 = true
	}

	public static void c6() { // Changing the Order of the Elements of a List
		// sort()
		// public static <T extends Comparable<? super T>> void sort(List<T> list)
		// public static <T> void sort(List<T> list, Comparator<? super T> c)
		List<Integer> l1 = Arrays.asList(10, 9, 12, 100, 50);
		Collections.sort(l1); // 按照升序排序就地改变集合中的元素，要求集合中的元素必须实现 Comparable 接口
		System.out.println("l1 = " + l1); // l1 = [9, 10, 12, 50, 100]

		// shuffle() randomly shuffles the elements of the provided list.
		// You can provide your instance of Random if you need a random shuffling that you can repeat.
		List<Integer> l2 = Arrays.asList(10, 9, 12, 100, 50);
		Collections.shuffle(l2);
		System.out.println("l2 = " + l2); // l2 = [9, 10, 100, 12, 50]

		// rotate() rotates the elements of the list. After a rotation the element at index 0 will
		// be found at index 1 and so on.
		List<Integer> l3 = Arrays.asList(10, 9, 12, 100, 50);
		Collections.rotate(l3, 1);
		System.out.println("l3 = " + l3); // l3 = [50, 10, 9, 12, 100]

		// reverse(): reverse the order of the elements of the list.
		List<Integer> l4 = Arrays.asList(10, 9, 12, 100, 50);
		Collections.reverse(l4);
		System.out.println("l4 = " + l4); // l4 = [50, 100, 12, 9, 10]

		// swap(): swaps two elements from the list.
		List<Integer> l5 = Arrays.asList(10, 9, 12, 100, 50);
		Collections.swap(l5, 2, 4);
		System.out.println("l5 = " + l5); // l5 = [10, 9, 50, 100, 12]
	}

	public static void c5() { // Finding a Sublist in a List
		// public static int indexOfSubList(List<?> source, List<?> target)
		// 找到 target 集合中第一个元素在 source 集合中第一次出现的索引位置，如果没有返回 -1
		ArrayList<String> source = new ArrayList<>();
		source.add("apple");
		source.add("banana");
		source.add("orange");
		source.add("orange");
		ArrayList<String> target = new ArrayList<>();
		target.add("orange");
		int index = Collections.indexOfSubList(source, target);
		System.out.println("index = " + index); // 2

		// public static int lastIndexOfSubList(List<?> source, List<?> target)
		// 找到 target 集合中第一个元素在 source 集合中最后一次出现的索引位置，如果没有返回 -1
		int lastIndex = Collections.lastIndexOfSubList(source, target);
		System.out.println("lastIndex = " + lastIndex); // 3
	}

	public static void c4() {
		// 如果调用 Collections 的 max(Collection<? extends T> coll) 方法来返回集合中的最大元素，那么该集合中的元素必须实现
		// 了 Comparable 接口，否则会抛出 ClassCastException。
		String max = Collections.max(Arrays.asList("apple", "blue", "zoo", "far"));
		System.out.println("max = " + max);
		Comparator<String> comparator = (s1, s2) -> {
			if (s1.length() > s2.length()) {
				return 1;
			} else if (s1.length() < s2.length()) {
				return -1;
			}
			return 0;
		};
		// 通过显示地传入一个 Comparator 实例来使用自定义的比较顺序，而不是按照元素实现的 Comparable 接口的默认自然顺序，
		// 这样就可以覆盖元素的默认自然顺序了，既然采用指定的比较器对象。
		String min = Collections.min(Arrays.asList("elephant", "pig", "do"), comparator);
		System.out.println("min = " + min);
	}

	public static void c3() {
		TreeSet<String> strings = new TreeSet<>();
		strings.add("zoo");
		strings.add("apple");
		strings.add("blue");
		strings.add("dog");
		strings.add("egg");
		System.out.println("strings = " + strings); // strings = [apple, blue, dog, egg, zoo]
		NavigableSet<String> descendingSet = strings.descendingSet(); // 获得目标集合的降序视图集合
		System.out.println("descendingSet = " + descendingSet); // descendingSet = [zoo, egg, dog, blue, apple]
		descendingSet.add("good"); // 对返回的降序集合视图的改变也会影响原来的目标集合
		System.out.println(strings.contains("good")); // true

		// 按照降序顺序获得遍历元素的迭代器
		Iterator<String> descendingIterator = strings.descendingIterator();
		while (descendingIterator.hasNext()) {
			String next = descendingIterator.next();
			System.out.println("next = " + next);
		}
	}

	public static void c2() {
		TreeSet<Double> doubles = new TreeSet<>();
		doubles.add(1.1);
		doubles.add(2.9);
		doubles.add(3.5);
		doubles.add(3.2);
		Double ceiling = doubles.ceiling(2.4);
		System.out.println("ceiling = " + ceiling); // 2.9
		Double floor = doubles.floor(3.4);
		System.out.println("floor = " + floor); // 3.2
		Double lower = doubles.lower(1.5);
		System.out.println("lower = " + lower); // 1.1
		Double higher = doubles.higher(3.1);
		System.out.println("higher = " + higher); // 3.2
		Double lowest = doubles.pollFirst();
		System.out.println("lowest = " + lowest); // 1.1
		Double highest = doubles.pollLast();
		System.out.println("highest = " + highest); // 3.5
	}

	public static void c1() {
		TreeSet<String> strings = new TreeSet<>();
		strings.add("a");
		strings.add("b");
		strings.add("c");
		strings.add("d");
		strings.add("e");
		strings.add("f");
		// 使用 SortedSet<E> subSet(E fromElement, E toElement);
		SortedSet<String> subSet = strings.subSet("a", "d");
		System.out.println("subSet = " + subSet); // [a, b, c]
		// 因为子集合获取的元素范围是 [a, d)，所以不能添加大于等于 d 的元素，也不能添加小于 a 的元素，
		// 否则会抛出 IllegalArgumentException
//		subSet.add("d"); // IllegalArgumentException

		// 使用 NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement,   boolean toInclusive);
		NavigableSet<String> strings2 = strings.subSet("a", true, "d", true);
		System.out.println("strings2 = " + strings2);
	}
}
