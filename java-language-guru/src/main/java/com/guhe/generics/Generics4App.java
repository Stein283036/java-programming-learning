package com.guhe.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author njl
 * @date 2023/2/17
 */
public class Generics4App {
	public static void main(String[] args) {
//		g1();
		g2();
	}

	public static <E> void append(List<E> list, Class<E> cls) throws InstantiationException, IllegalAccessException {
		E e = cls.newInstance();
		list.add(e);
	}

	public static <E> void append(List<E> list) { // 不能创建类型参数实例
//		E elem = new E();  // compile-time error
//		list.add(elem);
	}

	public static void g2() {
		List<String> stringListA = new ArrayList<String>();
		List<String> stringListB = new ArrayList<String>();

		ArrayBuilder.addToList(stringListA, "Seven", "Eight", "Nine");
		ArrayBuilder.addToList(stringListB, "Ten", "Eleven", "Twelve");
		List<List<String>> listOfStringLists =
				new ArrayList<List<String>>();
		ArrayBuilder.addToList(listOfStringLists,
				stringListA, stringListB);

		ArrayBuilder.faultyMethod(Arrays.asList("Hello!"), Arrays.asList("World!"));
	}

	public static void g1() {
		MyNode mn = new MyNode(5);
		Node n = mn;            // A raw type - compiler throws an unchecked warning
		n.setData("Hello");     // Causes a ClassCastException to be thrown.
		Integer x = mn.data;
	}
}

class ArrayBuilder {

	public static <T> void addToList(List<T> listArg, T... elements) {
		for (T x : elements) {
			listArg.add(x);
		}
	}

	public static void faultyMethod(List<String>... l) {
		Object[] objectArray = l;     // Valid
		objectArray[0] = Arrays.asList(42);
		String s = l[0].get(0);       // ClassCastException thrown here
	}

}

class Node<T> {

	public T data;

	public Node(T data) {
		this.data = data;
	}

	public void setData(T data) {
		System.out.println("Node.setData");
		this.data = data;
	}
}

class MyNode extends Node<Integer> {
	public MyNode(Integer data) {
		super(data);
	}

	public void setData(Integer data) {
		System.out.println("MyNode.setData");
		super.setData(data);
	}
}