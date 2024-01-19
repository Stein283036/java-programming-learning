package com.stein.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * Introduction to the Java NIO2 File API
 * 学习 NIO2 进行基本的文件操作，Files 类基于 Path 实例
 *
 * @author njl
 * @date 2023/3/6
 */
public class NIOApp1 {
	private static final String HOME = System.getProperty("user.home"); // C:\Users\Administrator

	public static void main(String[] args) throws IOException {
//		n1();
//		n2();
//		n3();
//		n4();
//		n5();
		n6();
	}

	public static void n6() throws IOException { // Moving Files
		Path dir1 = Paths.get(
				HOME + "/firstdir_" + UUID.randomUUID());
		Path dir2 = Paths.get(
				HOME + "/otherdir_" + UUID.randomUUID());

		Files.createDirectory(dir1);
		Files.createDirectory(dir2);

		Path file1 = dir1.resolve("filetocopy.txt");
		Path file2 = dir2.resolve("filetocopy.txt");

		Files.createFile(file1);
		System.out.println(Files.exists(file1)); // true
		System.out.println(Files.exists(file2)); // false

		Files.move(file1, file2, StandardCopyOption.REPLACE_EXISTING);
		System.out.println(Files.exists(file1)); // false
		System.out.println(Files.exists(file2)); // true
	}

	public static void n5() throws IOException { // Copying Files
		Path dir1 = Paths.get(
				HOME + "/firstdir_" + UUID.randomUUID());
		Path dir2 = Paths.get(
				HOME + "/otherdir_" + UUID.randomUUID());
		Files.createDirectory(dir1);
		Files.createDirectory(dir2);
		Path file1 = dir1.resolve("filetocopy.txt");
		Path file2 = dir2.resolve("filetocopy.txt");

		Files.createFile(file1);
		System.out.println(Files.exists(file1)); // true
		System.out.println(Files.exists(file2)); // false
		// 拷贝文件，如果目标文件存在，则拷贝失败，除非指定 REPLACE_EXISTING 选项
		Files.copy(file1, file2);
		Files.copy(file1, file2, StandardCopyOption.REPLACE_EXISTING);
		// 拷贝目录时只会拷贝一个空目录，目录中的内容不会被拷贝。
	}

	public static void n4() throws IOException { // Deleting a File
		// 删除文件
		Path p = Paths.get(HOME + "/fileToDelete.txt");
		Files.createFile(p);
		System.out.println(Files.exists(p)); // true
		// 如果要删除的文件不存在在文件系统，那么会抛出 IOException
		Files.delete(p);
		System.out.println(Files.notExists(p)); // true

		Path p2 = Paths.get(HOME + "/" + "nonexsist_file");
		System.out.println(Files.notExists(p2)); // true
		// 只有在文件存在时才删除
		Files.deleteIfExists(p2);

		// 删除目录
		// 如果要删除的目录不为空，那么会抛出
		Path p3 = Paths.get(HOME, "/" + "dir");
		Files.createDirectory(p3);
		Path f3 = p3.resolve("mylog.tmp");
		Files.createFile(f3);
		Files.deleteIfExists(p3); // DirectoryNotEmptyException
	}

	public static void n3() throws IOException { // Creating Temporary Files
		// Many applications create a trail of temporary files in the file system as they run.
		// As a result, most file systems have a dedicated directory to store temporary files generated
		// by such applications.

		// createTempFile API
		String prefix = "myTemp";
		String suffix = ".log";
		Files.createTempFile(prefix, suffix); // myTemp4175550648898577854.log
		// 使用默认的临时文件格式
		Path p = Paths.get(HOME, "/");
		Files.createTempFile(p, null, null);
	}

	public static void n2() throws IOException { // Creating Files
		String fileName = "myfile_" + UUID.randomUUID().toString() + ".txt";
		// 创建文件时，Path 路径除了要创建的文件名意外，其它路径必须存在，否则抛出 IOException，如果要创建的文件名已经存在，而且没有
		// 指定覆盖的行为，那么会抛出 FileAlreadyExistsException。
		Path p1 = Paths.get(HOME, "/" + fileName);
		Files.createFile(p1);

		// 创建目录
		String dirName = "myDir_" + UUID.randomUUID().toString();
		Path p2 = Paths.get(HOME, "/" + dirName);
		// 使用该方法创建目录时，除了最后一个要创建的目录不存在意外，前面的目录路径均必须存在，否则抛出 IOException，如果要创建的
		// 目录已存在，且没有指定覆盖行为，那么会抛出 FileAlreadyExistsException。
		Files.createDirectory(p2);
		Path dir = Paths.get(
				HOME + "/myDir_" + UUID.randomUUID().toString());
		Path subdir = dir.resolve("subdir");
		// 创建目录层级，如果中间的目录不存在，则递归创建这些目录
		Files.createDirectories(subdir);

	}

	public static void n1() throws IOException { // Checking a File or Directory
		Path p1 = Paths.get(HOME);
		// 检查文件是否存在
		System.out.println(Files.exists(p1)); // true
		// 检查文件是否不存在
		Path p2 = Paths.get(HOME, "/inexistent_file.txt");
		System.out.println(Files.notExists(p2)); // true
		// 检查文件是普通文件还是目录
		System.out.println(Files.isRegularFile(p1)); // false
		System.out.println(Files.isDirectory(p1)); // true
		// 检查文件权限
		System.out.println(Files.isReadable(p1)); // 可读 true
		System.out.println(Files.isWritable(p1)); // 可写 true
		System.out.println(Files.isExecutable(p1));// 可执行 true
		// 检查两个 Path 实例指向的文件是否相同
		Path p3 = Paths.get(HOME);
		System.out.println(Files.isSameFile(p1, p3)); // true
	}
}
