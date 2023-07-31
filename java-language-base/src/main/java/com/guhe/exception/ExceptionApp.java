package com.guhe.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author njl
 * @date 2023/1/29
 */
public class ExceptionApp {
	public static void main(String[] args) {
		e5();
	}

	public static void e5() {
		try {
			e4();
		} catch (MyException e) {
//			throw new RuntimeException(e.getMessage(), e);
			StackTraceElement[] elements = e.getStackTrace();
			for (StackTraceElement element : elements) {
				System.err.println(element.getFileName()
						+ ":" + element.getLineNumber()
						+ ">> "
						+ element.getMethodName() + "()");
			}
		}
	}

	public static void e4() {
		try {
			int i = 1 / 0;
		} catch (ArithmeticException e) {
			throw new MyException("除数不能为 0", e);
		}
	}

	public static void e3() { // try-with-resources
		try (
				// throws 和 throw，throws 用来在方法的定义处，方法名称和参数列表之后声明方法可能抛出的异常，
				// 通常是 checked exception，当然也可以选择抛出 runtime exception，将可能抛出的异常交给
				// 方法的调用者处理；throw 关键字后紧跟着 Throwable 类的实例对象，表示抛出的异常，通常是运行时异常。
				// 对于 throw 抛出的异常，方法的调用者可以不用处理。

				// 对于 checked exception，方法必须显示地使用 try-catch-finally 或者 JDK 7 新引入
				// 的 try-with-resources 语句进行捕获处理，否则程序无法通过编译。而对于 unchecked exception
				// 也叫 runtime exception 运行时异常来说，程序可以选择不捕获处理，也能正常通过编译。这些异常
				// 一旦发生且如果再当前方法没有处理，那么 JVM 会在方法调用栈中进行查找合适的异常处理器进行处理。

				// try 语句块后 catch 或者 finally 语句块可以不同时出现，但是必须至少存在一个

				// 使用 try-with-resources 语句声明的资源必须实现了 AutoCloseable 接口
				// 多个用 try 打开的资源之间使用 ; 分开，这些打开的资源会在 try 语句正常结束之前或者 try 代码块
				// 发生异常进入 catch 代码块之前自动释放打开的资源，释放的顺序是定义它们的顺序
				FileInputStream fis = new FileInputStream("in.txt");
				FileOutputStream fos = new FileOutputStream("out.txt")
		) {

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void e2() {
		try {
			// exception1 方法签名后抛出了 FileNotFoundException，因此调用者需要处理这个异常或者也将该异常抛出
			e1();
		} catch (FileNotFoundException e) {
			// 使用 throw 显示地抛出一个异常
			throw new RuntimeException(e);
		}
	}

	public static void e1() throws FileNotFoundException  /* 编译期异常（受检异常，不处理程序无法通过编译） */ {
		FileInputStream fis = new FileInputStream("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\exception\\ExceptionApp.java");
	}
}
