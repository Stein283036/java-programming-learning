package com.guhe.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * 不同 IO 流的一些简单操作
 *
 * @author njl
 * @date 2023/2/24
 */
public class IO3App {
	public static void main(String[] args) {
//		i1();
//		i2();
//		i3();
//		i4();
//		i5();
//		i6();
		i7();
	}

	public static void i7() { // InputStreamReader OutputStreamWriter - 字节流与字符流直接进行相互转换
		Path path = Paths.get("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io\\osr.txt");

		// OutputStreamWriter - 字符流 》 字节流
		try (
				OutputStreamWriter osw = new OutputStreamWriter(Files.newOutputStream(path), StandardCharsets.UTF_8);
		) {
			// 写入到这个流中的字符将会使用显示指定的字符集或者平台默认的字符集进行编码（encode）成指定的字节。
			osw.write("Hello OutputStreamWriter"); // 底层转换成字符数组然后在进行转码成字节在写入
		} catch (IOException e) {
			e.printStackTrace();
		}

		// InputStreamReader - 字节流 》 字符流
		// InputStreamReader 是将一个字节流转成成一个字符流的桥梁，使用显示指定的或平台默认的字符集读取字节并解码（decode）。
		try (
				InputStreamReader isr = new InputStreamReader(Files.newInputStream(path), StandardCharsets.UTF_8);
		) {
			// 使用 InputStreamReader 读取出来的数据就是以字符为单位了，而不是再以字节为单位。
			char[] buffer = new char[1024];
			int len;
			while ((len = isr.read(buffer)) != -1) {
				System.out.println(new String(buffer, 0, len));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public static void i6() { // ObjectIOStream - 对象（序列化、反序列化）流
		// 对象序列化这里还有很多需要注意的知识，如 static 和 transient 修饰的字段不会参与序列化的过程等等，之后对于这个知识点要进一步
		// 详细学习。
		// ObjectOutputStream
		Path path = Paths.get("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io\\oos.txt");
		try (
				ObjectOutputStream oos = new ObjectOutputStream(
						Files.newOutputStream(path)
				)
		) {
			String str = "ObjectOutputStream";
			oos.writeObject(str);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// ObjectOutputStream
		try (
				ObjectInputStream ois = new ObjectInputStream(
						Files.newInputStream(path)
				)
		) {
			String str = (String) ois.readObject();
			System.out.println("str = " + str); // str = ObjectOutputStream
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void i5() { // PrintStream PrintWriter - 打印流
		// PrintStream
		// System.out 就是一个 PrintStream 对象。
		System.out.println("PrintStream"); // 将字符串以字符数组的形式输出，而字符流底层都是依赖字节流的。

		// PrintWriter
		try (
				CharArrayWriter caw = new CharArrayWriter();
		) {
			caw.write("CharArrayWriter");
			PrintWriter pw = new PrintWriter(caw);
			pw.print("\nPrintWriter");
			System.out.println(caw); // CharArrayWriter\nPrintWriter
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void i4() { // BufferedIOStream - 缓存流
		// 程序与计算机设备之间的交互运算速度：CPU 缓存 》 内存 》 硬盘（其它外部设备，如网络、Socket、U盘等），因此使用缓存先在内存
		// 中架设起一个桥梁，只有在从磁盘（其它外部设备）读取到的数据达到了缓冲区的大小限度时，才会将缓存区中的数据从内存读取到 CPU 缓存，
		// 以供程序访问。同样地，只有在从程序中向内存中的缓存区中写入了缓存区的大小限度时，才会将内存中的数据一次性地通过 IO 总线写入到硬
		// 盘（其它外部设备）中，以减少 CPU 和外部设备之间频繁的 IO 通信，提高程序的性能。

		// BufferedOutputStream
		// The class implements a buffered output stream. By setting up such an output stream, an application can write
		// bytes to the underlying output stream without necessarily causing a call to the underlying system for each
		// byte written.
		// 内部维护了一个 byte buf[]; 数组，长度默认是 8192。
		Path path = Paths.get("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io\\bos.txt");
		try (
				BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(path))
		) {
			// 不会立即与 IO 发生交互，而是先判断是否已经达到了缓冲区的最大长度，如果达到，则刷新缓冲区的数据到指定的外部流的目标处，否则将写入的字节保存在缓冲数组中。
			bos.write(95);
			bos.write("BufferedOutputStream".getBytes(StandardCharsets.UTF_8)); // 同 bos.write(int b)
		} catch (IOException e) {
			e.printStackTrace();
		}

		//
		try (
				BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(path))
		) {
			// 不会一个字节一个字节读导致多次 IO 通信，而是一次从内部的输入流中读取满足 BufferedInputStream 内部的 byte buf[];
			// 缓冲数组的最大大小到内存中，以减少频繁的 IO 通信，提升程序性能。默认的缓冲区大小是 8192。而当缓冲区数组大小不足时，会进行
			// 动态扩容。
//			int read = bis.read();
//			System.out.println("read = " + read);
			byte[] buf = new byte[1024];
			int len;
			while ((len = bis.read(buf)) != -1) {
				System.out.println(new String(buf, 0, len)); // _BufferedOutputStream
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void i3() { // DataIOStream - 基本数据类型流，似乎只能对一种基本数据类型操作，否则读取的时候会出现问题。
		// DataOutputStream
		// A data output stream lets an application write primitive Java data types to an output stream
		// in a portable way. An application can then use a data input stream to read the data back in.
		Path path = Paths.get("D:\\JavaProjects\\java-programming-learning\\java-language-base\\src\\main\\java\\com\\guhe\\io\\dos.txt");
		try (
				DataOutputStream dos = new DataOutputStream(Files.newOutputStream(path))
		) {
			dos.writeBytes("DataOutputStream");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// DataInputStream
		// A data input stream lets an application read primitive Java data types from an underlying input stream
		// in a machine-independent way. An application uses a data output stream to write data that can later be
		// read by a data input stream.
		try (
				DataInputStream dis = new DataInputStream(Files.newInputStream(path))
		) {
			int len;
			byte[] buffer = new byte[1024];
			len = dis.read(buffer);
			System.out.println(new String(buffer, 0, len)); // DataOutputStream
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void i2() { // PipedIOStream - 管道流
		// FIXME 这段代码报 Pipe closed
		try (final PipedOutputStream pos = new PipedOutputStream();
		     final PipedInputStream pis = new PipedInputStream(pos)) {
			// 使用管道作为线程间通信的一种方式
			byte[] content = "content shared between multiple threads".getBytes(StandardCharsets.UTF_8);
			Thread t1 = new Thread(() -> { // 通过 PipedOutputStream 向该管道中写入数据
				try {
					pos.write(content);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			Thread t2 = new Thread(() -> {
				try {
					byte[] buffer = new byte[1024];
					int len;
					while ((len = pis.read(buffer)) != -1) {
						System.out.println(new String(buffer, 0, len));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			t1.start();
			TimeUnit.SECONDS.sleep(5L);
			t2.start();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void i1() { // ByteArrayIOStream - 数组流
		// ByteArrayInputStream
		try (
				ByteArrayInputStream bis = new ByteArrayInputStream("Learn Java IO API".getBytes(StandardCharsets.UTF_8));
		) {
			int length = 0;
			byte[] buffer = new byte[1024];
			while ((length = bis.read(buffer)) != -1) {
				System.out.println(new String(buffer, 0, length)); // Learn Java IO API
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// ByteArrayOutputStream
		try (
				ByteArrayOutputStream bos = new ByteArrayOutputStream(64); // 默认缓存的数组大小是 32 个长度单位
		) {

			byte[] content1 = "write content to an array(buf) maintained by ByteArrayOutputStream.".getBytes(StandardCharsets.UTF_8);
			bos.write(content1);
			byte[] content2 = bos.toByteArray(); // return Arrays.copyOf(buf, count); 返回一个新创建的数组，长度是原数组的大小。
			System.out.println(new String(content2, 0, content2.length)); // write content to an array(buf) maintained by ByteArrayOutputStream.
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
