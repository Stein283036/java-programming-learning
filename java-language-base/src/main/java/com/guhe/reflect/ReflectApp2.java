package com.guhe.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author njl
 * @date 2023/2/1
 */
public class ReflectApp2 {
	public static void main(String[] args) {
//		reflect1();
//		reflect2();
		reflect3();
	}

	public static void reflect3() {
		Object obj = new Person("stein");
		Class<?> cls = obj.getClass();
		Field name = null;
		try {
			name = cls.getDeclaredField("name");
			name.setAccessible(true);
			name.set(obj, "STEIN");
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		try {
			Object o = name.get(obj);
			System.out.println("o = " + o);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static void reflect2() {
		Field f = null;
		try {
			f = String.class.getDeclaredField("value");
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
		System.out.println(f.getName()); // "value"
		System.out.println("f.getType() = " + f.getType()); // class [C
		int m = f.getModifiers();

		System.out.println("Modifier.isFinal(m) = " + Modifier.isFinal(m)); // true
		System.out.println("Modifier.isPublic(m) = " + Modifier.isPublic(m)); // false
		System.out.println("Modifier.isProtected(m) = " + Modifier.isProtected(m)); // false
		System.out.println("Modifier.isPrivate(m) = " + Modifier.isPrivate(m)); // true
		System.out.println("Modifier.isStatic(m) = " + Modifier.isStatic(m)); // false
	}

	public static void reflect1() {
		try {
			Class<Student> cls = Student.class;
			Field name = cls.getDeclaredField("name");
			System.out.println("字段名称: " + name.getName());
			System.out.println("字段的类型: " + name.getType());
			System.out.println("字段的修饰符: " + name.getModifiers());
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
	}
}

class Student extends Person {
	public int score;
	public int grade;
	private String name;

	public Student(String name) {
		super(name);
	}
}

class Person {
	private String name;

	public Person(String name) {
		this.name = name;
	}
}
