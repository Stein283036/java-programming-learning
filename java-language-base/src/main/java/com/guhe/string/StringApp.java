package com.guhe.string;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author njl
 * @date 2023/1/28
 */
public class StringApp {
	public static void main(String[] args) throws UnsupportedEncodingException {
//		str1();
//		str5();
//		str8();
//		str9();
//		str10();
		str11();
	}

	public static void str11() {
		String string = "abc123adb23456aa";
		System.out.println(string);//abc123adb23456aa
		System.out.println(string.replace('a', 'A'));
		System.out.println(string.replaceFirst("\\d", "ABC"));
		System.out.println(string.replaceAll("\\d", "ABC"));

		//使用replace将a替换成H
		System.out.println(string.replace("a", "H"));//Hbc123Hdb23456HH
		//使用replaceFirst将第一个a替换成H
		System.out.println(string.replaceFirst("a", "H"));//Hbc123adb23456aa
		//使用replace将a替换成H
		System.out.println(string.replaceAll("a", "H"));//Hbc123Hdb23456HH

		//使用replaceFirst将第一个数字替换成H
		System.out.println(string.replaceFirst("\\d", "H"));//abcH23adb23456aa
		//使用replaceAll将所有数字替换成H
		System.out.println(string.replaceAll("\\d", "H"));//abcHHHadbHHHHHaa
	}

	public static void str10() {
		// 调用 intern 方法，会看字符串常量冲中是否有和该对象相等的字符串，通过 equals 比较，
		// 如果有，则返回池中对象的引用，否则将该对象添加到池中并返回池中的引用
		String str1 = new String("HelloWorld").intern();
		String str2 = "HelloWorld";
		System.out.println(str1 == str2); // true
	}

	public static void str9() throws UnsupportedEncodingException {
		byte[] b1 = "Hello".getBytes(); // 按系统默认编码转换，不推荐
		byte[] b2 = "Hello".getBytes("UTF-8"); // 按UTF-8编码转换
		byte[] b3 = "Hello".getBytes("GBK"); // 按GBK编码转换
		byte[] b4 = "Hello".getBytes(StandardCharsets.UTF_8); // 按UTF-8编码转换
		// 一个中文占 三 个字节
		byte[] b5 = "这是中文".getBytes(StandardCharsets.UTF_8); // 按UTF-8编码转换
		byte[] b6 = "这是中文".getBytes(); // 按UTF-8编码转换
	}

	public static void str8() {
		// 使用trim()方法可以移除字符串首尾空白字符。空白字符包括空格，\t，\r，\n
		System.out.println("    \thow are you\r\n   ".trim());
		; // "Hello"
		// isEmpty()来判断字符串是否为空
		System.out.println("".isEmpty());
		System.out.println(" ".isEmpty());
		// 替换子串
		String s = "hello";
		s.replace('l', 'w'); // "hewwo"，所有字符'l'被替换为'w'
		s.replace("ll", "~~"); // "he~~o"，所有子串"ll"被替换为"~~"
		// 分割字符串
		String str = "A,B,C,D";
//		String[] split = str.split("\\,");
		String[] split = str.split(",");
		System.out.println("split = " + Arrays.toString(split));
		// 拼接字符串
		String[] arr = {"A", "B", "C"};
		String join = String.join("***", arr);
		System.out.println("join = " + join);
		// 格式化字符串
		// %s：显示字符串；
		//%d：显示整数；
		//%x：显示十六进制整数；
		//%f：显示浮点数。
	}

	public static void str7() {
		// 是否包含子串:
		"Hello".contains("ll"); // true
		// 搜索子串的更多的例子：
		"Hello".indexOf("l"); // 2
		"Hello".lastIndexOf("l"); // 3
		"Hello".startsWith("He"); // true
		"Hello".endsWith("lo"); // true
		// 提取子串的例子：
		"Hello".substring(2); // "llo"
		"Hello".substring(2, 4); // "ll"
	}

	public static void str6() {
		String str = "helloworld";
		char[] data = str.toCharArray();// 将字符串转为数组
		for (int x = 0; x < data.length; x++) {
			System.out.print(data[x] + "  ");
			data[x] -= 32;
			System.out.print(data[x] + "  ");
		}
		System.out.println(new String(data));
	}

	public static void str5() {
		String s1 = "hello";
		// toLowerCase() 的 返回结果 return new String(result, 0, len + resultOffset);
		String s2 = "HELLO".toLowerCase();
		System.out.println(s1);
		System.out.println(s2);
		if (s1 == s2) {
			System.out.println("s1 == s2");
		} else {
			System.out.println("s1 != s2");
		}
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
