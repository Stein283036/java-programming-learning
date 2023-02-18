package com.guhe.java8.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.*;

/**
 * @author njl
 * @date 2023/2/18
 */
public class FunctionalInterfaces2App {
	public static void main(String[] args) {
//		f1();
//		f2();
//		f3();
//		f4();
//		f5();
//		f6();
//		f7();
//		f8();
//		f9();
//		f10();
//		f11();
//		f12();
//		f13();
//		f14();
//		f15();
		f16();
	}

	public static void f16() { // ToDoubleBiFunction
		// ToDoubleBiFunction 是专门用来产生 double 值的 BiFunction 的特殊形式
		ToDoubleBiFunction<Integer, Long> tdbf = Long::sum;
		double sum = tdbf.applyAsDouble(100, 200L);
		System.out.println("sum = " + sum);
	}

	public static void f15() { // IntBinaryOperator
		// IntBinaryOperator 是 BinaryOperator 的特殊形式，专门用于基本数据类型 int 的值
		IntBinaryOperator ibo = (i1, i2) -> i1 * i2;
		int sum = ibo.applyAsInt(10, 20);
		System.out.println("sum = " + sum);
		// 同理练习一下 LongBinaryOperator
		LongBinaryOperator lbo = (Math::addExact);
		long sum2 = lbo.applyAsLong(120000000, 20000000);
		System.out.println("sum2 = " + sum2);
	}

	public static void f14() { // BinaryOperator - BinaryOperator<T> extends BiFunction<T,T,T>
		// 二元运算符 BiFunction 的特殊形式
		BinaryOperator<String> bo1 = (s1, s2) -> s1 + s2;
		String s1 = bo1.apply("Hello", "World");
		System.out.println("s1 = " + s1);
	}

	public static void f13() { // Mapping Two Elements with a BiFunction
		BiFunction<String, String, Integer> bf1 = (word, sentence) -> sentence.indexOf(word);
		Integer i1 = bf1.apply("software", "I am a software developer.");
		System.out.println("i1 = " + i1);
	}

	public static void f12() { // Passing a Unary Operator to a List
		// You can transform the elements of a list with a UnaryOperator<T>. One could wonder
		// why a UnaryOperator<T> and not a basic Function. The answer is in fact quite simple:
		// once declared, you cannot change the type of a list.
		// So the function you apply can change the elements of the list, but not their type.
		// 只能在集合中应用一元运算符来改变集合中的元素，因为集合的元素类型一旦声明，那么就不能改变
		List<String> list = Arrays.asList("one", "two", "three");
		UnaryOperator<String> uo1 = String::toUpperCase;
		list.replaceAll(uo1);
		System.out.println("list = " + list);
	}

	public static void f11() { // Function 的特殊形式
		// UnaryOperator 一元运算符，函数式方法的接收参数类型和返回结果类型一致
		UnaryOperator<Integer> uo = integer -> Math.abs(integer);
		Integer i1 = uo.apply(-4);
		System.out.println("i1 = " + i1);

		// IntUnaryOperator 如果要对 Integer 类型做一元运算，那么可以直接使用 IntUnaryOperator
		IntUnaryOperator iuo = i2 -> Math.abs(i2);
		System.out.println(iuo.applyAsInt(-100));
	}

	public static void f10() { // Function
		// Functions are used in the Stream API to map objects to other objects
		// A predicate can be seen as a specialized type of function, that returns a boolean.
		Function<String, Integer> f = i -> i.length(); // 返回一个字符串的长度，自动装箱机智
		String word = "Hello Function";
		int integer = f.apply(word); // 自动拆箱机制
		System.out.println("integer = " + integer);
	}

	public static void f9() { // Passing a Predicate to a Collection
		// Arrays.asList 返回的 List 集合是 Arrays 类中的一个静态内部类，该类是一个不可变类，意味着
		// 对类的实例对象进行修改集合结构的操作都会抛出 UnsupportedOperationException，
		// 只能修改集合中已有的元素，因为修改不是一个改变集合结构的操作
		List<String> immutableStrings = Arrays.asList("one", "two", "three", "four", "five");
		List<String> strings = new ArrayList<>(immutableStrings);
		Predicate<String> p = s -> s.length() % 2 == 0;
		strings.removeIf(p); // 删除  strings 中字符串长度为偶数的元素
		System.out.println("strings = " + strings);
	}

	public static void f8() { // Predicate 的另一种变体 - BiPredicate
		BiPredicate<String, Integer> bp = (s, i) -> s.length() == 3 && i % 2 == 0;
		System.out.println(bp.test("ABC", 4));
		System.out.println(bp.test("ABC", 5));
	}

	public static void f7() { // Specialized Predicate(IntPredicate, DoublePredicate, LongPredicate)
		// IntPredicate
		IntPredicate ip = elem -> elem % 2 == 0; // elem 是 基本数据类型 int 而不是 Integer，这样就减小了额外的转换开销
		System.out.println("4 是偶数么？" + ip.test(4));
		System.out.println("5 是偶数么？" + ip.test(5));
	}

	public static void f6() { // Predicate
		Predicate<String> p1 = str -> str.equals(str.toUpperCase());
		System.out.println("HELLO 是全大写么？" + p1.test("HELLO"));
		System.out.println("hello 是全大写么？" + p1.test("hello"));
	}

	public static void f5() { // BiConsumer
		// BiConsumer 是 Consumer 的另一种特殊形式，接收两个参数
		// There are three specialized versions of the BiConsumer<T, U> interface to handle primitive types:
		// ObjIntConsumer<T>, ObjLongConsumer<T> and ObjDoubleConsumer<T>.
		BiConsumer<Random, Integer> randomNumberPrinter =
				(random, number) -> {
					for (int i = 0; i < number; i++) {
						System.out.println("next random = " + random.nextInt());
					}
				};
		randomNumberPrinter.accept(new Random(314), 5);
	}

	public static void f4() { // IntConsumer
		// 使用 Consumer 的特殊形式 IntConsumer 只消费 int 值，避免了 f3 函数中的自动拆箱装箱机制带来的性能消耗
		IntConsumer ic = num -> System.out.println("num = " + num);
		Random random = new Random(314L);
		IntSupplier is1 = () -> random.nextInt(10);
		for (int i = 0; i < 5; i++) {
			int nextRandom = is1.getAsInt();
			ic.accept(nextRandom);
		}
	}

	public static void f3() { // Consumer
		Consumer<String> c1 = str -> System.out.println(str);
		Random random = new Random(314L);
		IntSupplier is1 = () -> random.nextInt(10);
		for (int i = 0; i < 5; i++) {
			int nextRandom = is1.getAsInt();
			c1.accept("Next random " + nextRandom);
		}
	}

	public static void f2() { // IntSupplier
		// IntSupplier 是 Supplier 的特殊形式，专门用来返回值是基本数据类型 int 的 Supplier
		// 这个效果和使用 f1() 方法中的例子相同，但这一次没有因为使用自动装箱和自动拆箱机制带来的额外性能消耗
		Random random = new Random(314L);
		IntSupplier is1 = () -> random.nextInt(10);
		for (int i = 0; i < 5; i++) {
			int nextRandom = is1.getAsInt();
			System.out.println("next random = " + nextRandom);
		}
	}

	public static void f1() { // Supplier
		Random random = new Random(314L);
		Supplier<Integer> s1 = () -> random.nextInt(10); // 自动装箱 random.nextInt 返回值 int 自动装箱为一个 Integer 对象
		for (int i = 0; i < 5; i++) {
			int integer = s1.get(); // 自动拆箱
			System.out.println("integer = " + integer);
		}
	}
}
