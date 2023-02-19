package com.guhe.collection;

import java.util.*;

/**
 * Handling Map Values with Lambda Expressions
 * <a href="https://dev.java/learn/api/collections-framework/maps-and-lambdas">...</a>
 *
 * @author njl
 * @date 2023/2/19
 */
public class Collection4App {
	public static void main(String[] args) {
//		c1();
//		c2();
//		c3();
//		c4();
		c5();
	}

	public static void c5() { // Merging Values
		// default V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction)
		List<String> strings = Arrays.asList("one", "two", "three", "four", "five", "six", "seven");
		HashMap<Integer, String> map = new HashMap<>();
		for (String word : strings) {
			int length = word.length();
			map.merge(length, word, (existingValue, newWord) -> {
				System.out.println(word);
				System.out.println(existingValue);
				System.out.println(newWord);
				// word 和 newWorld 是一样的
				return existingValue + ", " + newWord;
			});
		}
		System.out.println("map = " + map);
		map.forEach((key, value) -> System.out.println(key + " :: " + value));
	}

	public static void c4() { // Computing Values 的使用案例
		// 假设有 [one two three four five six seven]，创建一个 Map，键是每一个字符串的长度，值是对应长度的字符串集合
		HashMap<Integer, List<String>> map = new HashMap<>();
		List<String> words = Arrays.asList("one", "two", "three", "four", "five", "six", "seven");
		for (String word : words) {
			int length = word.length();

//			if (!map.containsKey(length)) {
//				map.put(length, new ArrayList<>());
//			}

			// 使用 putIfAbsent 替换上面的 if
//			map.putIfAbsent(length, new ArrayList<>());
//			map.get(length).add(word);

			// 通过使用 computeIfAbsent 进一步简化上面两行代码
			// computeIfAbsent 返回的是：the current (existing or computed) value associated with the specified key,
			// or null if the computed value is null
			// 如果 key 存在，则不会调用 Function 方法创建并返回新的值，反之，会返回那个 key 已经绑定的值。
			map.computeIfAbsent(length, key -> new ArrayList<>()).add(word);
		}
		map.forEach(Collection4App::printKeyAndValueOfMap);
	}

	public static void c3() { // Computing Values
		HashMap<Integer, String> map = new HashMap<>();
		map.put(2, "two");
		map.put(3, null);
		// Attempts to compute a mapping for the specified key and its current
		// mapped value (or null if there is no current mapping).
		// default V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)
		map.compute(2, (key, value) -> key + value);
		System.out.println("map = " + map); // map = {2=2two}

		// If the value for the specified key is present and non-null, attempts to compute a new mapping given
		// the key and its current mapped value.
		// default V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)
		String s2 = map.computeIfPresent(1, (key, value) -> Integer.toString(key << 1) + value.concat(value));
		System.out.println("s2 = " + s2); // s2 = null 因为 key 为 1 的键在 map 中不存在
		map.computeIfPresent(2, (key, value) -> Integer.toString(key << 1) + value.concat(value));
		System.out.println("map = " + map); // map = {2=42two2two}

		// If the specified key is not already associated with a value (or is mapped to null),
		// attempts to compute its value using the given mapping function and enters it into this map unless null.
		// default V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)
		String s1 = map.computeIfAbsent(1, key -> "one");
		System.out.println("s1 = " + s1); // s1 = one
		// computeIfAbsent 返回：return the current (existing or computed) value associated with the specified key, or null if the computed value is null
		// key 为 3 的键对应的 value 为 null，因此满足 computeIfAbsent 的约束条件，会计算一个新值替换原来的 null
		String s3 = map.computeIfAbsent(3, key -> "three");
		System.out.println("s3 = " + s3); // s3 = three
		System.out.println("map = " + map); // map = {1=one, 2=42two2two, 3=three}

		// In all cases, if your bifunction (or function) returns a null value, then the key is removed from
		// the map: no mapping is created for that key.
		// No key/value pair with a null value can be put in the map using one of these three methods.
		map.computeIfPresent(3, (key, value) -> null);
		// key 为 3 的键值对被删除了
		System.out.println("map = " + map); // map = {1=one, 2=42two2two}

		// In all cases, the value returned is the new value bound to that key in the map or null if
		// the remapping function returned null.
		// It is worth pointing out that this semantic is different from the put() methods.
		// The put() methods return the previous value, whereas the compute() methods return the new value.
	}

	public static void c2() { // Replacing Values
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "one");
		map.put(2, "two");
		map.put(3, "three");
		// Map 接口提供了三个方法用另一个值替换与一个键绑定的值
		// default V replace(K key, V value)
		// 找到 map 中对应的 key，用 value 进行替换
		String oldValue1 = map.replace(1, "ONE");
		System.out.println("oldValue1 = " + oldValue1); // oldValue1 = one
		// default boolean replace(K key, V oldValue, V newValue)
		// 只有在 key 和 oldValue 匹配 map 中的键值对映射时才会用新值替换旧值，并返回 true
		boolean b1 = map.replace(2, "twe", "TWO"); // false
		boolean b2 = map.replace(2, "two", "TWO"); // true
		System.out.println("b1 = " + b1);
		System.out.println("b2 = " + b2);
		// default void replaceAll(BiFunction<? super K, ? super V, ? extends V> function)
		// 这里的泛型不难理解，前面两个是 super，最后一个是 extends，super 表示可以把 key 和 value
		// 用它们的父类型表示，而 extends 表示最终生成的新值可以是旧值的子类。
		// 比如这里，Integer 的 父类是 Number，String 的父类（实现的接口）是 CharSequence，String 不能又子类，
		// 所以 extends 无法举例。假设 String 可以有子类，那么这里可以返回 String 的子类对象。
		map.replaceAll((Number key, CharSequence value) -> key.toString() + value.toString());
		System.out.println("map = " + map); // map = {1=1ONE, 2=2TWO, 3=3three}
	}

	public static void c1() { // Consuming the Content of a Map
		// Map 接口提供了一个默认方法 default void forEach(BiConsumer<? super K, ? super V> action)，功能
		// 与 Iterable 接口类似，不过它接受一个 BiConsumer 对象用来消费每一个 Entry。这里的通配符使用 super 关键字
		// 是因为 PECS 原则，我们可以把 Entry 中的 key 或者 value 用它们的父类表示也能进行消费。
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "one");
		map.put(2, "two");
		map.put(3, "three");
		map.forEach(Collection4App::printKeyAndValueOfMap); // 结合使用静态方法引用
	}

	//	public static void printKeyAndValueOfMap(Integer key, String value) {
	public static void printKeyAndValueOfMap(Object key, Object value) {
		System.out.println(key + " :: " + value);
	}
}
