package com.guhe.string;

/**
 * @author njl
 * @date 2023/1/28
 */
public class StringApp {
	public static void main(String[] args) {
		str1();
		String str = "helloworld";
		char[] data = str.toCharArray();// 将字符串转为数组
		for (int x = 0; x < data.length; x++) {
			System.out.print(data[x] + "  ");
			data[x] -= 32;
			System.out.print(data[x] + "  ");
		}
		System.out.println(new String(data));
	}

	public static void str1() {
		String s1 = "abc"; // s1 直接指向常量池中的引用
		String s2 = new String("abc"); // s2 指向堆中的字符串对象,但其中的数组value引用与常量池中"abc"的引用相等
		System.out.println(s1 == s2); // false
		System.out.println(s1.equals(s2)); // true
	}

	public static void str2() {
		String s1 = "a" + "b" + "c"; // 编译器的常量优化 s1 = "abc",也叫做常量折叠
		String s2 = "abc";
		System.out.println(s1 == s2); // true
		System.out.println(s1.equals(s2)); // false
	}

	public static void str3() {
		String s1 = "ab";
		// final String s1 = "ab";
		String s2 = "abc";
		String s3 = s1 + "c";
		// s1是变量,因此编译器无法优化,实际执行的是StringBuilder的append方法拼接s1对象和字符串"c",
		// 返回的是StringBuilder的toString方法,创建的是一个new String的字符串对象,但如果用final修饰s1那么,s3==s2就是true了,因为
		// s1是常量,编译时值是确定的
		System.out.println(s3 == s2);
		System.out.println(s3.equals(s2)); // true
	}

	public static void str4() {
		String str1 = "a".concat("b").concat("c");
		String str2 = "a" + "b" + "c";
		String str3 = "abc";
		String str4 = new String("abc");
		System.out.println(str1 == str2); //运行结果为false
		System.out.println(str1 == str3); //运行结果为false
		System.out.println(str2 == str3); //运行结果为ture
		System.out.println(str2 == str4); //运行结果为false
		System.out.println(str1.equals(str4)); //运行结果为true
	}
}
