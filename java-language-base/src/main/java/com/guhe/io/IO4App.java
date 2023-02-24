package com.guhe.io;

import java.io.File;

/**
 * File、Path、Files、Paths
 *
 * @author njl
 * @date 2023/2/24
 */
public class IO4App {
	public static void main(String[] args) {
		i1();
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
