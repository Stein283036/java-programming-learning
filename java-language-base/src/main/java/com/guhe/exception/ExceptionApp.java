package com.guhe.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author njl
 * @date 2023/1/29
 */
public class ExceptionApp {
	public static void main(String[] args) {
		try {
			// exception1 方法签名后抛出了 FileNotFoundException，因此调用者需要处理这个异常或者也将该异常抛出
			exception1();
		} catch (FileNotFoundException e) {
			// 使用 throw 显示地抛出一个异常
			throw new RuntimeException(e);
		}
	}

	public static void exception1() throws FileNotFoundException /* 编译期异常（受检异常，不处理程序无法通过编译） */ {
		FileInputStream fis = new FileInputStream("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\exception\\ExceptionApp.java");
	}
}
