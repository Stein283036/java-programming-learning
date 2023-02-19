package com.guhe.collection;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Keeping Keys Sorted with SortedMap and NavigableMap
 * 这篇文章没有看完，等需要使用这些不流行的容器的时候再看看吧
 * <a href="https://dev.java/learn/api/collections-framework">...</a>
 *
 * @author njl
 * @date 2023/2/20
 */
public class Collection5App {
	public static void main(String[] args) {
		c1();
	}

	public static void c1() { // Methods Added by SortedMap
		// JDK 提供了 Map 的两个子接口 SortedMap 和 NavigableMap（继承了 SortedMap），TreeMap 同时实现了
		// 这两个接口，TreeMap 是一个红黑树
		// SortedMap and NavigableMap keep their key/value pairs sorted by key.
		// 因此要使用 这两个排序接口，你添加进 Map 中的 key 必须实现 Comparable 接口或者创建 TreeMap 的时候传入一个自定义的
		// Comparator 比较器对象，此时即使 key 是可以比较的，也会使用这个比较器对象进行排序，而不会依赖 key 的自然排序。
		SortedMap<Integer, String> map1 = new TreeMap<>();
		map1.put(1, "one");
		map1.put(2, "two");
		map1.put(3, "three");
		map1.put(5, "five");
		map1.put(6, "six");

		Integer firstKey = map1.firstKey(); // 1
		System.out.println("firstKey = " + firstKey);
		Integer lastKey = map1.lastKey(); // 6
		System.out.println("lastKey = " + lastKey);
		// SortedMap.headMap 返回严格小于 toKey 的 Map 的一部分视图
		SortedMap<Integer, String> map2 = map1.headMap(5);
		System.out.println("map2 = " + map2); // map2 = {1=one, 2=two, 3=three}
		String s1 = map2.remove(3); // s1 = three
//		map2.put(7, "seven"); // IllegalArgumentException
		System.out.println("s1 = " + s1);
		System.out.println("map1 = " + map1); // map1 = {1=one, 2=two, 5=five, 6=six}

		// SortedMap.tailMap 返回大于或等于 fromKey 的 Map 的部分视图
		SortedMap<Integer, String> map3 = map1.tailMap(3);
		System.out.println("map3 = " + map3); // map3 = {5=five, 6=six}
	}
}
