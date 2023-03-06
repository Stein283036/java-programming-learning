package com.guhe.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * File、Path、Files、Paths
 *
 * @author njl
 * @date 2023/2/24
 */
public class IO4App {
	public static void main(String[] args) throws IOException {
//		i1();
//		i2();
//		i3();
//		i4();
		i5();
	}

	public static void i5() { // 删除文件目录
		try {
			Path p1 = Paths.get("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io\\b\\c\\bar.txt");
			Path p2 = Paths.get("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io\\b\\c");
			Files.deleteIfExists(p1);
			// 删除的目录必须为空
			Files.delete(p2);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void i4() { // 移动文件目录
		Path p1 = Paths.get("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io\\a");
		try {
			Files.createDirectory(p1);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		Path p2 = Paths.get("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io\\bb");
		try {
			// 移动目录
			Files.move(p1, p2, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		// 修改名称
		Path p3 = Paths.get("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io\\bb\\a.txt");
		Path p4 = Paths.get("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io\\bb\\aa.txt");
		try {
			Files.move(p3, p4);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void i3() { // 复制文件目录
		/*
		static long copy(Path source, OutputStream out);
		static Path copy(Path source, Path target, CopyOption... options);
		static long copy(InputStream in, Path target, CopyOption... options);
		 */
		// 其中 CopyOption 选项可以选择指定复制模式，一般是其子枚举类 StandardCopyOption 提供选项，有 3 种模式
		// ATOMIC_MOVE :原子复制，不会被线程调度机制打断的操作；一旦开始，就一直运行到结束;
		// COPY_ATTRIBUTES ：同时复制属性，默认是不复制属性的;
		// REPLACE_EXISTING ：重写模式，会覆盖已存在的目的文件;
		// 注意：复制文件夹的时候，只能复制空文件夹，如果文件夹非空，需要递归复制，否则只能得到一个空文件夹，而文件夹里面的文件不会被复制。
		Path p1 = Paths.get("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io\\bar.txt");
		Path p2 = Paths.get("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io\\b\\c\\bar.txt");
		try {
			// 如果目标文件已存在且没有指定 CopyOption 为 StandardCopyOption.REPLACE_EXISTING 那么会抛出 FileAlreadyExistsException。
//			Files.copy(p1, p2);
			Files.copy(p1, p2, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void i2() throws IOException { // Path 接口和 Paths 类
		Path p1 = Paths.get("D://a", "me.txt"); // FileSystems.getDefault().getPath(first, more);
		Path p2 = Paths.get("D://a//me.txt");
		Path p3 = p1.getName(0);
		Path p4 = p1.getName(1);
		System.out.println("p3 = " + p3); // p3 = a
		System.out.println("p4 = " + p4); // p4 = me.txt

		Path p5 = Paths.get("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io\\b\\c");
//		Path p6 = Files.createDirectory(p5); // 如果是创建多级目录，并且中间目录不存在的话会抛出 NoSuchFileException。
		Path p6 = Files.createDirectories(p5); // 创建多级目录
		System.out.println("p6 = " + p6); // p6 = D:\JavaProjects\java-programming-learning\java-language-base\src\main\java\com\guhe\io\b\c
	}

	private static void listFilesRecursively(File dir) {
		if (dir.isFile()) {
			return;
		}
		File[] files = dir.listFiles();
		assert files != null;
		for (File file : files) {
			if (file.isDirectory()) {
				listFilesRecursively(file);
			}
			System.out.println(file.getName());
		}
	}

	public static void i1() { // File
		// File 是对文件和目录路径名称的抽象表示，File 类只用来表示磁盘路径上的文件和目录，而不能对文件内容进行访问或修改，可以通过
		// 它获取与文件和目录相关的属性，如文件类型、文件大小、是否可读等。
		File file = new File("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io");
		listFilesRecursively(file);
	}
}
