package com.stein.java8.references;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

/**
 * @author njl
 * @date 2023/2/18
 */
public class References2App {
	public static void main(String[] args) {
		r3();
	}

	public static void r5() { // 构造器方法引用
		Supplier<List<String>> s1 = () -> new ArrayList<>(); // no constructor method reference
		Supplier<List<String>> s2 = ArrayList::new; // 没有使用 <> 运算符指定泛型的参数类型
		Supplier<List<String>> s3 = ArrayList<String>::new; // 使用 <> 运算符指定泛型的参数化类型

//		Supplier<List<String>> newListOfStrings = () -> new ArrayList<>();
		// 引用的是 ArrayList 的无参构造器方法
		Supplier<List<String>> newListOfStrings = ArrayList::new;
//		Function<Integer, List<String>> newListOfNStrings = size -> new ArrayList<>(size);
		// 引用的是 ArrayList 的 ArrayList(int initialCapacity) 构造器方法
		Function<Integer, List<String>> newListOfNStrings = ArrayList::new;
	}

	public static void r4() { // 实例方法引用（Bound）
		Consumer<String> c1 = System.out::println;
		// This method reference is called a bound method reference.
		// This method reference is called bound because the object on which the method is called is
		// defined in the method reference itself.
		// So this call is bound to the object given in the method reference.
		// System.out::println 这个方法引用绑定了 out 对象本身
	}

	public static void r3() { // 实例方法引用（Unbound）
		Function<String, Integer> f1 = s -> s.length(); // old-school
		Function<String, Integer> f2 = String::length; // 没有使用中间对象，如 System.out
		// Or
		ToIntFunction<String > f3 = String::length;
		// length 是 String 类的一个实例方法，因此 String::length 是一个实例方法引用，虽然它的语法看起来和静态方法引用一样

		BiFunction<String, String, Integer> bf1 = (word, sentence) -> sentence.indexOf(word); // old-school
		Integer i1 = bf1.apply("software", "I am a software developer.");
		System.out.println("i1 = " + i1);

		BiFunction<String, String, Integer> bf2 = String::indexOf; // 实例方法引用的一个高级用法
		Integer i2 = bf2.apply("I like programming", "like");
		System.out.println("i2 = " + i2);
	}

	public static void r2() { // 静态方法引用
		DoubleUnaryOperator sqrt1 = a -> Math.sqrt(a);
		// 上面的 lambda 表达式就是一个 Math.sqrt((double a) 静态方法的引用，其中参数类型和返回值类型完全与这个静态方法匹配
		DoubleUnaryOperator sqrt2 = Math::sqrt;

		IntBinaryOperator max1 = (a, b) -> Math.max(a, b); // old-school
		IntBinaryOperator max2 = Math::max; // static method reference to Math.max(int, int)
	}

	public static void r1() {
		Consumer<String> c1 = str -> System.out.println(str); // old-school
		// lambda 表达式引用 System.out 对象中的 println 方法
		Consumer<String> c2 = System.out::println; // new-school, use method reference from java 8
		// 使用方法引用就是在 引用的这个方法需要满足 lambda 表达式对应的函数式接口中的抽象方法的参数列表和返回值类型
	}
}
