package com.guhe.io;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author njl
 * @date 2023/2/21
 */
public class IO2App {
	public static void main(String[] args) {
//		i1();
//		i2();
//		i3();
//		i4();
		i5();
	}

	public static void i5() { // Formatting 格式化输出
		int i = 2;
		double r = Math.sqrt(2);
		// d formats an integer value as a decimal value.
		// f formats a floating point value as a decimal value. .2f 保留两位小数
		// n outputs a platform-specific line terminator.
		// x formats an integer as a hexadecimal value.
		// s formats any value as a string.
		// Except for %% and %n, all format specifiers must match an argument. If they don't, an exception is thrown.
		// In the Java programming language, the \n escape always generates the linefeed character (\u000A).
		// Don't use \n unless you specifically want a linefeed character. To get the correct line separator for the local platform, use %n.
		System.out.format("%% The square of %d is %f.%n", i, r);
	}

	public static void i4() { // Scanner 格式化地使用默认的分隔符读取流中的 token 为 double
		// Scanner 除了 char 以外，其它的 token 都支持，还支持 BigInteger 和 BigDecimal
		double sum = 0;
		try (
				Scanner sc = new Scanner(new FileReader("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io\\usnumbers.txt"));
		) {
			sc.useLocale(Locale.US);
			while (sc.hasNext()) {
				sum += sc.nextDouble();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("sum = " + sum);
	}

	public static void i3() { // Scanner 格式化地读取输入流中的 token 为 String
		try (
				Scanner sc = new Scanner(new FileReader("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io\\bar.txt"))
		) {
			while (sc.hasNext()) {
				// Scanner 默认使用空白字符作为读取下一个 token
				System.out.println(sc.nextByte());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void i2() { // 使用缓冲缓存字符流
		try (
				BufferedReader br = new BufferedReader(new FileReader("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io\\Java 语言基础.md"));
				BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io\\Java 语言基础 Copy2.md"))
		) {
			// BufferedReader 和 BufferedWriter 同 BufferedInputStream 和 BufferedOutputStream 类似，内部都设置了 cb[] 数组
			// 进行缓存或缓冲，数组的大小默认也是 8192，可以通过构造函数自定义。
			int read;
			while ((read = br.read()) != -1) {
				bw.write(read);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void i1() { // 使用缓冲缓存字节流
		try (
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io\\img.png"));
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io\\img Copy2.png"))
		) {
			// 这时候我们就不用手动创建缓冲区了，BufferedInputStream 和 BufferedOutputStream 都内置了 buf 缓冲区分别用来存储
			// 读取的数据和写入的数据。默认缓存区大小都是 8192 个字节。
			int read;
			while ((read = bis.read()) != -1) {
				bos.write(read);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
