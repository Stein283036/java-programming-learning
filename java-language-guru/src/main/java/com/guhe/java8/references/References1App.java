package com.guhe.java8.references;

import com.guhe.java8.lambda.Converter;

/**
 * @author njl
 * @date 2023/2/13
 */
public class References1App {
	public static void main(String[] args) {
		r1();
	}

	public static void r2() { // 构造方法引用
		PersonFactory<Person> personFactory = Person::new;
		Person p1 = personFactory.create("James", "Leo");
	}

	public static void r1() { // 成员方法引用
		Something something = new Something();
		Converter<String, String> converter = something::startsWith;
		String s = converter.convert("Java");
		System.out.println("s = " + s);
	}
}
