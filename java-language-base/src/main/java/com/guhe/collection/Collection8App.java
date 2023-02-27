package com.guhe.collection;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Initialize a HashMap in Java
 *
 * @author njl
 * @date 2023/2/27
 */
public class Collection8App {
	public static void main(String[] args) {
//		c1();
		c2();
	}

	public static void c2() { // Using Collectors.toMap()
		Map<String, String> map = Stream.of(
				new String[][]{
						{"Hello", "World"},
						{"John", "Doe"}
				}
		).collect(Collectors.toMap(data -> data[0], data -> data[1]));
		System.out.println("map = " + map);
	}

	public static void c1() { // Using Java Collections
		// 返回的集合是 Collections 的静态内部类（SingletonMap）对象，这是一个不可变类
		Map<String, String> singletonMap = Collections.singletonMap("single", "单身");
		Map<Object, Object> emptyMap = Collections.emptyMap();
		emptyMap.put("a", "A");
	}
}
