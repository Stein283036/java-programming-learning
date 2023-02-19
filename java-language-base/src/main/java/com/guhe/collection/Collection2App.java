package com.guhe.collection;

import java.util.ArrayDeque;

/**
 * Storing Elements in Stacks and Queues
 *
 * @author njl
 * @date 2023/2/19
 */
public class Collection2App {
	public static void main(String[] args) {
//		c1();
		c3();
	}

	public static void c4() { // Staying Away from the Stack Class
		// Stack 类 继承自 Vector 类，从 JDK1.0 就引入了，是一个原始的集合类，虽然 Vector 没有被
		// 弃用，但是不推荐使用，Stack 类也是如此。
		// Vector 和 Stack 都是线程安全的，如果不需要线程安全，那么可以使用 Deque 和 ArrayDeque，如果需要
		// 线程安全，那么可以使用 BlockingQueue 及它的实现，是并发编程的范围了。
	}

	public static void c3() { // Queue 和 Deque 的实现
		// Java 集合框架给我们提供了三个 Queue 和 Deque 的实现（不包括并发编程 JUC 下的实现），
		// 分别是 ArrayDeque 实现了 Queue 和 Deque、LinkedList 实现了 Queue 和 Deque、PriorityQueue 只实现了 Queue。
		// ArrayDeque 底层由数组支持，在添加元素时如果容量不够会自动扩容，因此总是接受添加新的元素。
		// LinkedList 底层由双向链表支持，使访问头尾元素非常方便，LinkedList 总是接受添加新的元素。
		// PriorityQueue 底层由堆支持，通过元素的自然顺序或者通过构造 PriorityQueue 对象时传入的 Comparator 对象来
		// 维护元素添加进队列的顺序，队列的头部总是通过排序后最小的元素，容量也是会自动扩容，因此总是可以添加新的元素。

		ArrayDeque<String> q1 = new ArrayDeque<>();
		q1.offerLast("a");
		q1.offerLast("b");
		q1.offerFirst("c");
		System.out.println("q1 = " + q1);
	}

	public static void c2() { // 使用 Deque 来模拟队列和栈
		// Deque 是 Queue 接口的子接口，在 Queue 接口的方法基础上，同时拓展了对
		// 队列和栈两种数据结构的操作方法，其中哪些方法是用于队列的，哪些方法是用于
		// 栈的都在源码注释中写的十分清楚，只需要看一下就能理清它们之间的区别。
	}

	public static void c1() { // Queue Hierarchy
		// Java SE 5 saw the addition of a new interface in the Collections Framework: the Queue interface,
		// further extended in Java SE 6 by the Deque interface.
		// The Queue interface is an extension of the Collection interface.
		// 队列这种数据结构在 Java 集合框架中使用 Queue 表示，栈数据结构使用 Deque 双端队列表示。
		// 观察 Queue 接口可以发现对于基本的方法 入队、出队、查看队首元素，都分别提供了两种不同版本的方法，
		// 一种是在特定情况（队列为空或者队列已满）下抛出异常的，另一种是在特定情况下（队列为空或者队列已满）不会抛出异常的。
		// 比如 poll 和 remove，调用 remove 方法会在
		// 队列为空的适合抛出 NoSuchElementException 异常，而 poll 则不会，其它两个也是同理。
	}
}
