package com.stein.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author njl
 * @date 2023/2/9
 */
public class Generics2App {
	public static void main(String[] args) {
//		g1();
		g2();
	}

	public static void g2() {
		List<?>[] lsa = new List<?>[10]; // OK, array of unbounded wildcard type.
		Object[] oa = lsa;
		List<Integer> li = new ArrayList<>();
		li.add(3);
		oa[1] = li; // Correct.
		Integer i = (Integer) lsa[1].get(0); // OK
	}

	public static void g1() {
		Class<? super String> cls1 = String.class.getSuperclass(); // 表示 cls1 的类型是 String 或者 String 的父类
		Class<? super Object> cls2 = Object.class.getSuperclass();
		System.out.println("cls1 = " + cls1);
		System.out.println("cls2 = " + cls2);

		// 可以声明带泛型的数组，但不能用new操作符创建带泛型的数组
		Pair<String>[] p1;
//		Pair<String>[] p2 = new Pair<String>[2];
		// 必须通过强制转型为泛型数组
		Pair<String>[] p3 = (Pair<String>[]) new Pair[2];

		// 泛型数组的使用注意
		Pair[] p4 = new Pair[2];
		Pair<String>[] p5 = (Pair<String>[]) p4;
		p5[0] = new Pair<>("Geeks", "Linux"); // 编译器会强制检查 p5 的泛型类型
		p4[1] = new Pair<>(1, 2);
		Pair<String> p6 = p5[1];
		System.out.println("p6 = " + p6);
	}

	public static int add(Pair2<? extends Number> p) {
		Number first = p.getFirst(); // 编译器只知道 p 一定是 Number 类型 但是具体运行时是哪个类型它不知道
		Number last = p.getLast();
//		p.setFirst(Integer.valueOf(first.intValue() + 100));
//		p.setLast(Integer.valueOf(last.intValue() + 200));
		return p.getFirst().intValue() + p.getLast().intValue();
	}
}

class Test1 {
	// 泛型上限方法
	public static <T extends Number> Number add(T t1, T t2) {
		if (t1 == null || t2 == null) {
			throw new RuntimeException();
		}
		if (t1.getClass() == Integer.class && t2.getClass() == Integer.class) {
			return t1.intValue() + t2.intValue();
		} else if (t1.getClass() == Double.class && t2.getClass() == Double.class) {
			return t1.doubleValue() + t2.doubleValue();
		}
		return null;
	}
}


class Pair2<T> {
	private T first;
	private T last;

	public Pair2(T first, T last) {
		this.first = first;
		this.last = last;
	}

	public T getFirst() {
		return first;
	}

	public T getLast() {
		return last;
	}

	public void setFirst(T first) {
		this.first = first;
	}

	public void setLast(T last) {
		this.last = last;
	}
}
