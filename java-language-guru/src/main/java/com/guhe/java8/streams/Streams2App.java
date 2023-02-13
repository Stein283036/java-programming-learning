package com.guhe.java8.streams;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Parallel Streams
 *
 * @author njl
 * @date 2023/2/13
 */
public class Streams2App {
	private static final int max = 1000000;
	private static final List<String> values = new ArrayList<>(max);

	static {
		for (int i = 0; i < max; i++) {
			UUID uuid = UUID.randomUUID();
			values.add(uuid.toString());
		}
	}

	public static void main(String[] args) {
//		s1();
		s2();
	}

	public static void s2() { // Maps
		// maps do not directly support streams. There's no stream() method available on the Map interface itself,
		// however you can create specialized streams upon the keys,
		// values or entries of a map via map.keySet().stream(), map.values().stream() and map.entrySet().stream().
		Map<Integer, String> map = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			// putIfAbsent 只有在 Map 中不存在指定的 key 时才会插入键值对
			map.putIfAbsent(i, "val" + i);
		}
		map.forEach((key, value) -> {
			System.out.println(key + ": " + value);
		});
		// compute code on the map by utilizing functions
		// If the value for the specified key is present and non-null, attempts to compute a new
		// mapping given the key and its current mapped value.
		map.computeIfPresent(3, (key, value) -> value + key);
		System.out.println(map.get(3)); // val33
		// If the function returns null, the mapping is removed.
		map.computeIfPresent(9, (key, value) -> null);
		System.out.println(map.containsKey(9));
		System.out.println(map);
		// remove entries for a given key, only if it's currently mapped to a given value:
		map.remove(3, "val33");
		System.out.println(map.get(3));
		// 当 map 中没有指定的 key 时返回一个默认值
		String s1 = map.getOrDefault(43, "Not Found");
		System.out.println("s1 = " + s1);
		// 合并
	}

	public static void s1() {
		//  Operations on sequential streams are performed on a single thread while operations on parallel
		//  streams are performed concurrently on multiple threads.
		// 比较使用 parallel 和 sequential
		// Sequential Sort
//		long t0 = System.nanoTime();
//		long count = values.stream().sorted().count();
//		System.out.println(count);
//		long t1 = System.nanoTime();
//		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
//		System.out.println(String.format("sequential sort took: %d ms", millis));
		// parallel Sort
		long t0 = System.nanoTime();
		long count = values.parallelStream().sorted().count();
		System.out.println(count);
		long t1 = System.nanoTime();
		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("sequential sort took: %d ms", millis));
	}
}
