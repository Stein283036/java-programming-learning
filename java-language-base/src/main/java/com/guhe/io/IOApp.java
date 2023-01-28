package com.guhe.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author njl
 * @date 2023/1/28
 */
public class IOApp {
	public static void main(String[] args) {
//		io1();
//		io2();
//		io3();
	}

	/**
	 * 基于字符流读取纯文本文件（字符文件）
	 */
	public static void io5() {

	}

	/**
	 * 基于字节流读取二进制文件（音频、视频、图片等）
	 */
	public static void io4() {
		
	}

	/**
	 * 读取控制台输入 - 方式三
	 */
	public static void io3() {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		System.out.println("line = " + line);
	}

	/**
	 * 读取控制台输入 - 方式二
	 */
	public static void io2() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		do {
			try {
				line = br.readLine();
				System.out.println("line = " + line);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} while (!line.equals("EOF"));
	}

	/**
	 * 读取控制台输入 - 方式一
	 */
	public static void io1() {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("输入字符, 按下 'q' 键退出。");
		char c;
		do {
			try {
				c = (char) bufferedReader.read();
				System.out.print(c);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} while (c != 'q');
	}
}
