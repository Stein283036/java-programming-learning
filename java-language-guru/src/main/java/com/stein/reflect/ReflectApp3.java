package com.stein.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author njl
 * @date 2023/2/1
 */
public class ReflectApp3 {
	public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException, InstantiationException {
//		m1();
//		m2();
//		m3();
//		m4();
		m5();
	}

	/**
	 * 反射创建对象
	 */
	public static void m5() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		Class<ArrayList> cls = ArrayList.class;
		ArrayList instance = cls.newInstance();
		System.out.println("instance = " + instance);

		Constructor<ArrayList> c1 = cls.getConstructor(int.class);
		ArrayList arrayList = c1.newInstance(20);
		Constructor<ArrayList> c2 = cls.getConstructor(Collection.class);
		ArrayList arrayList1 = c2.newInstance(Arrays.asList(1, 2, 3));
		System.out.println("arrayList1 = " + arrayList1);
		System.out.println("arrayList = " + arrayList);
	}

	/**
	 * 反射多态
	 */
	public static void m4() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Class<?> cls = Class.forName("reflect.Flyable");
		Method fly = cls.getMethod("fly");
		fly.invoke(new Fly1());
		fly.invoke(new Fly2());

		Class<A> cls2 = A.class;
		Method method = cls2.getMethod("method");
		Object invoke = method.invoke(new B());
		System.out.println("invoke = " + invoke);
	}

	/**
	 * 反射调用静态方法
	 */
	public static void m3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Class<String> cls = String.class;
		Method valueOf = cls.getDeclaredMethod("valueOf", int.class);
		String str = (String) valueOf.invoke(null, 1000);
		System.out.println("str = " + str);
	}

	/**
	 * 反射调用实例方法
	 */
	public static void m2() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
		// 正射
		String s = "Hello world";
		String r = s.substring(6); // "world"
		// 反射
		Method substring = s.getClass().getDeclaredMethod("substring", int.class);
		String subString = (String) substring.invoke(s, 6);
		System.out.println("subString = " + subString);

		Field value = s.getClass().getDeclaredField("value");
		value.setAccessible(true);
		value.set(s, new char[]{'a', 'b', 'c'});
		System.out.println((char[]) value.get(s));
	}

	public static void m1() throws NoSuchMethodException {
		Class<Student2> cls = Student2.class;
		Method getGrade = cls.getDeclaredMethod("getGrade", int.class);
		// 获得方法的声明类对象
		System.out.println(getGrade.getDeclaringClass()); // class com.guhe.reflect.Student2
		System.out.println(getGrade.getName());
		System.out.println(getGrade.getReturnType());
		System.out.println(getGrade.getModifiers());
		System.out.println(Arrays.toString(getGrade.getParameters()));
		Method getScore = cls.getMethod("getScore", String.class);
		Method method = cls.getMethod("getName");
	}
}

class Student2 extends Person2 {
	public int getScore(String type) {
		return 99;
	}

	private int getGrade(int year) {
		return 1;
	}
}

class Person2 {
	public String getName() {
		return "Person";
	}
}

interface Flyable {
	void fly();
}

class Fly2 implements Flyable {

	@Override
	public void fly() {
		System.out.println("Fly2 - Flyable");
	}
}

class Fly1 implements Flyable {

	@Override
	public void fly() {
		System.out.println("Fly1 - Flyable");
	}
}

class A {
	public void method() {
		System.out.println("A - method");
	}
}

class B extends A {
	@Override
	public void method() {
		System.out.println("B method");
	}
}