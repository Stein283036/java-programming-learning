package com.guhe.generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author njl
 * @date 2023/2/16
 */
public class Generics3App {
	public static void main(String[] args) {

	}

	public static void g5() {
		List<EvenNumber> l1 = new ArrayList<>();
		List<? extends NaturalNumber> l2 = l1; // l1 是 l2 的子类
//		l2.add(new NaturalNumber(1));
		l2.add(null);
		l2.clear();
		Iterator<? extends NaturalNumber> iterator = l2.iterator();
		while (iterator.hasNext()) {
			iterator.remove();
		}
	}

	void foo(List<?> i) {
//		i.set(0, i.get(0));
		fooHelper(i);
	}

	private <T> void fooHelper(List<T> list) {
		list.set(0, list.get(0));
	}

	public static void g4() {
		// In order to create a relationship between these classes so that
		// the code can access Number's methods through List<Integer>'s elements,
		// use an upper bounded wildcard:
		List<? extends Integer> l1 = new ArrayList<>();
		List<? extends Number> l2 = l1; // List<? extends Integer> is a subtype of List<? extends Number>

		List<? super Number> l3 = new ArrayList<>(); // Number Object
		List<? super Integer> l4 = l3; // Integer Number Object
	}

	public static void g3() {
		// It's important to note that List<Object> and List<?> are not the same. You can insert an Object,
		// or any subtype of Object, into a List<Object>. But you can only insert null into a List<?>.
		List<Object> l1 = new ArrayList<>();
		l1.add("Hello");
		l1.add(new Object());
		List<?> l2 = new ArrayList<>();
		l2.add(null);
	}

	public static void g2() {
		Pair3<Integer, String> p1 = new Pair3<>(1, "apple");
		Pair3<Integer, String> p2 = new Pair3<>(2, "pear");
		boolean compare = Util.compare(p1, p2); // 调用 compare 泛型方法的 K 是 Integer V 是 String
		System.out.println("compare = " + compare);
	}

	public static void g1() {
		// 泛型参数只能是引用数据类型，如类、接口、数组、枚举、注解、甚至是其它泛型参数类型
		Box<int[]> box1 = new Box<>(); // 泛型参数使用数组
		Box<Box<String>> box2 = new Box<>(); // 泛型参数使用另一种泛型参数
		// 直接创建泛型类的原始类型
		Box box3 = new Box(); // Box是泛型类型的原始类型Box<T>
		// 原始类型出现在遗留代码中，因为许多 API 类（例如 Collections 类）在 JDK 5.0 之前不是通用的。
		// 当使用原始类型时，你基本上得到了前泛型行为——一个盒子给你对象。为了向后兼容，允许将参数化类型分配给其原始类型：
		Box<String> box4 = new Box<>();
		box3 = box4; // 无编译器警告
		// 如果将原始类型分配给参数化类型，则会收到警告：
		Box box5 = new Box();
		Box<String> box6 = box5;

	}
}

class NaturalNumber {

	private int i;

	public NaturalNumber(int i) {
		this.i = i;
	}
	// ...
}

class EvenNumber extends NaturalNumber {

	public EvenNumber(int i) {
		super(i);
	}
	// ...
}

class Util {
	public static <K, V> boolean compare(Pair3<K, V> p1, Pair3<K, V> p2) {
		return p1.getKey().equals(p2.getKey()) && p1.getValue().equals(p2.getValue());
	}

	public static void addNumbers(List<? super Integer> list) {
		for (int i = 1; i <= 10; i++) {
			list.add(i);
		}
	}

	public static <T extends Comparable<T>> int countGreaterThan(T[] arr, T elem) {
		int count = 0;
		for (T t : arr) {
			if (t.compareTo(elem) > 0) {
				count++;
			}
		}
		return count;
	}
}

class Pair3<K, V> {

	private K key;
	private V value;

	public Pair3(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}
}

class Box<T> { // T 泛型类型
	// T stands for "Type"
	private T t;

	public void boxTest(Box<? extends Number> n) { /* ... */ }

	public <U extends Number> void inspect(U u) {
		System.out.println("T: " + t.getClass().getName());
		System.out.println("U: " + u.getClass().getName());
	}

	public void set(T t) {
		this.t = t;
	}

	public T get() {
		return t;
	}
}