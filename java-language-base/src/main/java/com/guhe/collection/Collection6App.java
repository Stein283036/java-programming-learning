package com.guhe.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Choosing Immutable Types for Your Key
 *
 * @author njl
 * @date 2023/2/20
 */
public class Collection6App {
	public static void main(String[] args) {
		c1();
	}

	public static void c1() { // Avoiding the Use of Mutable Keys
		// Using mutable key is an anti-pattern, and you should definitely avoid doing that.
		// The side effects you may get if you do are terrible: you may end up making the content
		// of your map unreachable.

		Key one = new Key("1");
		Key two = new Key("2");

		Map<Key, String> map = new HashMap<>();
		map.put(one, "one");
		map.put(two, "two");

		System.out.println("map.get(one) = " + map.get(one));
		System.out.println("map.get(two) = " + map.get(two));

		// Map 的 key 被修改了
		// 因为 Key 的 hashCode 方法返回的是 return key.hashCode(); 而我们修改了对象的 key 字符串对象，因此再次计算
		// one 和 two 对象的哈希值就与修改前添加进 Map 集合中的哈希值不同，因此就无法取出对应的值了。
		one.setKey("one");
		two.setKey("two");
		System.out.println("map.get(one) = " + map.get(one));
		System.out.println("map.get(two) = " + map.get(two));
	}
}

class Key {
	private String key;

	public Key(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return key;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Key key = (Key) o;
		return Objects.equals(key, key.key);
	}

	@Override
	public int hashCode() {
		return key.hashCode();
	}
}
